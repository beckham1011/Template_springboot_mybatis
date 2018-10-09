package cn.bjjoy.bms.setting.controller;

import java.io.IOException;
import java.net.Socket;

import cn.bjjoy.bms.setting.constants.Constants;

/**  单例模式：有延迟加载的作用?
 *	 有缓存作用，而且缓存内容不变
 */
public class SingletonSocket {

	private static SingletonSocket instance = null ;
	
	private Socket socket8082 = null ;
	private Socket socket8084 = null ;
	
	private SingletonSocket(){
		try {
			socket8082 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8082_PORT) ;
			socket8084 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8084_PORT) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket8082() {
		return instance.socket8082;
	}
	
	public Socket getSocket8084() {
		return instance.socket8084;
	}
	
	public static SingletonSocket getInstance(){
		if(instance == null){
			synchronized (SingletonSocket.class) {
				if(instance == null){
					instance = new SingletonSocket();
				}
			}
		}
		return instance ;
	}
	
}