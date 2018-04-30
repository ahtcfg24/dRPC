package cn.iamding.drpc.server.listener;

import cn.iamding.drpc.server.service.CloudDiskServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private CloudDiskServer server;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Spring启动时单开一条线程启动grpc服务端接受客户端的调用请求
        new Thread(() -> {
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}