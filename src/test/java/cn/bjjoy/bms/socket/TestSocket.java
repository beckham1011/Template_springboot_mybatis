package cn.bjjoy.bms.socket;

import java.util.Arrays;

import org.junit.Test;

import cn.bjjoy.bms.util.SpringSocketUtil;

public class TestSocket {
	
	 @Test
	 public void test(){
		String s1 = "01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 25 3F 00 00 02 6F 3F 00 00 00 00 00 00 03 3F 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 EA E9" ;
		String s2 = "01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 2B 10 00 00 02 6F FB 00 00 00 00 00 00 03 E1 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 D3 2E";
		
		String s3 = "01 04 2C 3F 00 00 00 3F 00 00 00 00 00 00 00 45 26 70 00 00 01 2E 3F 00 00 00 00 00 00 00 09 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 16 5A ";
		
		String[] vals1 = SpringSocketUtil.parse8082SocketData(s1) ;
		System.out.println(Arrays.toString(vals1));	
		
		String[] vals2 = SpringSocketUtil.parse8082SocketData(s2) ;
		System.out.println(Arrays.toString(vals2));
		
		String[] vals3 = SpringSocketUtil.parse8082SocketData(s3) ;
		System.out.println(Arrays.toString(vals3));
		
//		for (int i = 0; i < source.length(); i++) {
//			 String vl = SpringSocketUtil.subStringByIndex(source , i , Constants.LENGTH_MORE) ;
//			 System.out.println(i + ", vl :"+ vl + ", " + SpringSocketUtil.convertToHexLong(vl)) ;
//		}
		
//		System.out.println("4E" & "");
		
//		String s1 = "00 00 A1 7B" ;
//		String l = SpringSocketUtil.convertToHexLong(s1) ;
//		System.out.println(l);
//		s1 = "00 00 01 1E" ;
//		l = SpringSocketUtil.convertToHexLong(s1) ;
////		String[] result = parse8082SocketData(source);
//		System.out.println(l);
	 }
	 
	 @Test
	 public void testHex(){
		 
		 String hexStr = "0123456789ABCDEF";
		 String result = "";
		 String hex = "";
		 
		 byte source = -63;
		 
		 hex = String.valueOf(hexStr.charAt((source & 0xF0) >> 4));
		 hex += String.valueOf(hexStr.charAt(source & 0x0F));
		 result += hex + " ";
		 
		 System.out.println(result);
	 }
	 
	 
	 
}
