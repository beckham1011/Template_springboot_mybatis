package cn.bjjoy.bms.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /** 本地换行符 */
    public static final String LS = System.getProperty("line.separator");
    public static final String R = "\r";
    public static final String N = "\n";
    /** 本地目录分隔符 */
    public static final String FS = System.getProperty("file.separator");

    /**
     * 是否存在空值
     * @param css
     * @return
     */
    public static boolean containEmpty(final CharSequence... css) {
    	for(CharSequence cs : css) {
    		if(isEmpty(cs)) {
    			return true;
    		}
    	}
    	return false;
    }

    public static String getXingxing(BigDecimal fen) {
    	
    	if(fen == null)
    		return "☆☆☆☆☆";
    	
    	if(fen.intValue() == 1) {
    		return "★☆☆☆☆";
    	}
    	if(fen.intValue() == 2) {
    		return "★★☆☆☆";
    	}
    	if(fen.intValue() == 3) {
    		return "★★★☆☆";
    	}
    	if(fen.intValue() == 4) {
    		return "★★★★☆";
    	}
    	if(fen.intValue() == 5) {
    		return "★★★★★";
    	}
    	return "☆☆☆☆☆";
    }
    /**
     *方法名:getUUID
     *返回值:String
     *作     用:获得uuid生成的id
     *日     期:2017年8月31日 16:59:20
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", EMPTY);
    }

    /**
     * @Title getSerialNum
     * @Description 流水号
     * @return String
     */
    public static String getSerialNum() {
        long random = Math.round(Math.random() * 89999) + 10000;
        return DateUtils.formatDate(DateUtils.YYYYMMDDHHMMSS)  + random;
    }
    
    public static String unicodeToString(String str) {
    	Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+EMPTY);
        }
        return str;
    }
    
    public static String toFileMsg(String loc){
        if(loc == null)
            return null;
        
        int index = loc.indexOf("-");
        
        if(index == -1)
            return loc;
        
        return loc.substring(index+1,loc.length()) + "第" + loc.substring(0, index) + "行";
    } 
    
    public static String toString(Throwable e){
        String sOut = EMPTY;
        
        if(e.getCause() != null){
            sOut = e.getCause().toString() + "\r\n";
        }
        
        StackTraceElement[] trace = e.getStackTrace();
        
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
    
    public static List<Long> toListInt(Object str) {
        String[] ids = String.valueOf(str).split(",");
        List<Long> list = new ArrayList<Long>();

        for (String s : ids) {
            if (RegexUtils.checkInteger(s))
                list.add(Long.parseLong(s));
        }
        
        return list;
    }

    public static List<String> toListString(Object str) {

        if(StringUtils.isEmpty(str))
            return new ArrayList<>();
        
        String[] strs = String.valueOf(str).split(",");
        return Arrays.asList(strs);
    }

    public static boolean isEmpty(Object str) {
        if (str == null || EMPTY.equals(String.valueOf(str)))
            return true;
        return false;
    }

    public static boolean notEmpty(Object str) {
        if (str == null || EMPTY.equals(String.valueOf(str)))
            return false;
        return true;
    }

    public static String valueOf(Object str) {
        if (str == null)
            return null;
        return String.valueOf(str);
    }

    public static Integer toInteger(Object obj) {
        if (obj == null)
            return null;
        return new Integer(new BigDecimal(obj.toString()).intValue());
    }
    
    public static String valueDoubleOf(BigDecimal str) {
        if (str == null)
            return null;
        return String.valueOf(str.doubleValue());
    }
    
    public static final char UNDERLINE='_';  
       public static String camelToUnderline(String param){  
           if (param==null||EMPTY.equals(param.trim())){  
               return EMPTY;  
           }  
           int len=param.length();  
           StringBuilder sb=new StringBuilder(len);  
           for (int i = 0; i < len; i++) {  
               char c=param.charAt(i);  
               if (Character.isUpperCase(c)){  
                   sb.append(UNDERLINE);  
                   sb.append(Character.toLowerCase(c));  
               }else{  
                   sb.append(c);  
               }  
           }  
           return sb.toString();  
       }  
       public static String underlineToCamel(String param){  
           if (param==null||EMPTY.equals(param.trim())){  
               return EMPTY;  
           }  
           int len=param.length();  
           StringBuilder sb=new StringBuilder(len);  
           for (int i = 0; i < len; i++) {  
               char c=param.charAt(i);  
               if (c==UNDERLINE){  
                  if (++i<len){  
                      sb.append(Character.toUpperCase(param.charAt(i)));  
                  }  
               }else{  
                   sb.append(c);  
               }  
           }  
           return sb.toString();  
       }  
       public static String underlineToCamel2(String param){  
           if (param==null||EMPTY.equals(param.trim())){  
               return EMPTY;  
           }  
           StringBuilder sb=new StringBuilder(param);  
           Matcher mc= Pattern.compile("_").matcher(param);  
           int i=0;  
           while (mc.find()){  
               int position=mc.end()-(i++);  
               //String.valueOf(Character.toUpperCase(sb.charAt(position)));  
               sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());  
           }  
           return sb.toString();  
       }


    public static String listToStr(List<String> strList) {
        if (CollectionUtils.isEmpty(strList))
            return EMPTY;
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (String str : strList) {
            if (isNotEmpty(str)) {
                if (flag) {
                    builder.append(",");
                } else {
                    flag = true;
                }
                builder.append(str);
            }
        }
        return builder.toString();
    }
    
    /**
     * 将对象转换成字符串,如果该对象为null 则直接反回null
     * @param object 待转换对象
     * @return String 转换后String对象
     */
    public static String parseString(Object object) {
        // 如果对象为空
        if (null == object) {
            // 直接返回null
            return null;
        }
        // 转换类型 并返回
        return String.valueOf(object);
    }
    
    /**
     * @Title trimEmpty
     * @Description 去掉全部空格
     * @date 2017年10月12日 下午3:40:11
     * @auther guoyongfeng
     * @param str
     * @return
     * @return String
     */
    public static String trimEmpty(String str) {
        if (isNotEmpty(str)) {
            str = str.replace("", EMPTY);
        }
        return str;
    }
    
    /**
     * @Title replaceRN
     * @Description 替换所有换行符
     * @date 2018年6月2日 下午10:28:28
     * @auther guoyongfeng
     * @param str
     * @return String
     */
    public static String replaceRN(String str) {
        if (isNotEmpty(str)) {
            str = str.replace(R, EMPTY);
            str = str.replace(N, EMPTY);
        }
        return str;
    }
    
    /**
     * @Title beginUpperCase
     * @Description 首字母大写
     * @date 2018年6月2日 下午10:28:40
     * @auther guoyongfeng
     * @param str
     * @return String
     */
    public static String beginUpperCase(String str) {
        if (StringUtils.isNotEmpty(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    } 
}