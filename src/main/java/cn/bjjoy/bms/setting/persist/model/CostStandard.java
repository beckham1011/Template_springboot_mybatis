package cn.bjjoy.bms.setting.persist.model;

public class CostStandard {

	private String id ;
	private String costStandard ;
	
	public CostStandard(String id, String costStandard) {
		super();
		this.id = id;
		this.costStandard = costStandard;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCostStandard() {
		return costStandard;
	}
	public void setCostStandard(String costStandard) {
		this.costStandard = costStandard;
	}
	
}
