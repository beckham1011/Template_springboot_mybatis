package cn.bjjoy.bms.socket_multi_nio;

import cn.bjjoy.bms.setting.constants.Constants;
import cn.bjjoy.bms.util.SpringSocketUtil;

public class ParseSocket {

	 public static String[] parseSocketData(String source){
		 String[] values = new String[4];
		  try {
			String address = SpringSocketUtil.subStringByIndex(source , 1 , Constants.LENGTH_RAW) ;
			String forwardCollection = SpringSocketUtil.subStringByIndex(source , 21 , Constants.LENGTH_MORE) ;
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
	 
	 public static void main(String[] args) {
		String source = "54 75 65 20 4F 63 74 20 30 32 20 31 37 3A 33 30 3A 35 37 20 43 53 54 20 32 30 31 38 20 63 6F 6E 6E 65 63 74 65 64 21" ;
		String[] vl = parseSocketData(source);
		System.out.println(vl[0] + " , " + vl[1] + " , " + vl[2] + " , " + vl[3]);
		
	}
	 
	
}
