package cn.bjjoy.bms;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataStation {

	static SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static void main(String[] args) {
		String[] addressCodes = {"hag763",
				"hag771",
				"hag775",
				"hgs912",
				"hsg145",
				"hsg146",
				"hsg149",
				"hsg152",
				"hsg166",
				"hsg181",
				"hsg187",
				"hsg188",
				"hsg205",
				"hsg206",
				"hsg212",
				"hsg214",
				"hsg215",
				"hsg215",
				"hsg216",
				"hsg218",
				"hsg221",
				"hsg228",
				"hsg229",
				"hsg230",
				"hsg232",
				"hsg233",
				"hsg235",
				"hsg236",
				"hsg239",
				"hsg240",
				"hsg241",
				"hsg243",
				"hsg244",
				"hsg245",
				"hsg247",
				"hsg249",
				"hsg250",
				"hsg259",
				"hsg260",
				"hsg262",
				"hsg263",
				"hsg265",
				"hsg765",
				"hsg766",
				"hsg767",
				"hsg768",
				"hsg769",
				"hsg770",
				"hsg772",
				"hsg773",
				"hsg774",
				"hsg776",
				"hsg777",
				"hsg778",
				"hsg779",
				"hsg781",
				"hsg855"} ;
		
		//946800000
		
		Random ran = new Random() ;
		for (int i = 0; i < addressCodes.length; i++) {
			int vl = ran.nextInt(20000)  ;
			if( vl < 10000){
				System.out.println(vl + 10000);
			}else
				System.out.println(vl );
		}
		
		
//		for(String code : addressCodes){
//			
//			System.out.println("values('" + code + "','2018-10-29");
//			
//		}
		
		
	}
	
	
	
	
}
