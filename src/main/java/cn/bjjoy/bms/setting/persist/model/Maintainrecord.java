package cn.bjjoy.bms.setting.persist.model;import java.io.Serializable;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */public class Maintainrecord implements Serializable {	/**	 */	private static final long serialVersionUID = -998651389383285351L;	/**  */	private int id;	/**  */	private String startTime;	/**  */	private String endTime;	/**  */	private String method;	/**  */	private String maintainPer;	/**  */	private String Description;	/**  */	private String results;	/**  */	private String money;	/**  */	private int DeclarationRecordId;	/**  */	private String reason;	 	/** 	 * 获取 主键	 */		public int getId() {		return id;	}	/** 	 * 设置 主键	 */	public void setId(int id) {		this.id = id;	}	 	/** 	 * 获取 	 */		public String getStartTime() {		return startTime;	}	/** 	 * 设置 	 */	public void setStartTime(String startTime) {		this.startTime = startTime;	}	 	/** 	 * 获取 	 */		public String getEndTime() {		return endTime;	}	/** 	 * 设置 	 */	public void setEndTime(String endTime) {		this.endTime = endTime;	}	 	/** 	 * 获取 	 */		public String getMethod() {		return method;	}	/** 	 * 设置 	 */	public void setMethod(String method) {		this.method = method;	}	 	/** 	 * 获取 	 */		public String getMaintainPer() {		return maintainPer;	}	/** 	 * 设置 	 */	public void setMaintainPer(String maintainPer) {		this.maintainPer = maintainPer;	}	 	/** 	 * 获取 	 */		public String getDescription() {		return Description;	}	/** 	 * 设置 	 */	public void setDescription(String Description) {		this.Description = Description;	}	 	/** 	 * 获取 	 */		public String getResults() {		return results;	}	/** 	 * 设置 	 */	public void setResults(String results) {		this.results = results;	}	 	/** 	 * 获取 	 */		public String getMoney() {		return money;	}	/** 	 * 设置 	 */	public void setMoney(String money) {		this.money = money;	}	 	/** 	 * 获取 	 */		public int getDeclarationRecordId() {		return DeclarationRecordId;	}	/** 	 * 设置 	 */	public void setDeclarationRecordId(int DeclarationRecordId) {		this.DeclarationRecordId = DeclarationRecordId;	}	 	/** 	 * 获取 	 */		public String getReason() {		return reason;	}	/** 	 * 设置 	 */	public void setReason(String reason) {		this.reason = reason;	}}