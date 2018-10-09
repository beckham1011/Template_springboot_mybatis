package cn.bjjoy.bms.socket;

import java.util.Arrays;

public class TestHexToByte {

	private static final int RAW_LENGTH = 2 ;
	private static final int MORE_LENGTH = 11 ;
	
	public static void main(String[] args) {
		
		String sSource = "31 31 31  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 32 00 00 00 00 58 07 00 00 00 00 00 00 00 1B 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 1B E3  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 20 10 00 00 00 00 A0 00 00 00 00 00 00 22 1B 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 CD BF  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 0B E0 00 00 00 9E 2C 00 00 00 00 00 00 00 1F 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 F6 94  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 3C B0 00 00 00 11 C9 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 6B F9  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 0F 00 00 00 00 3C 76 00 00 00 00 00 00 00 09 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 80 F6  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 1B 50 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 58 64  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 30 A0 00 00 00 82 F0 00 00 00 00 00 00 00 06 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 60 D1  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 2A 30 00 00 00 8A 5F 00 00 00 00 00 00 00 22 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 A8 CD  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 43 A0 00 00 00 00 0B 00 00 00 00 00 00 00 07 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 01 4B 7D  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 37 30 00 00 00 31 25 00 00 00 00 00 00 00 1A 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 17 DE  01 04 2C C2 14 66 66 BE 59 16 87 3F E2 8F 5C 3F 80 00 00 00 00 07 67 00 00 00 00 00 00 00 02 00 00 00 00 00 05 00 01 00 00 00 00 00 00 00 00 DE E9  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 0D C0 00 00 00 7A E0 00 00 00 00 00 00 00 1F 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 99 05  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 17 50 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 01 59 A2  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 44 F6 80 00 00 00 0A F7 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 10 DE  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 1D 10 00 00 00 71 04 00 00 00 00 00 00 00 5B 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 A1 96  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 0C 90 00 00 00 86 3E 00 00 00 00 00 00 00 70 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 0A 6B  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 32 50 00 00 00 2E 40 00 00 00 00 00 00 00 06 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 EE F0  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 39 10 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 08 77  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 0B 50 00 00 00 EA 09 00 00 00 00 00 00 00 3B 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 29 1E  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 16 70 00 00 01 43 C2 00 00 00 00 00 00 06 96 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 AC D6  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 50 F0 00 00 00 35 89 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 A6 EA  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 21 80 00 00 00 90 D9 00 00 00 00 00 00 00 B2 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 CD 67  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 10 20 00 00 00 A3 4C 00 00 00 00 00 00 00 95 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 61 E4  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 34 80 00 00 00 1A 6C 00 00 00 00 00 00 00 03 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 FA AE  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 15 20 00 00 00 00 3A 00 00 00 00 00 01 CB BA 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 33 07  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 14 10 00 00 00 72 C9 00 00 00 00 00 00 00 B6 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 35 1E  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 34 90 00 00 00 B2 08 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 F5 78  01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 4C F0 00 00 00 35 41 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 34 50  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 15 D0 00 00 00 21 9A 00 00 00 00 00 00 00 05 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 63 35  01 04 2C 80 00 00 00 80 00 00 00 00 00 00 00 45 3F 50 00 00 00 0F 23 00 00 00 00 00 00 00 01 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 39 C5  68 73 67 30 38 32  68 73 67 30 37 34  68 73 67 30 37 37  68 73 67 30 33 31  68 73 67 30 37 35  68 73 67 30 37 39  68 73 67 30 37 36  68 73 67 30 37 38  68 73 67 30 38 30  68 73 67 30 38 31  68 73 67 30 37 32  68 73 67 30 33 37  68 73 67 30 34 35  68 73 67 31 30 36  68 73 67 30 34 37  68 73 67 30 33 38  68 73 67 30 37 31  68 73 67 31 31 36  68 73 67 30 33 34  68 73 67 31 31 38  68 73 67 30 38 34  68 73 67 30 36 33  68 73 67 30 34 34  68 73 67 31 33 37  68 73 67 30 34 36  68 73 67 30 36 30  68 73 67 30 36 31  68 73 67 31 30 32  68 73 67 31 33 38  68 73 67 31 34 31  68 73 67 30 35 31  68 73 67 30 35 32  68 73 67 31 33 35  68 73 67 31 33 39  68 73 67 30 39 38  68 73 67 31 31 39  68 73 67 31 34 30  68 73 67 31 32 33  68 73 67 30 38 35  68 73 67 30 35 36  68 73 67 30 39 31  68 73 67 31 32 38  68 73 67 30 34 31  68 73 67 30 39 30  68 73 67 30 33 36  68 73 67 31 31 35  68 73 67 30 39 34  68 73 67 31 32 30  68 73 67 30 34 39  68 73 67 31 34 33  68 73 67 30 39 32  68 73 67 30 39 37  68 73 67 30 35 39  68 73 67 31 30 30  68 73 67 30 34 32  68 73 67 31 33 30  68 73 67 31 30 38  68 73 67 30 36 39  68 73 67 30 33 39  68 73 67 31 32 39  68 73 67 31 31 32  68 73 67 30 38 33  68 73 67 30 39 36  68 73 67 31 31 31  68 73 67 31 32 32  68 73 67 30 38 39  68 73 67 30 39 39  68 73 67 30 34 38  68 73 67 31 30 35  68 73 67 30 35 35  68 73 67 31 30 37  68 73 67 31 32 35  68 73 67 30 39 33  68 73 67 31 33 33  68 73 67 30 36 32  68 73 67 30 34 33  68 73 67 30 35 38  68 73 67 30 35 30  68 73 67 30 36 36  68 73 67 31 32 31  68 73 67 31 30 33  68 73 67 31 31 34  68 73 67 31 33 31  68 73 67 31 30 34  68 73 67 31 31 30  68 73 67 30 36 35  68 73 67 30 37 33  68 73 67 31 32 36  68 73 67 30 35 34  68 73 67 30 38 36  68 73 67 30 35 37  68 73 67 31 30 31  68 73 67 31 30 39  68 73 67 30 35 33  68 73 67 31 31 37  68 73 67 30 33 35  68 73 67 30 37 32  68 73 67 30 33 37  68 73 67 30 34 35  68 73 67 31 30 36  68 73 67 30 38 32  68 73 67 30 33 38  68 73 67 30 34 37  68 73 67 30 37 31  68 73 67 30 37 34  68 73 67 31 31 36  68 73 67 30 37 37  68 73 67 30 38 34  68 73 67 31 31 38  68 73 67 30 33 34  68 73 67 30 36 33  68 73 67 30 34 34  68 73 67 30 37 35  68 73 67 30 34 39  68 73 67 30 34 36  68 73 67 30 36 30  68 73 67 30 36 31  68 73 67 31 33 38  68 73 67 31 30 32  68 73 67 31 33 37  68 73 67 30 33 31  68 73 67 30 39 38  68 73 67 30 35 31  68 73 67 31 34 31  68 73 67 30 35 32  68 73 67 31 33 35  68 73 67 31 33 39  68 73 67 31 31 39  68 73 67 31 34 30  68 73 67 31 32 33  68 73 67 30 38 35  68 73 67 30 39 31  68 73 67 30 35 36  68 73 67 31 32 38  68 73 67 30 34 31  68 73 67 30 39 30  68 73 67 30 37 39  68 73 67 30 37 36  68 73 67 30 33 36  68 73 67 31 31 35  68 73 67 30 37 38  68 73 67 30 39 34  68 73 67 31 32 30  68 73 67 31 34 33  68 73 67 30 39 32  68 73 67 30 39 37  68 73 67 30 35 39  68 73 67 31 30 30  68 73 67 30 34 32  68 73 67 31 33 30  68 73 67 31 30 38  68 73 67 30 36 39  68 73 67 30 33 39  68 73 67 31 32 39  68 73 67 31 31 32  68 73 67 30 38 33  68 73 67 30 39 36  68 73 67 31 31 31  68 73 67 31 32 32  68 73 67 30 38 39  68 73 67 30 39 39  68 73 67 30 34 38  68 73 67 30 38 30  68 73 67 31 30 35  68 73 67 30 35 35  68 73 67 31 30 37  68 73 67 31 32 35  68 73 67 30 39 33  68 73 67 31 33 33  68 73 67 30 38 31  68 73 67 30 36 32  68 73 67 30 34 33  68 73 67 30 35 38  68 73 67 30 35 30  68 73 67 30 36 36  68 73 67 31 32 31  68 73 67 31 30 33  68 73 67 31 31 34  68 73 67 31 33 31  68 73 67 31 30 34  68 73 67 31 31 30  68 73 67 30 36 35  68 73 67 30 37 33  68 73 67 31 32 36  68 73 67 30 35 34  68 73 67 30 38 36  68 73 67 30 35 37  68 73 67 31 30 31  68 73 67 31 30 39  68 73 67 30 35 33  68 73 67 31 31 37  68 73 67 30 33 35  68 73 67 30 37 32  68 73 67 30 33 37  68 73 67 30 34 35  68 73 67 31 30 36  68 73 67 30 34 37  68 73 67 30 33 38  68 73 67 30 37 31  68 73 67 31 31 36  68 73 67 30 33 34  68 73 67 30 38 34  68 73 67 31 31 38  68 73 67 30 36 33  68 73 67 30 34 34  68 73 67 31 33 37  68 73 67 30 36 30  68 73 67 30 34 36  68 73 67 30 34 39  68 73 67 30 36 31  68 73 67 31 33 38  68 73 67 31 30 32  68 73 67 31 34 31  68 73 67 30 39 38  68 73 67 30 35 31  68 73 67 31 33 35  68 73 67 30 35 32  68 73 67 31 33 39  68 73 67 31 34 30  68 73 67 31 31 39  68 73 67 31 32 33  68 73 67 30 38 35  68 73 67 30 35 36  68 73 67 30 39 31  68 73 67 31 32 38  68 73 67 30 34 31  68 73 67 30 39 30  68 73 67 30 33 36  68 73 67 31 31 35  68 73 67 30 39 34  68 73 67 31 32 30  68 73 67 31 34 33  68 73 67 30 39 32  68 73 67 30 39 37  68 73 67 30 35 39  68 73 67 31 30 30  68 73 67 30 34 32  68 73 67 31 33 30  68 73 67 31 30 38  68 73 67 30 36 39  68 73 67 30 33 39  68 73 67 31 32 39  68 73 67 31 31 32  68 73 67 30 38 33  68 73 67 30 39 36  68 73 67 31 31 31  68 73 67 31 32 32  68 73 67 30 38 39  68 73 67 30 39 39  68 73 67 30 34 38  68 73 67 31 30 35  68 73 67 30 35 35  68 73 67 31 30 37  68 73 67 31 32 35  68 73 67 30 39 33  68 73 67 31 33 33  68 73 67 30 36 32  68 73 67 30 34 33  68 73 67 30 35 38  68 73 67 30 35 30  68 73 67 30 36 36  68 73 67 31 32 31  68 73 67 31 30 33  68 73 67 31 31 34  68 73 67 31 33 31  68 73 67 31 30 34  68 73 67 31 31 30  68 73 67 30 36 35  68 73 67 30 38 32  68 73 67 30 37 33  68 73 67 31 32 36  68 73 67 30 37 37  68 73 67 30 37 34  68 73 67 30 35 34  68 73 67 30 37 35  68 73 67 30 33 31  68 73 67 30 38 36  68 73 67 30 35 37  68 73 67 30 37 39  68 73 67 31 30 31  68 73 67 31 30 39  68 73 67 30 37 36  68 73 67 30 37 38  68 73 67 30 35 33" ;
		for(String source : sSource.split("  ")){
			if(source != null){
				if(source.length() >= 25){
					String[] values = parseSocketData(source) ;
					System.out.println(Arrays.toString(values));
				}else{
					System.out.println(getAddressCode(source));
				}
			}
		}
		
//		String source = "01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 0C 30 00 00 01 AC F9 00 00 00 00 00 00 06 C8 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 00 9A 1D" ;
//		String[] values = parseSocketData(source) ;
//		for(String v : values){
//			System.out.println(v);
//		}
	}
	
