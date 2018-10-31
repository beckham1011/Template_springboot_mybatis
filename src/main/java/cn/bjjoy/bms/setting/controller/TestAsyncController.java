package cn.bjjoy.bms.setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestAsyncController {

	@Autowired
	ComponentAsync async ;
	
	@RequestMapping("testAsync")
	public String doTask() throws InterruptedException{
		long currentTimeMillis = System.currentTimeMillis();
		async.task1();
		async.task2();
		async.task3();
		long currentTimeMillis1 = System.currentTimeMillis();
		return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
	}

	
}
