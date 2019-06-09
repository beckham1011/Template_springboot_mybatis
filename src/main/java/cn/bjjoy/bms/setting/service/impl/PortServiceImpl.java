package cn.bjjoy.bms.setting.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.PortService;
import cn.bjjoy.bms.setting.service.SystemService;

@Service
public class PortServiceImpl implements PortService {

	/**
	 */
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	SystemService renantService;
	
	@Autowired
	EquiptypeService equipService;
	
	@Override
	public int getPort(String addressCode) {
		Equiptype equip = equipService.getEquipByAddressCode(addressCode);
		logger.info(" get port .......");
		return renantService.getPort(equip.getSystemId());
	}
	
	
}
