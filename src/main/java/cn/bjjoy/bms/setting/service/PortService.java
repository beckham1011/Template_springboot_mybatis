package cn.bjjoy.bms.setting.service;

/**
    * 设计目标：根据 组织机构端口， 区域端口，租户端口，目前暂时实现租户端口
 * @author Beck-pc
 *
 */
public interface PortService {

	int getPort(String addressCode);
	
}
