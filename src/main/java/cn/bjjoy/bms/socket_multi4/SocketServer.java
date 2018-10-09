package cn.bjjoy.bms.socket_multi4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketServer {

	private static final int port = 9991;
	
	private static Map<String , Socket> clientMap = new ConcurrentHashMap<>();
	
	private static List<Socket> clientList = new ArrayList<>();
	
    static InputStream is;
    static OutputStream os;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(port);
			while(true){
			
				Socket client = server.accept();
				String clientIP = client.getInetAddress().getHostAddress() ;
				
				clientList.add(client);
				System.out.println(clientList.size());
//				clientMap.put(clientIP, client);
//				System.out.println(clientMap.size());
				
				while(true) {
					BufferedReader bReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
					String nameString = bReader.readLine();
					System.out.println(nameString);
				}
//				while((info = bReader.readLine()) != null){
//				}
//				OutputStream os = client.getOutputStream();
//				DataOutputStream out = new DataOutputStream(os);
//				out.write("111".getBytes());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
