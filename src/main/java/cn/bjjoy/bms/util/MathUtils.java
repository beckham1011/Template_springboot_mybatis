package cn.bjjoy.bms.util;

import java.math.BigDecimal;
import java.util.Random;

public class MathUtils {

	/**
	 * 产生随机的六位数，用做短信验证码
	 * @return
	 */
	public static String getSix() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return getSix();
		}
		return result;
	}
	
	public static BigDecimal sum(BigDecimal a, BigDecimal b) {

		if (a == null)
			a = BigDecimal.ZERO;

		if (b == null)
			b = BigDecimal.ZERO;

		return a.add(b);
	}
	
	public static double div(BigDecimal a, BigDecimal b){
		return div(a, b, 2);
	}
	
	public static double div(BigDecimal a, BigDecimal b, int scale){
		
		if(a == null)
			return 0;
		
		if(b == null)
			return 0;

		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
}
