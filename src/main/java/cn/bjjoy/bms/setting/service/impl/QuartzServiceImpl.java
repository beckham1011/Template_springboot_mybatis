package cn.bjjoy.bms.setting.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.bjjoy.bms.setting.entity.QuartzEntity;
import cn.bjjoy.bms.setting.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	@Qualifier("Scheduler")
	private Scheduler scheduler;

	public QuartzEntity generateQuartzEntity() {
		return new QuartzEntity();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addSchedulerTask(QuartzEntity quartz) {
		try {
			// 获取Scheduler实例、废弃、使用自动注入的scheduler、否则spring的service将无法注入
			// Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// 如果是修改 展示旧的 任务
			if (quartz.getOldJobGroup() != null) {
				JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
				scheduler.deleteJob(key);
			}
			Class cls = Class.forName(quartz.getJobClassName());
			cls.newInstance();
			
			// 构建job信息
			JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(), quartz.getJobGroup())
						.withDescription(quartz.getDescription()).build();
			
			// 触发时间点
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
						.startNow().withSchedule(cronScheduleBuilder).build();
			
			// 交由Scheduler安排触发
			logger.info("Execute scheduler Service , trigger name:" + quartz.getTriggerName() + ", task name: " + quartz.getJobName());
			scheduler.scheduleJob(job, trigger);
		} catch (ClassNotFoundException e) {
			logger.error("Execute scheduler Service ClassNotFoundException :" + e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.error("Execute scheduler Service InstantiationException :" + e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("Execute scheduler Service IllegalAccessException :" + e);
			e.printStackTrace();
		} catch (SchedulerException e) {
			logger.error("Execute scheduler Service SchedulerException :" + e);
			e.printStackTrace();
		}
	}

}
