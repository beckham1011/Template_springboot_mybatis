package cn.bjjoy.bms.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 验证包括以下 ：　
 *              验证特殊字符与长度 - 支持全角
 *              验证非法字符
 *              验证获得字符长度
 *              验证非空
 *              验证空白符
 *              验证中文
 *              验证英文
 *              验证大写字母
 *              验证小写字母
 *              验证数字
 *              验证15位整数
 *              验证15位浮点整数
 *              验证正整数 - 正整数浮点
 *              验证15位负数
 *              验证15位浮点负数
 *              验证负整数 - 负整数浮点
 *              验证     -整数     - 负数     -  整数浮点     - 负数浮点
 *              验证日期 格式为  2013-02-28      兼容证润年平年
 *              验证日期时间 格式为  2013-02-28  24:00:00  兼容证润年平年
 *              验证时间 格式为    24:00:00 
 *              电话号码
 *              邮编
 *              E-mail
 *              手机号码
 *              固定电话
 *              身份证
 *              URL
 *              域名
 *              IP
 * 创建人	：韩文博     
 */
public class RegexUtils {

	/** 
     * 电话号码验证.手机号,只验证了是否是11位纯数字
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {
    	if(str == null)
    		return false;
        Pattern p = Pattern.compile("^[1][0-9][0-9]{9}$"); // 验证手机号  
        Matcher m = p.matcher(str);
        return m.matches();   
    }
	
	/**
	 * 用来验证 在前台传来map 中的id 等值是否存在 或者是 否是一个 数字
	 * @param key
	 * @param map
	 * @return
	 */
	public static boolean validateMapIsNumber(String key ,Map<String,Object> map){
		if(null != key && !"".equals(key)){
			if(map.containsKey(key) && null != map.get(key) && !"".equals(map.get(key)))
				return checkInteger(map.get(key).toString());
		}
		return false;
	}
	
    /**
     * 验证数字
     */
    public static boolean checkInteger(Object obj) {
    	
    	String date = String.valueOf(obj);
    	
    	if(null == date || "".equals(date)){
    		return false;
    	}
        String regex = "^[0-9]*$";
        return Pattern.matches(regex, date);
    }	
	

    /**
     * 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     * @param userName
     * @return
     */
    public static boolean checkValidateString(String userName,int length) {
        String validateStr = "^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+${"+length+"}";
        return Pattern.matches(validateStr, userName);
    }
    
    /**
     * 非法字符验证
     * @param userName
     * @param length
     * @return
     */
    public static boolean checkIsIllegalCharacter(String userName,int length) {
        String regex = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        return Pattern.matches(regex, userName);
    }
    
    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     * @param value
     * @return
     */
    public static int checkStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 验证空白字符
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     */ 
    public static boolean checkBlankSpace(String blankSpace) { 
        String regex = "\\s+";
        return Pattern.matches(regex,blankSpace); 
    } 
     
    /**
     * 验证中文
     * @param chinese 中文字符
     */ 
    public static boolean checkChinese(String chinese) { 
        String regex = "^[\u4E00-\u9FA5]+$"; 
        return Pattern.matches(regex,chinese); 
    }
    
    /**
     * 验证英文 
     * @param chinese 中文字符
     */ 
    public static boolean checkEnglish(String chinese) { 
        String regex = "^[A-Za-z]+$"; 
        return Pattern.matches(regex,chinese); 
    }
    
    /**
     * 验证小写字母
     * @param chinese 中文字符
     */ 
    public static boolean checkLowercaseEnglish(String chinese) { 
        String regex = "^[a-z]+$"; 
        return Pattern.matches(regex,chinese); 
    }
    
    /**
     * 验证大写字母
     * @param chinese 中文字符
     */ 
    public static boolean checkUppercaseEnglish(String chinese) { 
        String regex = "^[A-Z]+$"; 
        return Pattern.matches(regex,chinese); 
    }

    /**
     * 验证正整书  不超过15位
     * @param date
     * @return
     */
    public static boolean checkNumber(String date) {
        String regex = "^[0-9]*$";
        return Pattern.matches(regex, date);
    }

    /**
     * 验证浮点小数 - 保留几位小数根据 count 参数验证
     * @param date
     * @return
     */
    public static boolean checkNumber(String number,int count) {
        return Pattern.matches("[0-9]*[.]{1}[0-9]{"+count+"}", number);
    }
    
    /**
     * 验证 正整数 或浮点数 - 兼容整数小数
     * @param date
     * @return
     */
    public static boolean checkIntegerNumber(String number,int count) {
        return Pattern.matches("^(([1-9]\\d*)|0)(\\.\\d{2})?$", number);
    }