	public static String subStringByIndex(String source , int index , int length){
		return source.substring(index, index + length);
	}

	public static String getAddressCode (String source){
		StringBuffer addressCode = new StringBuffer();
		for(String str : source.split(" ")){
			Integer x = Integer.parseInt(str,16);
			addressCode.append(byteAsciiToChar(x));
		}
		return addressCode.toString() ;
	}
	
	public static char byteAsciiToChar(int ascii){
		char ch = (char)ascii;
		return ch;
	}
	
	public static byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}   
	
	 private static byte charToByte(char c) {
		    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
	
	public static String[] parseSocketData(String source){
		String[] values = new String[4];
		try {
			String address = subStringByIndex(source , 1 , RAW_LENGTH) ;
			String forwardCollection = subStringByIndex(source , 57 , MORE_LENGTH) ;
			String backwardCollection = subStringByIndex(source , 81 , MORE_LENGTH) ;
			String flowRate = subStringByIndex(source , 9 , 12) ;
			values[0] = convertToHexLong(address) ;
			values[1] = convertToHexLong(forwardCollection) ;
			values[2] = convertToHexLong(backwardCollection) ;
			values[3] = convertToHexLong(flowRate) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values ;
	}
	 
	public static int getValueFromHex(String hexSource){
		StringBuffer reslt = new StringBuffer();
		for(String hex : hexSource.split(" ")){
			Integer x = Integer.parseInt(hex,16) ;
			reslt.append(x) ;
		}
		return Integer.parseInt(reslt.toString()) + 65536;
	}
	
	//将09 00 DE 1C这种数据转换成，10进制的数据
	public static String convertToHexLong(String source){
		Long x = Long.parseLong(source.trim().replaceAll(" ", ""),16) ;
		return String.valueOf(x);
	}
}
