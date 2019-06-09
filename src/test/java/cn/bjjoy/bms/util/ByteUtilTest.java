package cn.bjjoy.bms.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import cn.bjjoy.bms.socket.ByteUtil;

public class ByteUtilTest {

	private static final Logger logger = LogManager.getLogger();
	
	private static final String byteSource = "01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 22 E0 00 00 00 67 37 00 00 00 00 00 00 00 02 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 E8 74" ;
	
	private byte[] dataBytes = null ;
	
	@Before
	public void init() {
		String trimSource = org.springframework.util.StringUtils.trimAllWhitespace(byteSource);
		logger.info("trimSource:" + trimSource);
		dataBytes = ByteUtil.hexStringToByte(trimSource);
	}
	
	@Test
	public void test(){
		String receiveTextCode1  = ByteUtil.toHexString1(dataBytes);
		String receiveTextCode2 = ByteUtil.binaryToHexString(dataBytes);
		String receiveTextCode3 = ByteUtil.bytesToHex(dataBytes);
		String receiveTextCode4 = ByteUtil.bytesToHex1(dataBytes);
		String receiveTextCode5 = ByteUtil.byteArrayToHexStr(dataBytes);
		String receiveTextCode7 = ByteUtil.bytesToHexFun1(dataBytes);
		String receiveTextCode8 = ByteUtil.bytesToHexFun3(dataBytes);
		
		logger.info("=================================receiveTextCode1:" + receiveTextCode1);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode2:" + receiveTextCode2);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode3:" + receiveTextCode3);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode4:" + receiveTextCode4);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode5:" + receiveTextCode5);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode7:" + receiveTextCode7);
		logger.info("+++++++++++++++++++++++++++++++++receiveTextCode8:" + receiveTextCode8);
		
	}
	
	
}

