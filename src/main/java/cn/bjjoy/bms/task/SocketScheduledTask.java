package cn.bjjoy.bms.task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.controller.SingletonSocket;

@Component
public class SocketScheduledTask {

	Logger logger = LoggerFactory.getLogger(SocketScheduledTask.class) ;
	
	//每天凌晨12点定时刷新任务
	@Scheduled(cron = "20 00 00 * * ?")
	public void refreshAllData(){
		
		logger.info("Scheduled Task Execute....." + new Date());
		
		try {
			DataOutputStream refreshData8082Socket = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());
			refreshData8082Socket.write(Constants.All.getBytes()) ;
			
			DataOutputStream refreshData8084Socket = new DataOutputStream(SingletonSocket.getInstance().getSocket8084().getOutputStream());
			refreshData8084Socket.write(Constants.All.getBytes()) ;			
			
		} catch (IOException e) {
			logger.info("Scheduled Task Execute .fail....." + new Date());
			logger.error(e.getMessage() , e) ;
		}
	}
	
}
