package cn.bjjoy.bms.dateutil;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bjjoy.bms.util.DateUtils;

public class DateUtilTest {

	private  final Logger logger = LoggerFactory.getLogger(DateUtilTest.class);
	
	@Test
	public void tet(){
		Date d = new Date();
		Date d2 = new Date(d.getTime() - (30 * 24 * 3600 * 1000L));
		List<String> days = DateUtils.getDaysList(d2, d);
		for(String ds : days){
			logger.info(ds);
		}
	}

	@Test
	public void testDays(){
		List<String> days = DateUtils.getDaysList(DateUtils.addDays(new Date(), -120) , new Date()); 
		for(String day : days)
			System.out.println(day);
	}
	
}

