package cn.bjjoy.bms.setting.persist.model;import java.io.Serializable;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */public class Declarationrecord implements Serializable {	/**  */	private int id;	/**  */	private String unitName;	/**  */	private String MeasuringPoint;	/**  */	private String failure;	/**  */	private String reportingTime;	/**  */	private String reportingPer;	/**  */	private String reportingPhone;	/**  */	private String acceptTime;	/**  */	private String acceptPer;	/**  */	private String acceptPhone;	/**  */	private String results;	/**  */	private String failureDescribe;	/**  */	private String addTime;	 	/** 	 * 获取 主键	 */		public int getId() {		return id;	}	/** 	 * 设置 主键	 */	public void setId(int id) {		this.id = id;	}	 	/** 	 * 获取 	 */		public String getUnitName() {		return unitName;	}	/** 	 * 设置 	 */	public void setUnitName(String unitName) {		this.unitName = unitName;	}	 	/** 	 * 获取 	 */		public String getMeasuringPoint() {		return MeasuringPoint;	}	/** 	 * 设置 	 */	public void setMeasuringPoint(String MeasuringPoint) {		this.MeasuringPoint = MeasuringPoint;	}	 	/** 	 * 获取 	 */		public String getFailure() {		return failure;	}	/** 	 * 设置 	 */	public void setFailure(String failure) {		this.failure = failure;	}	 	/** 	 * 获取 	 */		public String getReportingTime() {		return reportingTime;	}	/** 	 * 设置 	 */	public void setReportingTime(String reportingTime) {		this.reportingTime = reportingTime;	}	 	/** 	 * 获取 	 */		public String getReportingPer() {		return reportingPer;	}	/** 	 * 设置 	 */	public void setReportingPer(String reportingPer) {		this.reportingPer = reportingPer;	}	 	/** 	 * 获取 	 */		public String getReportingPhone() {		return reportingPhone;	}	/** 	 * 设置 	 */	public void setReportingPhone(String reportingPhone) {		this.reportingPhone = reportingPhone;	}	 	/** 	 * 获取 	 */		public String getAcceptTime() {		return acceptTime;	}	/** 	 * 设置 	 */	public void setAcceptTime(String acceptTime) {		this.acceptTime = acceptTime;	}	 	/** 	 * 获取 	 */		public String getAcceptPer() {		return acceptPer;	}	/** 	 * 设置 	 */	public void setAcceptPer(String acceptPer) {		this.acceptPer = acceptPer;	}	 	/** 	 * 获取 	 */		public String getAcceptPhone() {		return acceptPhone;	}	/** 	 * 设置 	 */	public void setAcceptPhone(String acceptPhone) {		this.acceptPhone = acceptPhone;	}	 	/** 	 * 获取 	 */		public String getResults() {		return results;	}	/** 	 * 设置 	 */	public void setResults(String results) {		this.results = results;	}	 	/** 	 * 获取 	 */		public String getFailureDescribe() {		return failureDescribe;	}	/** 	 * 设置 	 */	public void setFailureDescribe(String failureDescribe) {		this.failureDescribe = failureDescribe;	}	 	/** 	 * 获取 	 */		public String getAddTime() {		return addTime;	}	/** 	 * 设置 	 */	public void setAddTime(String addTime) {		this.addTime = addTime;	}}