package cn.bjjoy.bms.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class MyScheduler {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	
	@Autowired
	public SchedulerFactoryBean schedulerFactoryBean;
	
	static Scheduler scheduler;
//
	public static Scheduler getScheduler() throws SchedulerException {
		return gSchedulerFactory.getScheduler();
	}
	
	public void scheduleJobs() throws SchedulerException {
		scheduler = schedulerFactoryBean.getScheduler();
		startJob1(); // 每5分钟执行一次
		startJob2(); // 每2分钟执行一次
	}

	public static void startJob1() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job1", "group1").build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(scheduleBuilder).build();
		gSchedulerFactory.getScheduler().scheduleJob(jobDetail, cronTrigger);
	}

	public static void modifyJob1(String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(scheduleBuilder).build();
		gSchedulerFactory.getScheduler().rescheduleJob(triggerKey, newTrigger);
	}

	public static String getJob1Status() throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
		return gSchedulerFactory.getScheduler().getTriggerState(triggerKey).name();
	}

	public static void pauseJob1() throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey("job1", "group1"));
	}

	public static void resumeJob1() throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey("job1", "group1"));
	}

	private void startJob2() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class).withIdentity("job2", "group1").build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
				.withSchedule(scheduleBuilder).build();
		gSchedulerFactory.getScheduler().scheduleJob(jobDetail, cronTrigger);
	}

	public static void modifyJob2(String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger2", "group1");
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
				.withSchedule(scheduleBuilder).build();
		gSchedulerFactory.getScheduler().rescheduleJob(triggerKey, newTrigger);
	}

	public static String getJob2Status() throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger2", "group1");
		return scheduler.getTriggerState(triggerKey).name();
	}

	public static void pauseJob2() throws SchedulerException {
		gSchedulerFactory.getScheduler().pauseJob(JobKey.jobKey("job2", "group1"));
	}

	public static void resumeJob2() throws SchedulerException {
		gSchedulerFactory.getScheduler().resumeJob(JobKey.jobKey("job2", "group1"));
	}

}
