package cn.bjjoy.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.controller.SingletonSocket;
import cn.bjjoy.bms.socket_multi_nio.NIOSServer8082;
import cn.bjjoy.bms.socket_multi_nio.NIOSServer8082_2;


@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class BmsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BmsApplication.class, args);
		
		String s = new String("");
		
		new Thread(new Runnable() {
			public void run() {
				try {
					new NIOSServer8082(Constants.SOCKET_8082_PORT).listen() ;
					new NIOSServer8082_2(Constants.SOCKET_8084_PORT).listen() ;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		//初始化给前端页面调用的client
		SingletonSocket.getInstance() ;
	}
}
