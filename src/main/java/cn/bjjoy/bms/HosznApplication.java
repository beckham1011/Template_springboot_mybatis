package cn.bjjoy.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bjjoy.bms.setting.controller.SingletonSocket;
import cn.bjjoy.bms.socket_multi_nio.HosznServer8082;
import cn.bjjoy.bms.socket_multi_nio.HosznServer8084;


@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class BmsApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(BmsApplication.class, args);
		
		int[] ports = {8082,8084};
		
		new Thread(new Runnable() {
			public void run() {
				try {
					new HosznServer8084().listen(ports[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					new HosznServer8082().listen(ports[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		//初始化给前端页面调用的client
		SingletonSocket.getInstance() ;
	}
}
