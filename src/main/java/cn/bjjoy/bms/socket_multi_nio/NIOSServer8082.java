package cn.bjjoy.bms.socket_multi_nio;

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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bjjoy.bms.mail.SendMail;
import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.exception.ServiceException;
import cn.bjjoy.bms.setting.persist.model.Equipdata;
import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.impl.EquipdataServiceImpl;
import cn.bjjoy.bms.setting.service.impl.EquiptypeServiceImpl;
//import cn.bjjoy.bms.setting.service.EquipdataService;
//import cn.bjjoy.bms.setting.service.EquiptypeService;
//import cn.bjjoy.bms.setting.service.impl.EquipdataServiceImpl;
import cn.bjjoy.bms.socket.ByteUtil;
import cn.bjjoy.bms.util.DateUtils;
import cn.bjjoy.bms.util.IPUtil;
import cn.bjjoy.bms.util.SpringSocketUtil;

import com.google.common.collect.Lists;

// https://blog.csdn.net/tang9140/article/details/39052877?locationNum=10&fps=1
public class NIOSServer8082 {
	
	Logger logger = LoggerFactory.getLogger(NIOSServer8082.class) ;
	
	Map<String , String> ipAddressCodeMap = new HashMap<>();
	Map<String , String> addressCodeIpMap = new HashMap<>();
	
    private Integer port ;
    
    private EquipdataService dataService  = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    private EquiptypeService typeService  = SpringSocketUtil.getBean(EquiptypeServiceImpl.class) ;

	//解码buffer  
    private Charset cs = Charset.forName("utf8");
    
    /*接受数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
    /*发送数据缓冲区*/
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    
    /*映射客户端channel */
    private Map<String, SocketChannel> clientsMap = new LinkedHashMap<>(200);
    
    private static Selector selector;
    
