package cn.bjjoy.bms.setting.persist.model;

import java.io.Serializable;
import java.util.Date;

public class Tenant extends cn.bjjoy.bms.setting.persist.model.System implements Serializable{

	/**
	 */
	private static final long serialVersionUID = -307719590634464267L;
	
	private int id ;
	private String name ;
	
	//添加时间
	private Date addTime ;
	
	//租期生效时间
	private Date validTime ;
	//是否在使用
	private String isUsed;
	//是否删除
	private String isDel;
	
	//租期，单位: 月
	private int renantMonth;
	
	//联系人、电话
	private String contractPerson ;
	private String contractTelphone ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getValidTime() {
		return validTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public int getRenantMonth() {
		return renantMonth;
	}
	public void setRenantMonth(int renantMonth) {
		this.renantMonth = renantMonth;
	}
	public String getContractPerson() {
		return contractPerson;
	}
	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}
	public String getContractTelphone() {
		return contractTelphone;
	}
	public void setContractTelphone(String contractTelphone) {
		this.contractTelphone = contractTelphone;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
}
