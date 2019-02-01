package cn.iamding.drpc.server.service;


import cn.iamding.drpc.server.Constants;
import cn.iamding.drpc.server.utils.HostUtils;
import cn.iamding.drpc.server.zk.ServiceDiscovery;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * author 徐鼎
 * rpc服务端启动程序
 * Server启动后会一直堵塞线程，需要在单独线程中开启，否则会堵塞主线程
 */
@Component
public class DRPCServer {

    @Value("${zk.connect-string}")
    private String zkConnectString;

    @Value("${grpc.cloud.disk.port}")
    private int port;

    @Autowired
    private CloudDiskServiceImpl cloudDiskService;

    @Autowired
    private StudentServiceImpl studentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DRPCServer.class);


    public void start() throws Exception {
        Server server = NettyServerBuilder.forPort(port)
                .addService(cloudDiskService)
                .addService(studentService)
                .build()
                .start();
        LOGGER.info("RPC服务端启动成功！");
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery(zkConnectString);
        String ip = HostUtils.getHostIp();
        serviceDiscovery.registerService("drpc-server", URI.create("dns://" + ip + ":" + server.getPort()), Constants.SERVER_TARGET);
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            LOGGER.error("启动异常！[{}]", e);
            serviceDiscovery.deregister("drpc-server", URI.create("dns://" + ip + ":" + server.getPort()), "DRPC");
            serviceDiscovery.close();
        }
    }


}