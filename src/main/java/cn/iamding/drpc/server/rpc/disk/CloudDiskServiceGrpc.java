package cn.iamding.drpc.server.rpc.disk;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.4.0)",
        comments = "Source: CloudDisk.proto")
public final class CloudDiskServiceGrpc {

    private CloudDiskServiceGrpc() {
    }

    public static final String SERVICE_NAME = "CloudDiskService";

    // Static method descriptors that strictly reflect the proto.
    @io.grpc.ExperimentalApi("https://github.com/rpc/rpc-java/issues/1901")
    public static final io.grpc.MethodDescriptor<RPCUploadFileRequest,
            RPCUploadFileResponse> METHOD_RPC_UPLOAD =
            io.grpc.MethodDescriptor.<RPCUploadFileRequest, RPCUploadFileResponse>newBuilder()
                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                    .setFullMethodName(generateFullMethodName(
                            "CloudDiskService", "rpcUpload"))
                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                            RPCUploadFileRequest.getDefaultInstance()))
                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                            RPCUploadFileResponse.getDefaultInstance()))
                    .build();

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static CloudDiskServiceStub newStub(io.grpc.Channel channel) {
        return new CloudDiskServiceStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static CloudDiskServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new CloudDiskServiceBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static CloudDiskServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new CloudDiskServiceFutureStub(channel);
    }

    /**
     */
    public static abstract class CloudDiskServiceImplBase implements io.grpc.BindableService {

        /**
         */
        public void rpcUpload(RPCUploadFileRequest request,
                              io.grpc.stub.StreamObserver<RPCUploadFileResponse> responseObserver) {
            asyncUnimplementedUnaryCall(METHOD_RPC_UPLOAD, responseObserver);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            METHOD_RPC_UPLOAD,
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            RPCUploadFileRequest,
                                            RPCUploadFileResponse>(
                                            this, METHODID_RPC_UPLOAD)))
                    .build();
        }
    }

    /**
     */
    public static final class CloudDiskServiceStub extends io.grpc.stub.AbstractStub<CloudDiskServiceStub> {
        private CloudDiskServiceStub(io.grpc.Channel channel) {
            super(channel);
        }

        private CloudDiskServiceStub(io.grpc.Channel channel,
                                     io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected CloudDiskServiceStub build(io.grpc.Channel channel,
                                             io.grpc.CallOptions callOptions) {
            return new CloudDiskServiceStub(channel, callOptions);
        }

        /**
         */
        public void rpcUpload(RPCUploadFileRequest request,
                              io.grpc.stub.StreamObserver<RPCUploadFileResponse> responseObserver) {
            ClientCalls.asyncUnaryCall(
                    getChannel().newCall(METHOD_RPC_UPLOAD, getCallOptions()), request, responseObserver);
        }
    }

    /**
     */
    public static final class CloudDiskServiceBlockingStub extends io.grpc.stub.AbstractStub<CloudDiskServiceBlockingStub> {
        private CloudDiskServiceBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private CloudDiskServiceBlockingStub(io.grpc.Channel channel,
                                             io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected CloudDiskServiceBlockingStub build(io.grpc.Channel channel,
                                                     io.grpc.CallOptions callOptions) {
            return new CloudDiskServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public RPCUploadFileResponse rpcUpload(RPCUploadFileRequest request) {
            return blockingUnaryCall(
                    getChannel(), METHOD_RPC_UPLOAD, getCallOptions(), request);
        }
    }

    /**
     */
    public static final class CloudDiskServiceFutureStub extends io.grpc.stub.AbstractStub<CloudDiskServiceFutureStub> {
        private CloudDiskServiceFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private CloudDiskServiceFutureStub(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected CloudDiskServiceFutureStub build(io.grpc.Channel channel,
                                                   io.grpc.CallOptions callOptions) {
            return new CloudDiskServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<RPCUploadFileResponse> rpcUpload(
                RPCUploadFileRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(METHOD_RPC_UPLOAD, getCallOptions()), request);
        }
    }

    private static final int METHODID_RPC_UPLOAD = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final CloudDiskServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(CloudDiskServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_RPC_UPLOAD:
                    serviceImpl.rpcUpload((RPCUploadFileRequest) request,
                            (io.grpc.stub.StreamObserver<RPCUploadFileResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static final class CloudDiskServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return CloudDiskProto.getDescriptor();
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (CloudDiskServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new CloudDiskServiceDescriptorSupplier())
                            .addMethod(METHOD_RPC_UPLOAD)
                            .build();
                }
            }
        }
        return result;
    }
}
