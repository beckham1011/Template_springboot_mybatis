package cn.bjjoy.bms.quartz;

import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzController {

	@GetMapping("quartz1")
	public String test1() throws SchedulerException {
		MyScheduler.startJob1();
		return "quartz1";
	}
	@RequestMapping("/modify")
    public @ResponseBody String modify() throws SchedulerException {
        MyScheduler.modifyJob1("0/1 * * * * ?");
        return "1";
    }

    @RequestMapping("/status")
    public @ResponseBody String status() throws SchedulerException {
        return MyScheduler.getJob1Status();
    }

    @RequestMapping("/pause")
    public @ResponseBody String pause() throws SchedulerException {
        MyScheduler.pauseJob1();
        return "1";
    }

    @RequestMapping("/resume")
    public @ResponseBody String resume() throws SchedulerException {
        MyScheduler.resumeJob1();
        return "1";
    }
    
}
