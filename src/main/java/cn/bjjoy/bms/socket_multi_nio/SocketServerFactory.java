package cn.bjjoy.bms.socket_multi_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**启动服务器端，配置为非阻塞，绑定端口，注册accept事件 
 * ACCEPT事件：当服务端收到客户端连接请求时，触发该事件 */
public class SocketServerFactory {

	private static final Map<Integer, Selector> serverSocketMap = new ConcurrentHashMap<>();
	
	public static synchronized void putServerSocketChannel(int port) throws IOException {
		
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		serverSocketMap.put(port, selector);
	}
	
	public static boolean existServerByPort(Integer port) {
		return serverSocketMap.containsKey(port);
	}
	
	public static Selector getSelector(Integer port) throws IOException {
		if(!existServerByPort(port))
			putServerSocketChannel(port);
			
		return serverSocketMap.get(port);
	}
	
	
}