    /**
     * 验证负整数  - 不能超过15位
     * @param date
     * @return
     */
    public static boolean checkNegativeNumber(String date) {
        String regex = "^-[1-9][0-9]{1,14}";
        return Pattern.matches(regex, date);
    }
    
    /**
     * 验证 负整数  -  保留几位小数根据 count 参数验证
     * @param date
     * @return
     */
    public static boolean checkNegativeNumber(String number,int count) {
        String regex = "^-[1-9][0-9]{1,14}\\.[0-9]{"+count+"}";
        return Pattern.matches(regex, number);
    }

    /**
     * 验证负整数 或小数 - 兼容整数小数
     * @param date
     * @return
     */
    public static boolean checkNegativeIntegerNumber(String number,int count) {
        String regex = "^-[1-9][0-9]{1,14}+(\\.[0-9]{1,"+count+"}+)?$";
        return Pattern.matches(regex, number);
    }
    
    /**
     * 验证  - 整数   - 负数
     */ 
    public static boolean checkDigit(String digit) { 
        String regex = "\\-?[1-9]\\d+"; 
        return Pattern.matches(regex,digit); 
    } 

     
    /**
     * 验证    整数        负数        浮点        整浮点    负浮点数
     */ 
    public static boolean checkDecimals(String decimals) { 
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
        return Pattern.matches(regex,decimals); 
    } 
    
    /**
     * 日期格式验证  2013-02-02 兼容闰年平年
     * @param date
     * @return
     */
    public static boolean checkDate(String date) {
        String regex = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
        return Pattern.matches(regex, date);
    }
    
    /**
     * 验证时间日期  格式  2103-02-21 21:23:20 兼容闰年平年
     * @param date
     * @return
     */
    public static boolean checkDateTime(String date){
    	String regex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        return Pattern.matches(regex, date);
    }
    /**
     * 验证时间日期  格式  2103-02-21 21:23 兼容闰年平年
     * @param date
     * @return
     */
    public static boolean checkDateHour(String date){
    	String regex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";
        return Pattern.matches(regex, date);
    }

    /**
     * 验证时间      格式  21:23:20
     * @param date
     * @return
     */
    public static boolean checkTime(String date){    
        String regex = "^(0?\\d|1[0-9]|2[0-3])|((0?\\d|1[0-9]|2[0-3]):(0?\\d|[0-5]\\d)(:(0?\\d|[0-5]\\d)){0,1})$";
        return Pattern.matches(regex, date);

    }
    
    /**
     * 验证Email
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) { 
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"; 
        return Pattern.matches(regex, email); 
    } 
     
    /**
     * 验证身份证号码 idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) { 
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"; 
        return Pattern.matches(regex,idCard); 
    }

    /**
     * 验证URL地址
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkURL(String url) { 
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"; 
        return Pattern.matches(regex, url); 
    } 
    
    /**
     * 获取网址 URL 的一级域名
     * http://detail.tmall.com/item.htm?spm=a230r.1.10.44.1xpDSH&id=15453106243&_u=f4ve1uq1092 ->> tmall.com
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }
    /**
     * 匹配中国邮政编码
     * @param postcode 邮政编码
     */ 
    public static boolean checkPostcode(String postcode) { 
        String regex = "[1-9]\\d{5}"; 
        return Pattern.matches(regex, postcode); 
    } 
     
    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIpAddress(String ipAddress) { 
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"; 
        return Pattern.matches(regex, ipAddress); 
    } 

	/**
	 * 验证邮箱地址是否合法
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email){
		boolean flag=false;
		String rex="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p=Pattern.compile(rex);
		Matcher m=p.matcher(email);
		if(m.find()){
			flag = true;
		}
		return flag;
	}
	/**
	 * 验证QQ号码是否合法
	 * @param qq
	 * @return
	 */
	public static boolean verifyQQ(String qq){
		boolean flag=false;
		String rex="/^[1-9]\\d{4,11}$/";
		Pattern p=Pattern.compile(rex);
		Matcher m=p.matcher(qq);
		if(m.find()){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 判断密码长度是否由字母和数字组成,并且长度在8到20之间
	 * @param pwd
	 * @return
	 */
	public static boolean verifyPwd(String pwd){
	     boolean flag=false;
		 Pattern pat = Pattern.compile("[\\da-zA-Z]{8,20}");
	     Pattern patno = Pattern.compile(".*\\d.*");
	     Pattern paten = Pattern.compile(".*[a-zA-Z].*");
	     Matcher mat = pat.matcher(pwd);
	     Matcher matno = patno.matcher(pwd);
	     Matcher maten = paten.matcher(pwd);
	     if(matno.matches()&& maten.matches() && mat.matches()){
	    	 flag=true;
	     }
	     return flag;
	}
     
}
