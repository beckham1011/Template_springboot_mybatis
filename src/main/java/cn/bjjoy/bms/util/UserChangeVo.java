package cn.bjjoy.bms.util;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class UserChangeVo implements Cloneable {

	/** @Fields serialVersionUID: */
	private static final long serialVersionUID = 1L;

	@Excel(name = "总数", width = 35)
	private String userSum;// 用户总数

	@Excel(name = "新增", width = 35)
	private String addSum;// 新增户数

	@Excel(name = "报停", width = 35)
	private String btSum;// 报停总户数

	@Excel(name = "暂拆", width = 35)
	private String zcSum;// 暂拆户数

	@Excel(name = "销户户数", width = 35)
	private String xhSum;// 销户户数

	public String getUserSum() {
		return userSum;
	}

	public void setUserSum(String userSum) {
		this.userSum = userSum;
	}

	public String getAddSum() {
		return addSum;
	}

	public void setAddSum(String addSum) {
		this.addSum = addSum;
	}

	public String getBtSum() {
		return btSum;
	}

	public void setBtSum(String btSum) {
		this.btSum = btSum;
	}

	public String getZcSum() {
		return zcSum;
	}

	public void setZcSum(String zcSum) {
		this.zcSum = zcSum;
	}

	public String getXhSum() {
		return xhSum;
	}

	public void setXhSum(String xhSum) {
		this.xhSum = xhSum;
	}
	
}
