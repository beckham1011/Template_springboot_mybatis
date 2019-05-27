package cn.bjjoy.bms.quartz2;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

public class DemoJob implements Job {

	private static final Logger LOGGER =  LogManager.getLogger();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
            LOGGER.info(context.getScheduler().getSchedulerName());
            String jobParam = (String) context.getJobDetail().getJobDataMap().get("jobParam");
            if (jobParam != null) {
                LOGGER.info(jobParam.toString());
            }
            LOGGER.info(Integer.toString(Calendar.getInstance().get(Calendar.SECOND)));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
	}

}
