package cn.bjjoy.bms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
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
			System.out.println(forwardCollection + " , " + backwardCollection);
			values[0] = SpringSocketUtil.convertToHexLong(address) ;
			values[1] = SpringSocketUtil.convertToHexLong(forwardCollection) ;
			values[2] = SpringSocketUtil.convertToHexLong(backwardCollection) ;
			values[3] = SpringSocketUtil.convertToHexLong(flowRate) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values ;
	 }
	
	 @Test
	 public void test(){
		String source = "01 04 2C 00 00 00 00 00 00 00 00 00 00 00 00 45 57 50 00 00 00 25 A7 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 01 00 00 00 00 00 01 00 01 25 A1" ;
		
		String[] vals = parse8082SocketData(source) ;
		System.out.println(Arrays.toString(vals));
		
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
	 
	 
	 public static List<String> readFileByLines(String fileName) {  
        BufferedReader reader = null;  
        List<String> dataList = new ArrayList<>();
        try {
        	File file = new File(fileName);  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
                dataList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
        	SpringSocketUtil.emptyFile(Constants.FILE_PATH) ;
            if (reader != null) {  
                try {
                    reader.close();
                } catch (IOException e1) {  
                }  
            }  
        }
        return dataList ;
    }

	public static void emptyFile(String filePath) {
		File f = new File(filePath) ;
        try {
			OutputStream out = new FileOutputStream(f); // 通过对象多态性，进行实例化
			// 第3步、进行写操作
			String str = "" ;        				// 准备一个字符串
			byte b[] = str.getBytes() ;            	// 只能输出byte数组，所以将字符串变为byte数组
			out.write(b) ;                        	// 将内容输出，保存文件
			// 第4步、关闭输出流
			out.close() ;                        	// 关闭输出流
			//文件不存在会自动创建
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	 
	 
}
