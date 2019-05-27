//package cn.bjjoy.bms.quartz2;
//
//import org.junit.jupiter.api.Test;
//
//public class TestTask {
//
//	@Test
//	public void testTask() {
//		try {
//			String job_name = "动态任务调度";
//			System.out.println("【系统启动】开始(每1秒输出一次)...");
//			JobSchedule.addJob(job_name, DemoJob.class, "0/1 * * * * ?");
//
//			Thread.sleep(5000);
//			System.out.println("【修改时间】开始(每2秒输出一次)...");
//			JobSchedule.modifyJobTime(job_name, "10/2 * * * * ?");
//			Thread.sleep(6000);
//			System.out.println("【移除定时】开始...");
//			JobSchedule.removeJob(job_name);
//			System.out.println("【移除定时】成功");
//
//			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
//			JobSchedule.addJob(job_name, HelloJob.class, "*/10 * * * * ?");
//			Thread.sleep(60000);
//			System.out.println("【移除定时】开始...");
//			JobSchedule.removeJob(job_name);
//			System.out.println("【移除定时】成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
