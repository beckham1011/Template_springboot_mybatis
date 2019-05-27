package cn.bjjoy.bms.setting.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.SystemService;
import cn.bjjoy.bms.setting.service.impl.EquipdataServiceImpl;
import cn.bjjoy.bms.setting.service.impl.SystemServiceImpl;
import cn.bjjoy.bms.util.SpringSocketUtil;

/**  单例模式：有延迟加载的作用?
 *	 有缓存作用，而且缓存内容不变
 */
public class SingletonSocket {

	private static final Logger logger = LogManager.getLogger();
	
	private volatile static SingletonSocket instance = null ;

	private Socket socket = null ;
	
	private Socket socket8082 = null ;
	private Socket socket8084 = null ;
	
	SystemService systemService  = SpringSocketUtil.getBean(SystemServiceImpl.class) ;
	
	static Map<Integer, Socket> socketMap = new HashMap<>();
	
	private SingletonSocket(){
		try {
			socket8082 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8082_PORT) ;
			socket8084 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8084_PORT) ;
			
			logger.info("Init socket finished....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单例socket实例化失败：", e);
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
	
	public Socket getSocketBySystemId(Integer systemId) throws UnknownHostException, IOException {
		
		int port = systemService.getPort(systemId);
		
		if(socketMap.containsKey(port)) {
			return socketMap.get(port);
		} else {
			socket = new Socket(Constants.SOCKET_SERVER_IP , port);
			socketMap.put(port, socket);
			return socket;
		}
	}
	
}