    public NIOSServer8082(int port) {
        this.port = port;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void init() throws IOException {
        /**启动服务器端，配置为非阻塞，绑定端口，注册accept事件 
         * ACCEPT事件：当服务端收到客户端连接请求时，触发该事件 */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("server start on port:" + port);
        
        /** 初始化ip和泵站地址关系 */
        if(ipAddressCodeMap.size() == 0){
        	List<Equiptype> types = typeService.queryList(new HashMap<>());
        	for(Equiptype type : types){
        		if(type.getIP() != null)
        			ipAddressCodeMap.put(type.getIP() , type.getAddressCode() );
        		if( null!= type.getAddressCode() || !"".equals(type.getAddressCode() )){
        			addressCodeIpMap.put(type.getAddressCode(), type.getIP());
        		}
        	}
        }
    }
    
    /** 服务器端轮询监听，select方法会一直阻塞直到有相关事件发生或超时  */
    public void listen() {
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
			   	for(;;) {
		            try {
		                selector.select();//返回值为本次触发的事件数  
		                Set<SelectionKey> selectionKeys = selector.selectedKeys();
		                for (SelectionKey key : selectionKeys) {
		                	try {
		                		handle(key);
							} catch (Exception e) {
								logger.info("Socket 8082 client message");
							}
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
    	logger.info("Listen8082...");
 
    }
    
    /** 处理不同的事件  */
    private void handle(SelectionKey selectionKey) throws IOException {
    	
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText = null;
        Socket s = null ;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            /** 客户端请求连接事件 
             *  serversocket为该客户端建立socket连接，将此socket注册READ事件，监听客户端输入 
             *  READ事件：当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件 
             */
            server = (ServerSocketChannel)selectionKey.channel();
            client = server.accept();
            client.getRemoteAddress();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            s = client.socket();
            String name = s.getInetAddress().toString().substring(1) ;
            logger.info("Client Name: " + name + "client connect");
            clientsMap.put(name, client);
        } else if (selectionKey.isReadable()) {
            /* 
             * READ事件，收到客户端发送数据，读取数据后继续注册监听客户端 
             */
            client = (SocketChannel)selectionKey.channel();
            s = client.socket();
            String ip = s.getInetAddress().toString().substring(1) ;
            
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
                receiveText = new String( rBuffer.array(),0,count);
                try {
					if(Constants.All_MSG.equalsIgnoreCase(receiveText)){
						logger.info("ISIP-------------client IP:" + s.getInetAddress().getHostAddress() + ", receiveText : " + receiveText);
						//发送给所有客户端，要数据
						refreshAllStation(client,Constants.MSG_8082);
					} else if(IPUtil.isIPv4(receiveText)){
						//给特定的站点发送发送即时数据请求
						logger.info("receiveText:" + receiveText + "Refresh Special Station---------");
						refreshSpecialStation(clientsMap.get(receiveText) , Constants.MSG_8082);
					} else {
						String receiveTextCode = ByteUtil.binaryToHexString(receiveText.getBytes());
						if(receiveText.length() <= 25){
							logger.info("receiveTextCode:" + receiveTextCode);
							getAddressCode(client , receiveTextCode) ;
						}else{
							receiveTextCode = ByteUtil.binaryToHexString(receiveText.getBytes());
							logger.info("Raw Data from equip receiveTextCode:" + receiveTextCode );
							saveData(receiveTextCode ,ip ) ;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            
        	}else if(count == -1){
        		client = (SocketChannel)selectionKey.channel();
                s = client.socket();
                String name = s.getInetAddress().toString().substring(1)  ;
                logger.info(ip + " is not connect ,will close , clientsMap.size: " + clientsMap.size());
        		clientsMap.remove(name) ;
        		client.close();
        	}
        }
    }


    //从报文种解析泵站编码地址
	public void getAddressCode (SocketChannel client ,String source){
		Socket s = client.socket();
		StringBuffer addressCode = new StringBuffer();
		for(String str : source.split(" ")){
			Integer x = Integer.parseInt(str,16);
			addressCode.append(SpringSocketUtil.byteAsciiToChar( x ));
		}
		String ip = s.getInetAddress().toString().substring(1);
		
		String addCode = addressCode.toString();
		Map<String,Object> params = new HashMap<>();
		params.put("addressCode", addCode);

		ipAddressCodeMap.put(ip, addCode) ;
		addressCodeIpMap.put(addCode, ip) ;
		
		params.put("ip", ip);
		if(typeService.queryList(params).size() > 0){
			logger.info("Equip type update : IP " + ip + "  , addresscode :" + addCode);
			try {
				typeService.updateByAddressCode(params);
			} catch (Exception e) {
				logger.error("Equiptype Update 失败",e);
			}
		}else{
			try {
				logger.info("Equip type create : IP " + ip + "  , addresscode :" + addCode);
				Equiptype t = new Equiptype ();
				t.setIP(ip);
				t.setAddressCode(addCode);
				t.setAddTime(DateUtils.getCurrentDate());
				typeService.save(t);
			} catch (ServiceException e) {
				e.printStackTrace();
				logger.error("保存失败",e);
			}
			
			if(addCode != null || !"".equals(addCode)){
				if( addCode.startsWith("hsg") || addCode.startsWith("") || addCode.startsWith("")){
					try {
						//新添加的泵站需要数据维护，发送邮件通知
						sendMailToAdmin(addCode);
						logger.info("邮件发送成功" + addCode );
					} catch (Exception e) {
						logger.error("邮件发送失败" + addCode,e);
					}
				}
			}
		}
	}

	private SendMail sendMail = new SendMail();
	//新添加的泵站需要数据维护，发送邮件通知,异步
	public void sendMailToAdmin(String addressCode){
		//新添加的泵站需要数据维护，发送邮件通知
		String content = "新增加了泵站。地址编码是： " + addressCode + "，需要维护" ;
		List<String> receivers = Lists.newArrayList();
		receivers.add(Constants.MAIL_RECEIVER_ADMIN) ;
		sendMail.sendEmail("新增加了泵站,请维护泵站信息", content, receivers, null);
	}
	
	
	//从socket获取的ip，根据ip获取泵站编码地址
    private String getAddressCode(String ip){
//    	return typeService.getAddressCodeByIP(ip);
    	return ipAddressCodeMap.get(ip) ;
    }
    
    
    private void saveData(String receiveText , String ip) {
    	Equipdata t = new Equipdata();
    	String[] values = SpringSocketUtil.parse8082SocketData(receiveText);
    	logger.info("ip:" + ip + ",receiveTextCode:" + receiveText);
    	logger.info("values: " + values[0] + " , " + values[1] + " , " + values[2] + " , " + values[3]);
    	
    	if(getAddressCode(ip) != null){
    		t.setAddressCode(getAddressCode(ip));
    		t.setNetCumulative(new BigDecimal(values[1]));
    		t.setAreCumulative(new BigDecimal(values[2]));
    		t.setFlowRate(new BigDecimal(values[3]).compareTo(new BigDecimal(10 ^ 7)) > 0 ? new BigDecimal(0.0) : new BigDecimal(values[3]));
    		t.setAddTime(DateUtils.getCurrentDate());
    		try {
				dataService.save(t);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
    	}
    	logger.info("Save equip data: " + t.toString() + " , IP:" + ip);
	}

    
	/** 
     * 把当前客户端信息 推送到其他客户端 
     */
    private void refreshAllStation(SocketChannel client, String info) throws IOException {
        if (!clientsMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()) {
                try {
					SocketChannel temp = entry.getValue();
					if (!client.equals(temp)) {
					    sBuffer.clear();
					    sBuffer.put(ByteUtil.hexStringToByte(info));
					    sBuffer.flip();
					    //输出到通道  
					    temp.write(sBuffer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
    }

	/** 
     * 把当前客户端信息 推送到其他客户端 
     */
    private void refreshSpecialStation(SocketChannel client, String info) throws IOException {
    	if(client != null){
    		sBuffer.clear();
    		sBuffer.put(ByteUtil.hexStringToByte(info));
    		sBuffer.flip();
    		//输出到通道  
    		client.write(sBuffer);
    	}
    }
    
}
