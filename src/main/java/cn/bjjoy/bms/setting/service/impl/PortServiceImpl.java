package cn.bjjoy.bms.setting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(EquiptypeServiceImpl.class) ;

	@Autowired
	SystemService renantService;
	
	@Autowired
	EquiptypeService equipService;
	
	@Override
	public int getPort(String addressCode) {
		Equiptype equip = equipService.getEquipByAddressCode(addressCode);
		return renantService.getPort(equip.getSystemId());
	}
	
	
}
