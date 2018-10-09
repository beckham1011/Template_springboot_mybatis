package cn.bjjoy.bms.socket_multi2;

import cn.bjjoy.bms.socket_multi2.Client.TestClient;

public class Test {

	public static void main(String[] args) {
		try {
			TestClient client = Client.TestClientFactory.createClient();
			client.send(String.format("Hello,Server!I'm %d.简历服务端。", client.client.getLocalPort()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
