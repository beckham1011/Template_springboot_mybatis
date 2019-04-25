package cn.bjjoy.bms.util;

import javax.servlet.http.HttpServletRequest;

import cn.bjjoy.bms.enumutil.Device;

public class DeviceUtil {

	public static boolean isApp(HttpServletRequest request){		
		String  browserDetails = request.getHeader("User-Agent");
		if(browserDetails.indexOf(Device.Windows.toString()) > 0)
			return false;
		return true;
	}
	
}
