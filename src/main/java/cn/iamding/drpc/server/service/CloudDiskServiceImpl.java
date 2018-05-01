package cn.iamding.drpc.server.service;


import cn.iamding.drpc.server.Constants;
import cn.iamding.drpc.server.rpc.disk.CloudDiskServiceGrpc;
import cn.iamding.drpc.server.rpc.disk.RPCUploadFileRequest;
import cn.iamding.drpc.server.rpc.disk.RPCUploadFileResponse;
import cn.iamding.drpc.server.utils.HostUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CloudDiskServiceImpl extends CloudDiskServiceGrpc.CloudDiskServiceImplBase {


    @Value("${grpc.cloud.disk.port}")
    private int port;

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudDiskServiceImpl.class);

    @Override
    public void rpcUpload(RPCUploadFileRequest request, StreamObserver<RPCUploadFileResponse> responseObserver) {
        LOGGER.info("[{}] 收到请求：{}", HostUtils.getHostIp() + ":" + port, request);
        RPCUploadFileResponse rpcResponse;
        RPCUploadFileResponse.UploadResult uploadResult;
        uploadResult = RPCUploadFileResponse.UploadResult.newBuilder()
                .setFileId("id:" + System.currentTimeMillis())
                .setShareUrl("https://www.baidu.com")
                .build();
        rpcResponse = RPCUploadFileResponse.newBuilder().setCode(Constants.RESPONSE_OK)
                .setMsg("ip=" + HostUtils.getHostIp() + " port=" + port)
                .setData(uploadResult)
                .setUploadJsonResponse("testJson")
                .build();
        responseObserver.onNext(rpcResponse);
        responseObserver.onCompleted();
    }


}
