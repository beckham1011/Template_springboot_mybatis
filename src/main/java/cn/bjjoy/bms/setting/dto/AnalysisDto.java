package cn.bjjoy.bms.setting.dto;

public class AnalysisDto {

	private String addressCode ;
	private String name ;
	
	private double mxflwrate ;
	private double mnflwrate ;
	private double avgflwrate ;
	
	private double mxcumlate ;
	private double mncumlate ;
	private double avgcumlate ;
	
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMxflwrate() {
		return mxflwrate;
	}
	public void setMxflwrate(double mxflwrate) {
		this.mxflwrate = mxflwrate;
	}
	public double getMnflwrate() {
		return mnflwrate;
	}
	public void setMnflwrate(double mnflwrate) {
		this.mnflwrate = mnflwrate;
	}
	public double getAvgflwrate() {
		return avgflwrate;
	}
	public void setAvgflwrate(double avgflwrate) {
		this.avgflwrate = avgflwrate;
	}
	public double getMxcumlate() {
		return mxcumlate;
	}
	public void setMxcumlate(double mxcumlate) {
		this.mxcumlate = mxcumlate;
	}
	public double getMncumlate() {
		return mncumlate;
	}
	public void setMncumlate(double mncumlate) {
		this.mncumlate = mncumlate;
	}
	public double getAvgcumlate() {
		return avgcumlate;
	}
	public void setAvgcumlate(double avgcumlate) {
		this.avgcumlate = avgcumlate;
	}

}
