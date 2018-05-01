package cn.iamding.drpc.server.service;


import cn.iamding.drpc.server.Constants;
import cn.iamding.drpc.server.rpc.CloudDiskServiceGrpc;
import cn.iamding.drpc.server.rpc.RPCUploadFileRequest;
import cn.iamding.drpc.server.rpc.RPCUploadFileResponse;
import cn.iamding.drpc.server.utils.CustomSystemUtil;
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
        LOGGER.info("recv resquest：{}", request);
        RPCUploadFileResponse rpcResponse;
        RPCUploadFileResponse.UploadResult uploadResult;
        uploadResult = RPCUploadFileResponse.UploadResult.newBuilder()
                .setFileId("id:" + System.currentTimeMillis())
                .setShareUrl("https://www.baidu.com")
                .build();
        rpcResponse = RPCUploadFileResponse.newBuilder().setCode(Constants.RESPONSE_OK)
                .setMsg("server ip " + CustomSystemUtil.OUTTER_IP)
                .setData(uploadResult)
                .setUploadJsonResponse("testJson")
                .build();
        responseObserver.onNext(rpcResponse);
        responseObserver.onCompleted();
    }


}
