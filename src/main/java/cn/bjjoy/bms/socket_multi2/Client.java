package cn.bjjoy.bms.socket_multi2;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TestClient client = TestClientFactory.createClient();
					client.send(String.format("Hello,Server!I'm %d.这周末天气如何。", client.client.getLocalPort()));
					client.receive();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	static class TestClientFactory {         
		public static TestClient createClient() throws Exception {            
			return new TestClient("127.0.0.1", 8899);        
		}
	}

	
	static class TestClient {
		public Socket client;
		 
		public Writer writer;

        public TestClient(String host, int port) throws Exception {
        	// 与服务端建立连接            
        	this.client = new Socket(host, port);
        	System.out.println("Cliect[port:" + client.getLocalPort() + "] 与服务端建立连接...");        
        }

        
        public void send(String msg) throws Exception{
        	if(writer == null) {
                writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
            }
        	writer.write(msg);
        	writer.write("eof\n");
        	writer.flush();// 写完后要记得flush
        	System.out.println("Cliect[port:" + client.getLocalPort() + "] 消息发送成功");
        }
        
        
        public void receive() throws Exception {
        	// 写完以后进行读操作
        	Reader reader = new InputStreamReader(client.getInputStream(), "UTF-8");
        	client.setSoTimeout(10*1000);
        	char[] chars = new char[64];
        	int len;
        	StringBuilder sb = new StringBuilder();
        	while ((len = reader.read(chars)) != -1) {
        		sb.append(new String(chars, 0, len));
    		}
        	System.out.println("Cliect[port:" + client.getLocalPort() + "] 消息收到了，内容:" + sb.toString());            
//        	reader.close();             
        	
        	// 关闭连接            
//        	writer.close();            
//        	client.close();
        }
	}
	
}
