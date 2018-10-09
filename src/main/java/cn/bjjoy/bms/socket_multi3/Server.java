package cn.bjjoy.bms.socket_multi3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ArrayList<Socket> list = new ArrayList<Socket>();
    
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = new ServerSocket(8888);
        
        while(true) {
            //如果队列中没有等待的连接，套接字也没有被标记为Non-blocking，accept()会阻塞调用函数直到连接出现
            Socket socket = server.accept();
            
            //将连接的socket放到一个list集合
            list.add(socket);
            new ServerThread(socket,list).start();
            
        }
    }

	
	public void getAllIpData(){

		try {
			ServerSocket server = new ServerSocket(8888);
			
			while(true) {
			    //如果队列中没有等待的连接，套接字也没有被标记为Non-blocking，accept()会阻塞调用函数直到连接出现
			    Socket socket = server.accept();
			    
			    //将连接的socket放到一个list集合
			    list.add(socket);
			    new ServerThread(socket,list).start();
			    
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getSpecialIPData(String specialIP){

		try {
			ServerSocket server = new ServerSocket(8888);
			
			while(true) {
			    //如果队列中没有等待的连接，套接字也没有被标记为Non-blocking，accept()会阻塞调用函数直到连接出现
			    Socket socket = server.accept();
			    
			    //将连接的socket放到一个list集合
			    list.add(socket);
			    new ServerThread(socket,list  , specialIP).start();
			    
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
