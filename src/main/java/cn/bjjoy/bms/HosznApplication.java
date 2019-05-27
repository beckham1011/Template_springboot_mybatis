package cn.bjjoy.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bjjoy.bms.setting.controller.SingletonSocket;


@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class HosznApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(HosznApplication.class, args);
		
		//初始化给前端页面调用的client
		SingletonSocket.getInstance() ;
	}
}
