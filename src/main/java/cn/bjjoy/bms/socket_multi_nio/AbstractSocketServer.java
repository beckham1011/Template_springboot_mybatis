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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import cn.bjjoy.bms.mail.SendMail;
import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.setting.exception.ServiceException;
import cn.bjjoy.bms.setting.persist.model.Equipdata;
import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.impl.EquipdataServiceImpl;
import cn.bjjoy.bms.setting.service.impl.EquiptypeServiceImpl;
import cn.bjjoy.bms.socket.ByteUtil;
import cn.bjjoy.bms.util.DateUtils;
import cn.bjjoy.bms.util.IPUtil;
import cn.bjjoy.bms.util.SpringSocketUtil;
import cn.bjjoy.bms.util.StringUtils;

public abstract class AbstractSocketServer {

	private static final Logger logger = LogManager.getLogger();

    private Integer port ;
	/*发送数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
	SendMail sendMail = new SendMail();

	static final int threadsNum = Runtime.getRuntime().availableProcessors();
    
    ExecutorService threadsPool = Executors.newFixedThreadPool(threadsNum * 2);
	
	Selector selector;
    
    ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    
	//泵站总数
	private static final int STATION_NUM = 558;
	Map<String , String> ipAddressCodeMap = new ConcurrentHashMap<>(STATION_NUM);
	Map<String , String> addressCodeIpMap = new ConcurrentHashMap<>(STATION_NUM);
	
	/*映射客户端channel */
	Map<String, SocketChannel> clientsMap = new ConcurrentHashMap<>(STATION_NUM * 4);
	
    EquipdataService dataService  = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    EquiptypeService typeService  = SpringSocketUtil.getBean(EquiptypeServiceImpl.class) ;


    void init() throws IOException {
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
//        		if(type.getIP() != null)
//        			ipAddressCodeMap.put(type.getIP() , type.getAddressCode() );
//        		if( null!= type.getAddressCode() || !"".equals(type.getAddressCode() )){
//        			addressCodeIpMap.put(type.getAddressCode(), type.getIP());
//        		}
        	}
        }
    }
    
    /** 服务器端轮询监听，select方法会一直阻塞直到有相关事件发生或超时  */
    public void listen(int port) {
    	try {
    		this.port = port;
			init();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
    	logger.info("Listen...port:" + port);
    }
    
	abstract String[] parseCustomerEquipData(String receiveTxt);
	
	/** 
     * 把当前客户端信息 推送到其他客户端 
     */
    public void refreshAllStation(SocketChannel client ,String msg) throws IOException {
        if (!clientsMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()) {
				SocketChannel clientSocket = entry.getValue();
				if (!client.equals(clientSocket)) {
					refreshStation(clientSocket , msg );
				}
            }
        }
    }


	//新添加的泵站需要数据维护，发送邮件通知,异步
	public void sendMailToAdmin(String addressCode){
		//新添加的泵站需要数据维护，发送邮件通知
		String content = String.format("新增加了泵站。地址编码是：%s，需要维护" , addressCode);
		List<String> receivers = Lists.newArrayList();
		receivers.add(Constants.MAIL_RECEIVER_ADMIN) ;
		sendMail.sendEmail("新增加了泵站,请维护泵站信息", content, receivers, null);
	}
	    
    
	/** 
         * 把当前客户端信息 推送到其他客户端
     */
    public void refreshStation(SocketChannel client , String msg) {
    	if(client != null){
    		sBuffer.clear();
    		sBuffer.put(ByteUtil.hexStringToByte(msg));
    		sBuffer.flip();
    		
    		//输出到通道  
    		try {
				client.write(sBuffer);
				logger.info("Send msg to station : " + client.getLocalAddress() +" OK.....");
			} catch (IOException e) {
				logger.warn(" refreshStation exception: " , e);
			}
    	}
    }
	
    
    /**
     * 	 接收设备心跳数据，新加设备发送邮件
     * @param ip
     * @param addCode
     */
	public void updateStationHeartBeat(String ip, String addCode){
		Map<String,Object> params = new HashMap<>();
		params.put("addressCode", addCode);
		params.put("ip", ip);

		ipAddressCodeMap.put(ip, addCode) ;
		addressCodeIpMap.put(addCode, ip) ;
		
		logger.info("Equip station heart beat: IP " + ip + " , addresscode :" + addCode);
		if(typeService.queryList(params).size() > 0){
			typeService.updateByAddressCode(params);
		}else{
			Equiptype t = new Equiptype ();
			t.setIP(ip);
			t.setAddressCode(addCode);
			t.setAddTime(DateUtils.getCurrentDate());
			typeService.save(t);
			//接新添加泵站信号，发送邮件通知管理
			sendMail(addCode);
		}
	}
	
	public void sendMail(String addCode) {
		if(addCode != null || !"".equals(addCode)){
			if(addCode.startsWith("hsg")){
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
	
	//从socket获取的ip，根据ip获取泵站编码地址
	public String getAddressCode(String ip){
    	return ipAddressCodeMap.get(ip) ;
    }
    
    
	
    public void saveData(String receiveText , String ip) {
    	Equipdata t = new Equipdata();
    	String[] values = parseCustomerEquipData(receiveText);
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
				logger.error("parse customer data error: " + e);
			}
    	}
    	logger.info("Save equip data: " + t.toString() + " , IP:" + ip);
	}
	
    

    /** 处理不同的事件  */
    public void handle(SelectionKey selectionKey) throws Exception{
    	
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
					refreshAllStation(client, Constants.MSG_8082);
				} else if(IPUtil.isIPv4(receiveText)){
					//给特定的站点发送发送即时数据请求
					logger.info("receiveText:" + receiveText + "Refresh Special Station---------");
					refreshStation(clientsMap.get(receiveText) , Constants.MSG_8082);
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
	
}
