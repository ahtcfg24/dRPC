package cn.iamding.drpc.service.pan;


import cn.iamding.drpc.Constants;
import cn.iamding.drpc.rpc.CloudDiskServiceGrpc;
import cn.iamding.drpc.rpc.RPCUploadFileRequest;
import cn.iamding.drpc.rpc.RPCUploadFileResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xuding_sx on 2017/7/27.
 */
@Component
public class CloudDiskServiceImpl extends CloudDiskServiceGrpc.CloudDiskServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudDiskServiceImpl.class);

    @Override
    public void rpcUpload(RPCUploadFileRequest request, StreamObserver<RPCUploadFileResponse> responseObserver) {
        RPCUploadFileResponse rpcResponse;
        RPCUploadFileResponse.UploadResult uploadResult;
        uploadResult = RPCUploadFileResponse.UploadResult.newBuilder()
                .setFileId("id:" + System.currentTimeMillis())
                .setShareUrl("https://www.baidu.com")
                .build();
        rpcResponse = RPCUploadFileResponse.newBuilder().setCode(Constants.RESPONSE_OK)
                .setMsg("请求成功")
                .setData(uploadResult)
                .setUploadJsonResponse("testJson")
                .build();
        responseObserver.onNext(rpcResponse);
        responseObserver.onCompleted();
        LOGGER.info("rpc response：{}", rpcResponse);
    }


}
