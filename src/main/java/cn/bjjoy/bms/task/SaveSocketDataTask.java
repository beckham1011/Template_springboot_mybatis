package cn.bjjoy.bms.task;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.exception.ServiceException;
import cn.bjjoy.bms.setting.persist.model.Equipdata;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.socket_multi_nio.NIOSServer8082;
import cn.bjjoy.bms.util.SpringSocketUtil;

@Component
public class SaveSocketDataTask {

	@Scheduled(cron = "*/20 * * * * ?")
	public void importData(){
		//上次机器未读完，异常退出，下次度也会是重复数据
		List<String> datas = SpringSocketUtil.readFileByLines(Constants.FILE_PATH);
		for(String data : datas){
			dataService.saveReadData(data) ;
		}
	}
	
	
	// cnn.Open "provider=SQLOLEDB;Password=123ABCabc;
	// Persist security Info=True;User ID=sa;Initial Catalog=EDM;Data Source=101.132.126.72"
	@Autowired
	EquipdataService dataService ;
	
	Logger logger = LoggerFactory.getLogger(NIOSServer8082.class) ;
	
	public void save(String data){
	
		String[] datas = data.split("  ") ;
		
		Equipdata t = new Equipdata();
		String[] values = SpringSocketUtil.parse8082SocketData(datas[0]);
		logger.info("values: " + values[0] + " , " + values[1] + " , " + values[2] + " , " + values[3]);
		
		t.setAddressCode(datas[1]);
		t.setNetCumulative(new BigDecimal(values[1]));
		t.setAreCumulative(new BigDecimal(values[2]));
		t.setFlowRate(new BigDecimal(values[3]).compareTo(new BigDecimal(10 ^ 7)) > 0 ? new BigDecimal(0.0) : new BigDecimal(values[3]));
		t.setAddTime(datas[2]);
		try {
			dataService.save(t);
			logger.info("save data.........." + t.toString());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("Save equip data: " + t.toString());
	}
	
	
}
