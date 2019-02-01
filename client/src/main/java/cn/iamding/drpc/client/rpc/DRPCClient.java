package cn.iamding.drpc.client.rpc;


import cn.iamding.drpc.client.Constants;
import cn.iamding.drpc.client.rpc.disk.CloudDiskServiceGrpc;
import cn.iamding.drpc.client.rpc.disk.RPCUploadFileRequest;
import cn.iamding.drpc.client.rpc.disk.RPCUploadFileResponse;
import cn.iamding.drpc.client.rpc.student.Student;
import cn.iamding.drpc.client.rpc.student.StudentServiceGrpc;
import cn.iamding.drpc.client.rpc.student.SubmitResult;
import cn.iamding.drpc.client.zk.ZookeeperZoneAwareNameResolverProvider;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DRPCClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DRPCClient.class);

    @Value("${zk.connect-string}")
    private String zkConnectString;

    private CloudDiskServiceGrpc.CloudDiskServiceBlockingStub diskBlockingStub;

    private StudentServiceGrpc.StudentServiceBlockingStub studentBlockingStub;

    private ManagedChannel channel;

    public DRPCClient() {
    }

    @PostConstruct
    public void start() {
        this.channel = NettyChannelBuilder
                .forTarget(Constants.SERVER_TARGET)
                .nameResolverFactory(ZookeeperZoneAwareNameResolverProvider.newBuilder()
                        .setZookeeperAddress(zkConnectString)
                        .build())
                .negotiationType(NegotiationType.PLAINTEXT)
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
                .build();
        if (this.channel == null) {
            LOGGER.error("RpcClient启动失败！");
            return;
        }
        diskBlockingStub = CloudDiskServiceGrpc.newBlockingStub(this.channel);
        studentBlockingStub = StudentServiceGrpc.newBlockingStub(this.channel);
        LOGGER.error("RpcClient启动成功！");
    }

    public void shutdown() {
        this.channel.shutdown();

    }

    public RPCUploadFileResponse uploadByteFile(byte[] file, String fileType) {
        return this.uploadByteFile(file, fileType, "public");
    }

    public RPCUploadFileResponse uploadByteFile(byte[] file, String fileType, String shareType) {
        if (diskBlockingStub == null) {
            LOGGER.error("还没有启动Client！");
            return null;
        }
        RPCUploadFileRequest request = RPCUploadFileRequest.newBuilder()
                .setFileType(fileType)
                .setShareType(shareType)
                .setContent(ByteString.copyFrom(file))
                .build();
        return withRetry(() -> diskBlockingStub.rpcUpload(request), 3);
    }

    public SubmitResult submitStudent(String name, int age, String sex, String photoUrl) {
        if (studentBlockingStub == null) {
            LOGGER.error("还没有启动Client！");
            return null;
        }
        Student student = Student.newBuilder()
                .setName(name)
                .setAge(age)
                .setSex(sex)
                .setPhoto(photoUrl)
                .build();
        return withRetry(() -> studentBlockingStub.submitStudent(student), 3);
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

    interface ExceptionalSupplier<T> {
        T supply() throws Exception;
    }


}