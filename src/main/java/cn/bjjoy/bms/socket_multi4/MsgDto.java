package cn.bjjoy.bms.socket_multi4;

import java.io.Serializable;

public class MsgDto implements Serializable{

	/**
	 */
	private static final long serialVersionUID = -1946347746867001534L;
	
	private String type ;
	private String msg ;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
