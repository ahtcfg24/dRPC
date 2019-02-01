package cn.iamding.drpc.client.rpc.student;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.13.0-SNAPSHOT)",
        comments = "Source: StudentService.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {
  }

  public static final String SERVICE_NAME = "StudentService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSubmitStudentMethod()} instead. 
  public static final io.grpc.MethodDescriptor<cn.iamding.drpc.client.rpc.student.Student,
          cn.iamding.drpc.client.rpc.student.SubmitResult> METHOD_SUBMIT_STUDENT = getSubmitStudentMethodHelper();

  private static volatile io.grpc.MethodDescriptor<cn.iamding.drpc.client.rpc.student.Student,
          cn.iamding.drpc.client.rpc.student.SubmitResult> getSubmitStudentMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<cn.iamding.drpc.client.rpc.student.Student,
          cn.iamding.drpc.client.rpc.student.SubmitResult> getSubmitStudentMethod() {
    return getSubmitStudentMethodHelper();
  }

  private static io.grpc.MethodDescriptor<cn.iamding.drpc.client.rpc.student.Student,
          cn.iamding.drpc.client.rpc.student.SubmitResult> getSubmitStudentMethodHelper() {
    io.grpc.MethodDescriptor<cn.iamding.drpc.client.rpc.student.Student, cn.iamding.drpc.client.rpc.student.SubmitResult> getSubmitStudentMethod;
    if ((getSubmitStudentMethod = StudentServiceGrpc.getSubmitStudentMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getSubmitStudentMethod = StudentServiceGrpc.getSubmitStudentMethod) == null) {
          StudentServiceGrpc.getSubmitStudentMethod = getSubmitStudentMethod =
                  io.grpc.MethodDescriptor.<cn.iamding.drpc.client.rpc.student.Student, cn.iamding.drpc.client.rpc.student.SubmitResult>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(
                                  "StudentService", "submitStudent"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  cn.iamding.drpc.client.rpc.student.Student.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  cn.iamding.drpc.client.rpc.student.SubmitResult.getDefaultInstance()))
                          .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("submitStudent"))
                          .build();
        }
      }
    }
    return getSubmitStudentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
          io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
          io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void submitStudent(cn.iamding.drpc.client.rpc.student.Student request,
                              io.grpc.stub.StreamObserver<cn.iamding.drpc.client.rpc.student.SubmitResult> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitStudentMethodHelper(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      getSubmitStudentMethodHelper(),
                      asyncUnaryCall(
                              new MethodHandlers<
                                      cn.iamding.drpc.client.rpc.student.Student,
                                      cn.iamding.drpc.client.rpc.student.SubmitResult>(
                                      this, METHODID_SUBMIT_STUDENT)))
              .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
                               io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
                                       io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void submitStudent(cn.iamding.drpc.client.rpc.student.Student request,
                              io.grpc.stub.StreamObserver<cn.iamding.drpc.client.rpc.student.SubmitResult> responseObserver) {
      asyncUnaryCall(
              getChannel().newCall(getSubmitStudentMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
                                       io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
                                               io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public cn.iamding.drpc.client.rpc.student.SubmitResult submitStudent(cn.iamding.drpc.client.rpc.student.Student request) {
      return blockingUnaryCall(
              getChannel(), getSubmitStudentMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
                                     io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
                                             io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.iamding.drpc.client.rpc.student.SubmitResult> submitStudent(
            cn.iamding.drpc.client.rpc.student.Student request) {
      return futureUnaryCall(
              getChannel().newCall(getSubmitStudentMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_STUDENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
          io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBMIT_STUDENT:
          serviceImpl.submitStudent((cn.iamding.drpc.client.rpc.student.Student) request,
                  (io.grpc.stub.StreamObserver<cn.iamding.drpc.client.rpc.student.SubmitResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
            io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cn.iamding.drpc.client.rpc.student.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
          extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {
    }
  }

  private static final class StudentServiceMethodDescriptorSupplier
          extends StudentServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                  .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
                  .addMethod(getSubmitStudentMethodHelper())
                  .build();
        }
      }
    }
    return result;
  }
}
