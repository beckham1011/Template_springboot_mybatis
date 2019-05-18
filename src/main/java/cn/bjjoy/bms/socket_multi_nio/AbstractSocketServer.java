package cn.bjjoy.bms.socket_multi_nio;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import cn.bjjoy.bms.util.SpringSocketUtil;

public abstract class AbstractSocketServer {

	private static final Logger logger = LogManager.getLogger();

    /*发送数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    
	SendMail sendMail = new SendMail();

	static final int threadsNum = Runtime.getRuntime().availableProcessors();
    
    ExecutorService threadsPool = Executors.newFixedThreadPool(threadsNum * 2);
	
	//泵站总数
	private static final int STATION_NUM = 558;
	Map<String , String> ipAddressCodeMap = new ConcurrentHashMap<>(STATION_NUM);
	Map<String , String> addressCodeIpMap = new ConcurrentHashMap<>(STATION_NUM);
	
	/*映射客户端channel */
	Map<String, SocketChannel> clientsMap = new ConcurrentHashMap<>(STATION_NUM * 4);
	
    EquipdataService dataService  = SpringSocketUtil.getBean(EquipdataServiceImpl.class) ;
    EquiptypeService typeService  = SpringSocketUtil.getBean(EquiptypeServiceImpl.class) ;

	abstract void init() throws IOException;

	abstract public void listen();
	
	
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
    
    
	public abstract String[] parseCustomerEquipData(String receiveTxt);
	
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
				logger.error(e.getMessage());
				e.printStackTrace();
			}
    	}
    	logger.info("Save equip data: " + t.toString() + " , IP:" + ip);
	}
	
	
}
