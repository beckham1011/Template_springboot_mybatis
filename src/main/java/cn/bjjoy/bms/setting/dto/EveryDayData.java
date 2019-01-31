package cn.bjjoy.bms.setting.dto;

import java.io.Serializable;

public class EveryDayData implements Serializable{

	/**
	 */
	private static final long serialVersionUID = -4127887399513051142L;
	
	private int equiptypeId ;
	private String addressCode;
	private String name; 
	private String addTime ;
	private double areCumulativeHis ;
	private String bengxing ;
	private String koujing ;
	private String gonglv ;
	
	public int getEquiptypeId() {
		return equiptypeId;
	}
	public void setEquiptypeId(int equiptypeId) {
		this.equiptypeId = equiptypeId;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public double getAreCumulativeHis() {
		return areCumulativeHis;
	}
	public void setAreCumulativeHis(double areCumulativeHis) {
		this.areCumulativeHis = areCumulativeHis;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
