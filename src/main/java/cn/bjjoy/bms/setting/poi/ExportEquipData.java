package cn.bjjoy.bms.setting.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ExportEquipData {

	@Excel(name = "镇", orderNum = "0")
	private String p2name ;
	
	@Excel(name = "村", orderNum = "1")
	private String p3name ;
	
	@Excel(name = "泵站名称", orderNum = "2" , width=20)
	private String name ;
	
	@Excel(name = "地址码", orderNum = "3")
	private String addresscode ;
	
	@Excel(name = "泵型", orderNum = "4")
	private String bengxing ;
	
	@Excel(name = "口径", orderNum = "5")
	private String koujing ;
	
	@Excel(name = "功率", orderNum = "6")
	private String gonglv ;
	
	@Excel(name = "正累计(M3/h)", orderNum = "7" , width=10)
	private String netcumulative ;
	
	@Excel(name = "更新时间", orderNum = "8" ,width=20)
	private String date;

	public String getP2name() {
		return p2name;
	}

	public void setP2name(String p2name) {
		this.p2name = p2name;
	}

	public String getP3name() {
		return p3name;
	}

	public void setP3name(String p3name) {
		this.p3name = p3name;
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

	public String getNetcumulative() {
		return netcumulative;
	}

	public void setNetcumulative(String netcumulative) {
		this.netcumulative = netcumulative;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
