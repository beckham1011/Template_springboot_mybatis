package cn.bjjoy.bms.setting.constants;

import java.io.File;

public class Constants {

	public static final String CHARSET_UTF8 = "UTF-8";

	public static final String ALL = "all" ;
	
	public static final String MSG_8082 = "01041010001674C1" ;
	public static final String MSG_8084 = "010300020004E5C9" ;
	
	public static final String SOCKET_SERVER_IP = "127.0.0.1";
	
	public static final Integer SOCKET_8082_PORT = 8082 ;
	public static final Integer SOCKET_8084_PORT = 8084 ;
	
	public static final String Y = "Y";
	public static final String N = "N";

	
	public static final String USER_DEFAULT_PSSSWROD = "123456";
	public static final String USER_TOKEN = "authtoken";
	
	public final static int HTTP_200 = 200; // 操作成功
	public final static int HTTP_400 = 400; // 请求无效
	public final static int HTTP_401 = 401; // token验证失败
	public final static int HTTP_403 = 403; // 访问被拒绝
	public final static int HTTP_500 = 500; // 服务器错
	
	public static final String PAGE = "page";
	public static final String ROWS = "rows";
	public static final String START = "start";
	public static final String TOTALITEMS = "totalItems";
	public static final String PAGENO = "pageNo";
	public static final String PAGESIZE = "pageSize";
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String OBJ = "obj";
	public static final String LIST = "list";
	public static final String IS_DEL = "isDel";

	// 文件类型
	public static final String RESOURCE_TYPE_VIDEO = "VIDEO";
	public static final String RESOURCE_TYPE_VOICE = "VOICE";
	public static final String RESOURCE_TYPE_IMAGE = "IMAGE";
	public static final String RESOURCE_TYPE_ATTACHMENT = "ATTACHMENT";
	
	public static final String MESSAGE_STATUS_READ = "READ";// 已读
	public static final String MESSAGE_STATUS_UNREAD = "UNREAD";// 未读
	

	public static final int LENGTH_RAW = 2 ;
	public static final int LENGTH_MORE = 11 ;
	
	
	//系统默认发送人
	public static final String MESSAGE_SENDER_ID = "1";
	
	public static String MAIL_SENDER_HOST = "smtp.126.com" ;
	public static String MAIL_SENDER_ACCOUNT = "xuzj850329@126.com" ;
	public static String MAIL_SENDER_PASSWORD = "OIvyx7" ;

	public static String MAIL_RECEIVER_ADMIN = "892285199@qq.com" ;
	public static String MAIL_RECEIVER_SUB_ADMIN = "512839244@qq.com" ;
	
	public final static String FILE_PATH = "C:" + File.separator + "vbdata" + File.separator + "socket8082data.txt";
	
}