package cn.iamding.drpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//ServletComponentScan 注解可以让@WebFilter注解的类自动注入
@ServletComponentScan
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Bootstrap.class, args);
    }

}
