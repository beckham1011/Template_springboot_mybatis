package cn.bjjoy.bms.util;

public class IPUtil {

	public static boolean isIPv4(String source){
		String[] items = source.split("\\.");
		if(items.length != 4)
			return false ;
		else{
			try {
				for(String item : items){
					int value = Integer.parseInt(item) ;
					if(value < 0 || value > 255)
						return false ;
				}
				return true ;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false; 
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(isIPv4("255.255.255.255"));
	}
	
	
}
