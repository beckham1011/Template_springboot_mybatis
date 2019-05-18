package cn.bjjoy.bms.setting.controller;

import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.bjjoy.bms.setting.constants.Constants;

/**  单例模式：有延迟加载的作用?
 *	 有缓存作用，而且缓存内容不变
 */
public class SingletonSocket {

	private static final Logger logger = LogManager.getLogger();
	
	private volatile static SingletonSocket instance = null ;
	
	private Socket socket8082 = null ;
	
	private SingletonSocket(){
		try {
			socket8082 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8082_PORT) ;
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单例socket实例化失败：", e);
		}
	}
	
	public Socket getSocket8082() {
		return instance.socket8082;
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
