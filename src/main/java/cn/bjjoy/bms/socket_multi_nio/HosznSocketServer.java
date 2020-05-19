package cn.bjjoy.bms.socket_multi_nio;

import cn.bjjoy.bms.util.SpringSocketUtil;

public class HosznSocketServer extends AbstractSocketServer {

	@Override
	String[] parseCustomerEquipData(String receiveText, int port) {
		String[] values = new String[4];
		switch (port) {
			case 8082: values = SpringSocketUtil.parse8082SocketData(receiveText);
			break;
			case 8084: values = SpringSocketUtil.parse8084SocketData(receiveText);
			break;
		}
		return values;
	}
}
