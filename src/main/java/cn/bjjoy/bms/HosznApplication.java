package cn.bjjoy.bms;

import cn.bjjoy.bms.setting.controller.SingletonSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class HosznApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HosznApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(HosznApplication.class, args);

        //初始化给前端页面调用的client
        SingletonSocket.getInstance();
    }
}
