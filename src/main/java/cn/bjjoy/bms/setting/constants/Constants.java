package cn.bjjoy.bms.setting.constants;

public class Constants {

	public static final String CHARSET_UTF8 = "UTF-8";

	public static final String Y = "Y";
	public static final String N = "N";

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
	
	//系统默认发送人
	public static final String MESSAGE_SENDER_ID = "1";
}