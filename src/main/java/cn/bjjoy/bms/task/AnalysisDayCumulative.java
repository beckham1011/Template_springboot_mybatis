package cn.bjjoy.bms.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.util.DateUtils;

@Component
public class AnalysisDayCumulative {

	Lock lock = new ReentrantLock();
	
	@Autowired
	EquiptypeService typeService ;
	
	@Scheduled(cron = "20 1 * * * ?")
	public void everyDayCumulative(){
		String startDate = DateUtils.getDayStartTime(DateUtils.getPrevDay(DateUtils.getDate()));
		String endDate =DateUtils.getDayStartTime(DateUtils.getDate());
	}

//	@Test
//	public void syso() {
//		DateUtils.getDayStartTime(DateUtils.getPrevDay(DateUtils.getDate()));
//		DateUtils.getDayStartTime(DateUtils.getDate());
//		
//		
//		
//	}
	
	
}
