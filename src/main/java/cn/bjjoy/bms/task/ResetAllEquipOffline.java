package cn.bjjoy.bms.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.setting.service.EquiptypeService;

@Component
public class ResetAllEquipOffline {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	EquiptypeService equipService ;
	
	@Scheduled(cron = "00 00 00 * * ?")
	public void resetAllEquipOffline() {
		
		List<Equiptype> equips = equipService.getEquipsByParentId(1);
		equips.stream().forEach(equip ->{
			logger.info(equip.getAddressCode() + " will be reset to offline.");
			equipService.updateOnline(equip.getAddressCode(), false);
		});
		
	}
	
}
