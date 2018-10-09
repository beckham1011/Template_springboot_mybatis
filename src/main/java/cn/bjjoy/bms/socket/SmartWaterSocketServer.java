package cn.bjjoy.bms.socket;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.persist.model.Equipdata;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.impl.EquipdataServiceImpl;
import cn.bjjoy.bms.util.IPUtil;
import cn.bjjoy.bms.util.SpringSocketUtil;
 
// https://blog.csdn.net/tang9140/article/details/39052877?locationNum=10&fps=1
public class SmartWaterSocketServer {
	
	private DateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	
	private String MSG_TO_PUMP  ;
	
    private Integer port ;
    
    private EquipdataService dataService = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    private EquiptypeService typeService = SpringSocketUtil.getBean(EquiptypeService.class) ;


	//解码buffer  
    private Charset cs = Charset.forName("utf-8");
    
    /*接受数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
    /*发送数据缓冲区*/
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    
    /*映射客户端channel */
    private Map<String, SocketChannel> clientsMap = new HashMap<String, SocketChannel>();
    
    private static Selector selector;
    
    
    public SmartWaterSocketServer(int port) {
    	
        this.port = port;
        try {
            init();
            MSG_TO_PUMP = (port == 8082) ? Constants.MSG_8082 : Constants.MSG_8084;
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
    	//加入线程的目的是，当有多个服务启动时，避免相互之间阻塞
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
    	System.out.println("Socket Listen " + port + "...");
    }
    
    /** 
     * 处理不同的事件 
    */
    private void handle(SelectionKey selectionKey) throws IOException {
    	
    	EquipdataService dataService = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    	
    	System.out.println(dataService.toString());
    	
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
        } else if (selectionKey.isReadable()) {
        	System.out.println("client send msg");
            /* 
             * READ事件，收到客户端发送数据，读取数据后继续注册监听客户端 
             */
            client = (SocketChannel)selectionKey.channel();
            
            rBuffer.clear();
            count = client.read(rBuffer);
            if (count > 0) {
                rBuffer.flip();
                receiveText = String.valueOf(cs.decode(rBuffer).array());
                try {
					if(Constants.All_MSG.equalsIgnoreCase(receiveText)){
						System.out.println("All--");
						
						//发送给所有客户端，要数据
//						dispatch(client, MSG_TO_PUMP);
//						client = (SocketChannel)selectionKey.channel();
//						client.register(selector, SelectionKey.OP_READ);
					} else if(IPUtil.isIPv4(receiveText)){
						
						System.out.println("IP---------");
//						if(client.socket().getInetAddress().getHostAddress().equals(receiveText)){
//							dispatch(client, MSG_TO_PUMP);
//						}else{
//							return ;
//						}
					} else {
						//处理泵站发来数据
						System.out.println(ByteUtil.binaryToHexString(receiveText.getBytes()));
						if(receiveText.length() <= 25){
							System.out.println("getAddressCode");
//							getAddressCode(client , receiveText) ;
						}else{
							System.out.println("saveData");
//							saveData(receiveText ) ;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
    }

    private void saveData(String receiveText) {
    	Equipdata t = new Equipdata();
    	String[] values = parseSocketData(receiveText);
    	t.setAddPer(values[0]);
    	t.setAreCumulative(new BigDecimal(values[2]));
    	t.setNetCumulative((new BigDecimal(values[1]).compareTo(BigDecimal.ZERO) >= 0) ? new BigDecimal(values[1]).add(new BigDecimal(65535)) : new BigDecimal(values[1]));
    	t.setFlowRate(new BigDecimal(values[3]));
    	t.setAddTime(sdf.format(new Date()));
    	t.setAddressCode("");
    	t.setAddPer("");
    	dataService.save(t);
	}

	 public static String[] parseSocketData(String source){
		 String[] values = new String[4];
		  try {
			String address = SpringSocketUtil.subStringByIndex(source , 1 , Constants.LENGTH_RAW) ;
			String forwardCollection = SpringSocketUtil.subStringByIndex(source , 57 , Constants.LENGTH_MORE) ;
			String backwardCollection = SpringSocketUtil.subStringByIndex(source , 81 , Constants.LENGTH_MORE) ;
			String flowRate = SpringSocketUtil.subStringByIndex(source , 9 , 9 + 12) ;
			values[0] = SpringSocketUtil.convertToHexLong(address) ;
			values[1] = SpringSocketUtil.convertToHexLong(forwardCollection) ;
			values[2] = SpringSocketUtil.convertToHexLong(backwardCollection) ;
			values[3] = SpringSocketUtil.convertToHexLong(flowRate) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return values ;
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
        clientsMap.put(name, client);
    }
    
    public static void main(String[] args) throws IOException {
        SmartWaterSocketServer server = new SmartWaterSocketServer(8082);
        server.listen();
    }


	public void getAddressCode (SocketChannel client ,String source){
		Socket s = client.socket();
		StringBuffer addressCode = new StringBuffer();
		for(String str : source.split(" ")){
			Integer x = Integer.parseInt(str,16);
			addressCode.append(SpringSocketUtil.byteAsciiToChar( x ));
		}
		String ip = s.getInetAddress().toString().substring(1);
		
		Map<String,Object> params = new HashMap<>();
		params.put("addressCode", addressCode.toString());
		params.put("ip", ip);
		System.out.println(addressCode.toString());
		dataService.update(params) ;
	}
	
    
}
