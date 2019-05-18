package cn.bjjoy.bms;

import java.net.Socket;

import cn.bjjoy.bms.setting.constants.Constants;

public enum SingletonPatternEnum {

	INSTANCE ;
	
	private static Socket socket8082 ;
	private static Socket socket8084 ;
	
	static{
		try {
			socket8082 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8082_PORT) ;
			socket8084 = new Socket(Constants.SOCKET_SERVER_IP , Constants.SOCKET_8084_PORT) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Socket getSocket8082() {
		return socket8082;
	}

	public static void setSocket8082(Socket socket8082) {
		SingletonPatternEnum.socket8082 = socket8082;
	}

	public static Socket getSocket8084() {
		return socket8084;
	}

	public static void setSocket8084(Socket socket8084) {
		SingletonPatternEnum.socket8084 = socket8084;
	}
	
	
}
