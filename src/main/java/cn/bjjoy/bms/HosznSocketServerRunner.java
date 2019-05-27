package cn.bjjoy.bms;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.service.SystemService;
import cn.bjjoy.bms.socket_multi_nio.HosznSocketServer;

@Component
@Order(1)
public class HosznSocketServerRunner implements ApplicationRunner {

	private static final Logger logger = LogManager.getLogger();

	//线程池数量 CPU 内核  * 2
	private static final ExecutorService threadsPool = 
			Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Map<Integer, Integer> systemMap = SystemService.systemPortMap;
		
		for(Integer port : systemMap.values()) {
			threadsPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						new HosznSocketServer().listen(port);
					} catch (IOException e) {
						logger.error("start socket server error : " , e);
					}
				}
			});
		}

	}

}
