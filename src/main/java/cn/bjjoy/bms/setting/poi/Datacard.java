package cn.bjjoy.bms.setting.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Datacard {
	/**  */
	@Excel(name = "单位名称", orderNum = "0")
	private String unitName;
	/**  */
	@Excel(name = "设备名称", orderNum = "1")
	private String equipName;
	/**  */
	@Excel(name = "手机卡号", orderNum = "2")
	private String mobilePhone;
	
	@Excel(name = "资费标准", orderNum = "3")
	private String costStandard;
	/**  */
	@Excel(name = "充值金额", orderNum = "4")
	private String amount;
	/**  */
	@Excel(name = "充值时间", orderNum = "5")
	private String refillTime;
	/**  */
	@Excel(name = "当前余额", orderNum = "6")
	private String balance;
	/**  */
	@Excel(name = "当前状态", orderNum = "7")
	private String currentState;
	public Datacard(String unitName, String equipName, String mobilePhone,
			String amount, String refillTime, String balance,
			String currentState, String costStandard) {
		super();
		this.unitName = unitName;
		this.equipName = equipName;
		this.mobilePhone = mobilePhone;
		this.amount = amount;
		this.refillTime = refillTime;
		this.balance = balance;
		this.currentState = currentState;
		this.costStandard = costStandard;
	}
	
	public Datacard() {
	}

	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRefillTime() {
		return refillTime;
	}
	public void setRefillTime(String refillTime) {
		this.refillTime = refillTime;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getCostStandard() {
		return costStandard;
	}
	public void setCostStandard(String costStandard) {
		this.costStandard = costStandard;
	}

}
