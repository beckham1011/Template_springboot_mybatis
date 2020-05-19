package cn.bjjoy.bms.setting.service;

import cn.bjjoy.bms.setting.entity.QuartzEntity;

public interface QuartzService {

	void addSchedulerTask(QuartzEntity quartz);
	
}
