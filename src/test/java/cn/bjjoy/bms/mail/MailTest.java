package cn.bjjoy.bms.mail;

import org.junit.Test;

public class MailTest {

	@Test
	public void test() {
		String addressCode = "hsg299";
		String content = String.format("新增加了泵站。地址编码是：%s，需要维护" , addressCode) ;
		
		System.out.println(content);
	}
	
}
