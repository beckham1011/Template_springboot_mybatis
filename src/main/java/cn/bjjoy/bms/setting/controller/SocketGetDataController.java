package cn.bjjoy.bms.setting.controller;import java.io.DataOutputStream;import java.io.IOException;import java.net.Socket;import java.util.List;import java.util.Map;import java.util.Objects;import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.mail.SendMail;import cn.bjjoy.bms.setting.constants.Constants;import cn.bjjoy.bms.setting.persist.model.Equiptype;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.setting.service.SystemService;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.UserUtils;/** * 类描述   :  * 创建人	：ZhijieXu * 创建时间 ：2018-10-13 23:02:18 * @version 1.0 */@SuppressWarnings("rawtypes")@RestController@CrossOriginpublic class SocketGetDataController extends AbstractHosznController {	private static final Logger logger = LogManager.getLogger();	//	public static final String IP_ADDR = "101.132.126.72";//服务器地址		@Autowired	SendMail sendMail ;		@Autowired	EquiptypeService equipService ;		@Autowired	SystemService sysService;		@Description("页面请求更新数据")	@GetMapping("refreshAll")	public ResponseResult synchronizedEquipdata8082(@RequestParam Map map) throws ControllerException {				try {			DataOutputStream refreshData8082Socket = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());			int parentId = UserUtils.getUer().getParentId();			refreshData8082Socket.write((Constants.ALL + "_" + parentId).getBytes()) ;					} catch (IOException e) {			e.printStackTrace();		}		return ResponseResult.ok("刷新成功");	}		/**需求重现：	 * 1，不同的前端发送不同请求	 * 	 * @param map	 * @return	 * @throws ControllerException	 */	@GetMapping(value="socket")	public ResponseResult socketRefresh(@RequestParam Map map) throws ControllerException {		try {			Equiptype equip = equipService.getEquipByAddressCode((String)map.get("param"));			if(equip != null && equip.getIP() != null && Objects.nonNull(equip.getId())) {				String ip = equip.getIP();				int systemId = equip.getSystemId();								logger.info("Receive code get ip : " + ip +", systemId:" + systemId);								Socket socket = SingletonSocket.getInstance().getSocketBySystemId(systemId);				DataOutputStream out = new DataOutputStream(socket.getOutputStream());    				out.write(ip.getBytes());			}		} catch (IOException e) {			e.printStackTrace();		}		return ResponseResult.ok("刷新成功");	}		@GetMapping(value="refreshAllOneByOne")	public ResponseResult refreshAll(@RequestParam Map map) throws ControllerException, IOException {		try {			DataOutputStream refreshData8082Socket = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());			List<Equiptype> equips = equipService.getEquipsByParentId(Integer.valueOf(1));			equips.stream().forEach( equip ->{				try {					refreshData8082Socket.write(equip.getAddressCode().getBytes()) ;				} catch (IOException e) {					e.printStackTrace();				}			});		}finally {					}		return ResponseResult.ok("刷新成功");	}						@Description("页面请求更新数据")	@GetMapping("socket8084")	public Response synchronizedEquipdata8084(@RequestParam Map map) throws ControllerException {		try {			String value = (String) map.get("param");			logger.info("socket8084: " + value);		} catch (Exception e) {			e.printStackTrace();		}		return null ;	}		}