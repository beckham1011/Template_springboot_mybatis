package cn.bjjoy.bms.enumutil;

public enum PageEnum {

	DataIndex("DataIndex" ,"实时监测"),
	TypeIndex("TypeIndex" ,"实时数据监控"),
	HistoryIndex("HistoryIndex" ,"记录查询"),
	ChartIndex("ChartIndex" ,"汇总统计"),
	;
	
	public String code;
	public String name;

	private PageEnum(String code ,String name) {
		this.code = code;
		this.name = name;
	}
	
}
