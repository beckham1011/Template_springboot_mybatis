package cn.bjjoy.bms.setting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CurrentEquipData {
/***
 
 	select eqda.addresscode ,eqda.add_time ,eqda.waterstatus ,eqda.flowrate , eqda.netcumulative , eqda.signalquality , eqda.communicationStatus , 
	eqtp.name  , eqtp.bengxing  , eqtp.koujing  , eqtp.gonglv  , eqtp.id  
	from equipdata eqda
	inner join ( select max(id) mxid from equipdata   group by addressCode ) out2 on eqda.id = out2.mxid 
	LEFT JOIN equiptype eqtp on eqda.addresscode = eqtp.addresscode 
 	order by eqda.id 
 
 * 
 * 
 */
	private int id; 
	private String name ;
	private String addresscode ;
	
	private String bengxing ;
	private String koujing ;
	private String gonglv ;
	
	private String waterstatus ;
	private String flowrate ;
	private String netcumulative ;
	
	private String signalquality ;
	private String communicationStatus ;
	
	private String add_time ;
	private String update_time ;

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

	public String getAddresscode() {
		return addresscode;
	}

	public void setAddresscode(String addresscode) {
		this.addresscode = addresscode;
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

	public String getWaterstatus() {
		return waterstatus;
	}

	public void setWaterstatus(String waterstatus) {
		this.waterstatus = waterstatus;
	}

	public String getFlowrate() {
		return flowrate;
	}

	public void setFlowrate(String flowrate) {
		this.flowrate = flowrate;
	}

	public String getNetcumulative() {
		return netcumulative;
	}

	public void setNetcumulative(String netcumulative) {
		this.netcumulative = netcumulative;
	}

	public String getSignalquality() {
		return signalquality;
	}

	public void setSignalquality(String signalquality) {
		this.signalquality = signalquality;
	}

	public String getCommunicationStatus() {
		return communicationStatus;
	}

	public void setCommunicationStatus(String communicationStatus) {
		this.communicationStatus = communicationStatus;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
}
