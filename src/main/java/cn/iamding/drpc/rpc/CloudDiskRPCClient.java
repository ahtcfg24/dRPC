package cn.iamding.drpc.rpc;


import cn.iamding.drpc.Constants;
import cn.iamding.drpc.service.zk.ZookeeperZoneAwareNameResolverProvider;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

interface ExceptionalSupplier<T> {
    T supply() throws Exception;
}

/**
 * 用于和生成的代码一起打包给客户端调用，服务端不使用
 * author 徐鼎
 * time 2017/08/07
 */
@Component
public class CloudDiskRPCClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudDiskRPCClient.class);
    public static final String SHARE_TYPE_PUBLIC = "public";

    @Value("${zk.connect-string}")
    private String zkConnectString;

    private CloudDiskServiceGrpc.CloudDiskServiceBlockingStub blockingStub;

    private ManagedChannel channel;

    public CloudDiskRPCClient() {
    }

    private static <T> T withRetry(ExceptionalSupplier<T> supplier, int maxAttempts) {
        try {
            return supplier.supply();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE && maxAttempts > 0) {
                LOGGER.error("grpc连接异常[{}]", e);
                return withRetry(supplier, maxAttempts - 1);
            } else {
                throw e;
            }
        } catch (Exception e) {
            LOGGER.error("grpc异常[{}]", e);
            throw new RuntimeException(e);
        }
    }

    public void start() {
        this.channel = NettyChannelBuilder.forTarget(Constants.SERVER_TARGET)
                .nameResolverFactory(ZookeeperZoneAwareNameResolverProvider.newBuilder()
                        .setZookeeperAddress(zkConnectString)
                        .build())
                .usePlaintext(true)
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
                .build();
        if (this.channel == null) {
            LOGGER.error("RpcClient启动失败！");
            return;
        }
        blockingStub = CloudDiskServiceGrpc.newBlockingStub(this.channel);
    }

    public void shutdown() {
        this.channel.shutdown();

    }

    public RPCUploadFileResponse uploadMultipartFile(MultipartFile multipartFile) {
        return this.uploadMultipartFile(multipartFile, SHARE_TYPE_PUBLIC);
    }

    public RPCUploadFileResponse uploadMultipartFile(MultipartFile multipartFile, String shareType) {
        String fileType = multipartFile.getContentType();
        if (fileType.contains("/")) {
            fileType = fileType.split("/")[1];
        } else {
            LOGGER.warn("contentType error:[{}]", fileType);
        }

        byte[] file = null;

        try {
            file = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.uploadByteFile(file, fileType, shareType);

    }

    public RPCUploadFileResponse uploadByteFile(byte[] file, String fileType) {
        return this.uploadByteFile(file, fileType, SHARE_TYPE_PUBLIC);
    }

    public RPCUploadFileResponse uploadByteFile(byte[] file, String fileType, String shareType) {
        if (blockingStub == null) {
            LOGGER.error("还没有启动Client！");
            return null;
        }
        RPCUploadFileRequest request = RPCUploadFileRequest.newBuilder()
                .setFileType(fileType)
                .setShareType(shareType)
                .setContent(ByteString.copyFrom(file))
                .build();
        return withRetry(() -> blockingStub.rpcUpload(request), 3);
    }


}