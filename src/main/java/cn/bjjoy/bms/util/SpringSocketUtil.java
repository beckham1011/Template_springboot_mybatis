package cn.bjjoy.bms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import cn.bjjoy.bms.setting.constants.Constants;

@Component
public class SpringSocketUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringSocketUtil.applicationContext == null) {
        	SpringSocketUtil.applicationContext = applicationContext;
        }
    }
 
    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
 
    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
 
    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

	public static String convertToHexLong(String source){
		Long x = Long.parseLong(source.trim().replaceAll(" ", ""),16) ;
		return String.valueOf(x);
	}
    
	public static String subStringByIndex(String source , int index , int length){
		return source.substring(index, index + length);
	}
	
	public static char byteAsciiToChar(int ascii){
		char ch = (char)ascii;
		return ch;
	}


	 public static String[] parse8084SocketData(String source){
		 String[] values = new String[4];
		  try {
			String address = SpringSocketUtil.subStringByIndex(source , 1 , Constants.LENGTH_RAW) ;
			String forwardCollection = SpringSocketUtil.subStringByIndex(source , 21 , Constants.LENGTH_MORE) ;
			String backwardCollection = SpringSocketUtil.subStringByIndex(source , 81 , Constants.LENGTH_MORE) ;
			String flowRate = SpringSocketUtil.subStringByIndex(source , 9 , 11) ;
			values[0] = SpringSocketUtil.convertToHexLong(address) ;
			values[1] = SpringSocketUtil.convertToHexLong(forwardCollection) ;
			values[2] = SpringSocketUtil.convertToHexLong(backwardCollection) ;
			values[3] = SpringSocketUtil.convertToHexLong(flowRate) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values ;
	}
	

	 public static String[] parse8082SocketData(String source){
		 String[] values = new String[4];
		  try {
			String address = SpringSocketUtil.subStringByIndex(source , 0 , Constants.LENGTH_RAW) ;
			String forwardCollection = SpringSocketUtil.subStringByIndex(source , 57 , Constants.LENGTH_MORE) ;
			String backwardCollection = SpringSocketUtil.subStringByIndex(source , 81 , Constants.LENGTH_MORE) ;
			String flowRate = SpringSocketUtil.subStringByIndex(source , 9 , Constants.LENGTH_MORE) ;
			values[0] = SpringSocketUtil.convertToHexLong(address) ;
			values[1] = SpringSocketUtil.convertToHexLong(forwardCollection) ;
			values[2] = SpringSocketUtil.convertToHexLong(backwardCollection) ;
			values[3] = SpringSocketUtil.convertToHexLong(flowRate) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values ;
	 }
	

}
