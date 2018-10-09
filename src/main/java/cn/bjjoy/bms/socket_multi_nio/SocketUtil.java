package cn.bjjoy.bms.socket_multi_nio;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;

public class SocketUtil {

	public Socket getSocketChannel(List<SocketChannel> channels, String ip){
		Socket socket = null ;
		for(SocketChannel chnl : channels){
			if(chnl.socket().getInetAddress().equals(ip)){
				socket = chnl.socket() ;
				break ;
			}
		}
		return  socket ;
	}

	public static void main(String[] args) { 
		List<String> sprcialCourseList = Arrays.asList("606", "604", "602", "600") ;
		System.out.println(sprcialCourseList.contains("606"));
	}
	
}
