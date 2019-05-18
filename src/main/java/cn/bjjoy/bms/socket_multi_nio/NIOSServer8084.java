package cn.bjjoy.bms.socket_multi_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.socket.ByteUtil;
import cn.bjjoy.bms.util.IPUtil;
import cn.bjjoy.bms.util.SpringSocketUtil;
import cn.bjjoy.bms.util.StringUtils;

// https://blog.csdn.net/tang9140/article/details/39052877?locationNum=10&fps=1
public class NIOSServer8084 extends AbstractSocketServer {

	private static final Logger logger = LogManager.getLogger();

	private Integer port;

	private static Selector selector;

	private static final String FRESH_STATION_REQUEST_CODE = Constants.MSG_8084;
	
	ByteBuffer rBuffer = ByteBuffer.allocate(1024);

	public NIOSServer8084(int port) {
		this.port = port;
		try {
			init();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	void init() throws IOException {
		/**
		 * 启动服务器端，配置为非阻塞，绑定端口，注册accept事件 ACCEPT事件：当服务端收到客户端连接请求时，触发该事件
		 */
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		logger.info("server start on port:" + port);

		/** 初始化ip和泵站地址关系 */
		if (ipAddressCodeMap.size() == 0) {
			List<Equiptype> types = typeService.queryList(new HashMap<>());
			for (Equiptype type : types) {
				if (type.getIP() != null)
					ipAddressCodeMap.put(type.getIP(), type.getAddressCode());
				if (null != type.getAddressCode() || !"".equals(type.getAddressCode())) {
					addressCodeIpMap.put(type.getAddressCode(), type.getIP());
				}
			}
		}
	}

	/** 服务器端轮询监听，select方法会一直阻塞直到有相关事件发生或超时 */
	public void listen() {
		for (;;) {
			try {
				selector.select();// 返回值为本次触发的事件数
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				for (SelectionKey key : selectionKeys) {
					try {
						handle(key);
					} catch (Exception e) {
						logger.info("Socket 8082 client message");
					}
				}
				selectionKeys.clear();// 清除处理过的事件
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	/** 处理不同的事件 */
	private void handle(SelectionKey selectionKey) throws IOException {
    	
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText = null;
        Socket socket = null ;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            /** 客户端请求连接事件 
             *  serversocket为该客户端建立socket连接，将此socket注册READ事件，监听客户端输入 
             *  READ事件：当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件  */
            server = (ServerSocketChannel)selectionKey.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            socket = client.socket();
            String name = socket.getInetAddress().toString().substring(1) ;
            logger.info("Client Name: " + name + "client connect");
            clientsMap.put(name, client);
        } else if (selectionKey.isReadable()) {
        	
            /** READ事件，收到客户端发送数据，读取数据后继续注册监听客户端  */
            client = (SocketChannel)selectionKey.channel();
            socket = client.socket();
            String ip = socket.getInetAddress().toString().substring(1) ;
            
        	rBuffer.clear();
            try {
				count = client.read(rBuffer);
			} catch (Exception e1) {
				clientsMap.remove(ip) ;
				client.close();
				e1.printStackTrace();
			}
        	
        	if (count > 0) {
                rBuffer.flip();
                byte[] dataBytes = rBuffer.array();
                receiveText = new String( dataBytes , 0 , count);
				logger.info("ISIP-------------client IP:" + socket.getInetAddress().getHostAddress() + ", receiveText : " + receiveText);                
				if(Constants.All_MSG.equalsIgnoreCase(receiveText)){
					//发送给所有客户端，要数据
					refreshAllStation(client, FRESH_STATION_REQUEST_CODE);
				} else if(IPUtil.isIPv4(receiveText)){
					//给特定的站点发送发送即时数据请求
					logger.info("receiveText:" + receiveText + "Refresh Special Station---------");
					refreshStation(clientsMap.get(receiveText) , FRESH_STATION_REQUEST_CODE);
				} else {
					
					if(receiveText.length() <= 25){
						updateStationHeartBeat(ip , receiveText) ;
					}else{
						String receiveTextCode = StringUtils.trim(ByteUtil.toHexString1(dataBytes));
						saveData(receiveTextCode, ip);
					}
				}
        	}else if(count == -1){
                String name = socket.getInetAddress().toString().substring(1)  ;
                logger.info(ip + " is not connect ,will close , clientsMap.size: " + clientsMap.size());
        		clientsMap.remove(name) ;
        		client.close();
        	}
        }
    }

	public String[] parseCustomerEquipData(String source) {
		String[] values = new String[4];
		try {
			String address = SpringSocketUtil.subStringByIndex(source, 1, Constants.LENGTH_RAW);
			String forwardCollection = SpringSocketUtil.subStringByIndex(source, 21, Constants.LENGTH_MORE);
			String backwardCollection = SpringSocketUtil.subStringByIndex(source, 81, Constants.LENGTH_MORE);
			String flowRate = SpringSocketUtil.subStringByIndex(source, 9, 11);
			values[0] = SpringSocketUtil.convertToHexLong(address);
			values[1] = SpringSocketUtil.convertToHexLong(forwardCollection);
			values[2] = SpringSocketUtil.convertToHexLong(backwardCollection);
			values[3] = SpringSocketUtil.convertToHexLong(flowRate);
		} catch (Exception e) {
			logger.error("ParseCustomerEquipData:" + e);
		}
		return values;
	}

}
