package cn.iamding.drpc.server.service;


import cn.iamding.drpc.server.Constants;
import cn.iamding.drpc.server.rpc.student.Student;
import cn.iamding.drpc.server.rpc.student.StudentServiceGrpc;
import cn.iamding.drpc.server.rpc.student.SubmitResult;
import cn.iamding.drpc.server.utils.HostUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public void submitStudent(Student student, StreamObserver<SubmitResult> responseObserver) {
        String serverAddress = HostUtils.getHostIp();
        LOGGER.info("[{}] 收到请求：[{}]", serverAddress, student);
        SubmitResult result = SubmitResult.newBuilder()
                .setCode(Constants.RESPONSE_OK)
                .setServer(serverAddress)
                .setData(student)
                .build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }


}
