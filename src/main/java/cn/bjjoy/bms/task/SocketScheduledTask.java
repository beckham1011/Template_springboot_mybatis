package cn.bjjoy.bms.task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.mail.SendMail;
import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.controller.SingletonSocket;
import cn.bjjoy.bms.setting.service.EquiptypeService;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;


@Component
public class SocketScheduledTask {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	SendMail sendMail ;
	
	@Autowired
	EquiptypeService typeService ;
	
	//每天凌晨12点定时刷新任务
	@Scheduled(cron = "00 00 00 * * ?")
	public void refreshAllData(){
		
		logger.info("Scheduled Task Execute....." + new Date());
		
		try {
			DataOutputStream refreshData8082Socket = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());
			refreshData8082Socket.write(Constants.All.getBytes()) ;
			
		} catch (IOException e) {
			logger.info("Scheduled Task Execute .fail....." + new Date());
			logger.error(e.getMessage() , e) ;
		}
	}
	
	
	//每周一早上九点提醒刷新泵站信息
	@Scheduled(cron = "00 00 13 * * ?")
	public void modifyStationMsg(){
		// 新添加的泵站需要数据维护，发送邮件通知
		// receivers.add("xuzj850329@126.com") ;
		String smg = null;
		try {
			List<String> addressCodes = Lists.newArrayList(typeService.getAddressCodeNull());
			if(!Objects.nonNull(addressCodes)){
				String content = "以下地址编码信息不全，需要维护" ;
				String addresses = Joiner.on(",\n").join(addressCodes);
				List<String> receivers = Lists.newArrayList();
				receivers.add(Constants.MAIL_RECEIVER_SUB_ADMIN) ;
				smg = "下列地址信息不全\n" + addresses + ",共 "+addressCodes.size()+"个 ，需要维护" + System.currentTimeMillis();
				sendMail.sendEmail(content , smg, receivers, null);
				logger.info("邮件发送成功：" + smg);
			}
		} catch (Exception e) {
			logger.error("邮件发送失败：", e);
		}
	}

	@Scheduled(cron="00 00 01 * * ?")
	public void delLog(){
		String logURL = "C:\\logger";
		try {
			Files.list(Paths.get(logURL)).forEach( p -> {
				try {
					long day = LocalDate.now().toEpochDay() - Files.getLastModifiedTime(p).to(TimeUnit.DAYS) ;
					if(day >= 5L ){
						Files.delete(p);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			logger.debug("");
		}
	}
	
}
