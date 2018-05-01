package cn.iamding.drpc.server.service;


import cn.iamding.drpc.server.Constants;
import cn.iamding.drpc.server.rpc.student.Student;
import cn.iamding.drpc.server.rpc.student.StudentServiceGrpc;
import cn.iamding.drpc.server.rpc.student.SubmitResult;
import cn.iamding.drpc.server.utils.HostUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {


    @Value("${grpc.cloud.disk.port}")
    private int port;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public void submitStudent(Student student, StreamObserver<SubmitResult> responseObserver) {
        String serverAddress = HostUtils.getHostIp() + ":" + port;
        LOGGER.info("[{}] 收到请求：[{}]", serverAddress, student);
        // TODO: 2018/5/1 处理业务逻辑
        SubmitResult result = SubmitResult.newBuilder().setCode(Constants.RESPONSE_OK)
                .setMsg(serverAddress)
                .setData(student)
                .build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }


}
