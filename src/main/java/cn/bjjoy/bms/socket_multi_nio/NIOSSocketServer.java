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
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import cn.bjjoy.bms.setting.constants.Constants;

// https://blog.csdn.net/tang9140/article/details/39052877?locationNum=10&fps=1
public class NIOSSocketServer {

	
    public static void main(String[] args) {
    	NIOSSocketServer server = new NIOSSocketServer(8087) ;
    	server.listen();
	}
	
	private static final String All_MSG = "All" ;
	
    private Integer port ;
    
	//解码buffer  
    private Charset cs = Charset.forName("utf-8");
    
    /*接受数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
    /*发送数据缓冲区*/
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    
    /*映射客户端channel */
    private Map<String, SocketChannel> clientsMap = new HashMap<String, SocketChannel>();
    
    private static Selector selector;
    
    
    public NIOSSocketServer(int port) {
        this.port = port;
        try {
            init();
            
//            server.listen();
//            sendMsgFromServer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void init() throws IOException {
        /* 
         *启动服务器端，配置为非阻塞，绑定端口，注册accept事件 
         *ACCEPT事件：当服务端收到客户端连接请求时，触发该事件 
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start on port:" + port);
    }
    
    /** 
     * 服务器端轮询监听，select方法会一直阻塞直到有相关事件发生或超时 
     */
    public void listen() {
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
			   	for(;;) {
		            try {
		                selector.select();//返回值为本次触发的事件数  
		                Set<SelectionKey> selectionKeys = selector.selectedKeys();
		                for (SelectionKey key : selectionKeys) {
		                    handle(key);
		                }
		                selectionKeys.clear();//清除处理过的事件  
		            }
		            catch (Exception e) {
		                e.printStackTrace();
		                break;
		            }
		        }
			}
		}).start();
    	System.out.println("Listen" + port + "...");
 
    }
    
    /** 
     * 处理不同的事件 
    */
    private void handle(SelectionKey selectionKey) throws IOException {
    	
//    	EquipdataService dataService = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    	
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText = null;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            /* 
             * 客户端请求连接事件 
             * serversocket为该客户端建立socket连接，将此socket注册READ事件，监听客户端输入 
             * READ事件：当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件 
             */
            server = (ServerSocketChannel)selectionKey.channel();
            client = server.accept();
            client.getRemoteAddress();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            
            System.out.println("client connect");
            String name = "[" + client.socket().getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
            clientsMap.put(name, client);
        } else if (selectionKey.isReadable()) {
        	System.out.println("client send msg");
            /* 
             * READ事件，收到客户端发送数据，读取数据后继续注册监听客户端 
             */
            client = (SocketChannel)selectionKey.channel();
            rBuffer.clear();
            try {
				count = client.read(rBuffer);
			} catch (Exception e1) {
				String name = "[" + client.socket().getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
				clientsMap.remove(name) ;
				client.close();
				e1.printStackTrace();
			}
            
            if (count > 0) {
                rBuffer.flip();
                receiveText = String.valueOf(cs.decode(rBuffer).array());
                try {
					if(All_MSG.equalsIgnoreCase(receiveText)){
						dispatch(client, Constants.MSG_8082);
						System.out.println(receiveText);
						//发送给所有客户端，要数据
//                	System.out.println(msgs.size());
//                	client = (SocketChannel)selectionKey.channel();
						//client.register(selector, SelectionKey.OP_READ);
						
					} else if(receiveText.indexOf("ip") > -1){
						
					} else if(receiveText.length() <= 25){
						System.out.println("getAddressCode");
						getAddressCode(client , receiveText) ;
					}else{
						System.out.println("saveData");
						saveData(receiveText) ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
    }

    private void saveData(String receiveText) {
    	System.out.println(receiveText);
	}

    
	/** 
     * 把当前客户端信息 推送到其他客户端 
     */
    private void dispatch(SocketChannel client, String info) throws IOException {
        Socket s = client.socket();
        String name = "[" + s.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
        if (!clientsMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()) {
                SocketChannel temp = entry.getValue();
                if (!client.equals(temp)) {
                    sBuffer.clear();
                    sBuffer.put(info.getBytes());
                    sBuffer.flip();
                    //输出到通道  
                    temp.write(sBuffer);
                }
            }
        }
    }
    
	public void getAddressCode (SocketChannel client ,String source){
		Socket s = client.socket();
		StringBuffer addressCode = new StringBuffer();
		for(String str : source.split(" ")){
			Integer x = Integer.parseInt(str,16);
			addressCode.append(byteAsciiToChar( x ));
		}
		String ip = s.getInetAddress().toString().substring(1);
		System.out.println("IP:" + ip);
	}

	public static char byteAsciiToChar(int ascii){
		char ch = (char)ascii;
		return ch;
	}
	
    public void sendMsgFromServer(){
    	Scanner scanner = new Scanner(System.in) ;
    	try {
			while(true){
				String info = scanner.next() ;
				while((info = scanner.next()) != null){
					for (Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()) {
					SocketChannel temp = entry.getValue();
						sBuffer.clear();
						sBuffer.put(info.getBytes());
						sBuffer.flip();
						//输出到通道  
						temp.write(sBuffer);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


}
