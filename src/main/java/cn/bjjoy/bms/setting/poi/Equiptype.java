package cn.bjjoy.bms.setting.poi;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Equiptype implements Serializable{

	/**
	 */
	private static final long serialVersionUID = 473384717268222838L;

	/**  */
	@Excel(name = "泵站名称", width = 20, orderNum = "1")
	private String name;
	
	@Excel(name = "地址码", width = 20, orderNum = "2")
	private String addressCode;
	/**  */
	@Excel(name = "经度", width = 20, orderNum = "7")
	private String longitude;
	/**  */
	@Excel(name = "纬度", width = 20, orderNum = "8")
	private String latitude;
	/**  */
	
	@Excel(name = "镇管理员", width = 20, orderNum = "9")
	private String zguanliPer;
	/**  */
	@Excel(name = "镇管理员电话", width = 20, orderNum = "10")
	private String zguanliPhone;
	
	@Excel(name = "村管理员", width = 20, orderNum = "11")
	private String cguanliPer;
	/**  */
	@Excel(name = "村管理员电话", width = 20, orderNum = "12")
	private String cguanliPhone;
	/**  */
	@Excel(name = "具体管理人员", width = 20, orderNum = "13")
	private String jguanliPer;
	/**  */
	@Excel(name = "具体管理人员电话", width = 20, orderNum = "14")
	private String jguanliPhone;
	/**  */
	
	@Excel(name = "泵型", width = 20, orderNum = "3")
	private String bengxing;
	/**  */
	@Excel(name = "口径", width = 20, orderNum = "4")
	private String koujing;
	/**  */
	@Excel(name = "功率", width = 20, orderNum = "5")
	private String gonglv;
	/**  */
	@Excel(name = "型号", width = 20, orderNum = "6")
	private String xinhao;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getZguanliPer() {
		return zguanliPer;
	}
	public void setZguanliPer(String zguanliPer) {
		this.zguanliPer = zguanliPer;
	}
	public String getZguanliPhone() {
		return zguanliPhone;
	}
	public void setZguanliPhone(String zguanliPhone) {
		this.zguanliPhone = zguanliPhone;
	}
	public String getCguanliPer() {
		return cguanliPer;
	}
	public void setCguanliPer(String cguanliPer) {
		this.cguanliPer = cguanliPer;
	}
	public String getCguanliPhone() {
		return cguanliPhone;
	}
	public void setCguanliPhone(String cguanliPhone) {
		this.cguanliPhone = cguanliPhone;
	}
	public String getJguanliPer() {
		return jguanliPer;
	}
	public void setJguanliPer(String jguanliPer) {
		this.jguanliPer = jguanliPer;
	}
	public String getJguanliPhone() {
		return jguanliPhone;
	}
	public void setJguanliPhone(String jguanliPhone) {
		this.jguanliPhone = jguanliPhone;
	}
	public String getBengxing() {
		return bengxing;
	}
	public void setBengxing(String bengxing) {
		this.bengxing = bengxing;
	}
	public String getKoujing() {
		return koujing;
	}
	public void setKoujing(String koujing) {
		this.koujing = koujing;
	}
	public String getGonglv() {
		return gonglv;
	}
	public void setGonglv(String gonglv) {
		this.gonglv = gonglv;
	}
	public String getXinhao() {
		return xinhao;
	}
	public void setXinhao(String xinhao) {
		this.xinhao = xinhao;
	}
	
}

