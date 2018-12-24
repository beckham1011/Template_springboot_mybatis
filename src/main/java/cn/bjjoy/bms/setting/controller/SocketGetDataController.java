package cn.bjjoy.bms.setting.controller;import java.io.DataOutputStream;import java.io.IOException;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.bind.annotation.RestController;import cn.bjjoy.bms.base.ResponseResult;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.mail.SendMail;import cn.bjjoy.bms.setting.service.EquiptypeService;import cn.bjjoy.bms.setting.service.impl.EquiptypeServiceImpl;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.SpringSocketUtil;/** * 类描述   :  * 创建人	：system * 创建时间 ：2018-09-13 23:02:18 * @version 1.0 */@SuppressWarnings("rawtypes")@RestController@CrossOriginpublic class SocketGetDataController extends BaseController {//	public static final String IP_ADDR = "101.132.126.72";//服务器地址    	@Autowired	SendMail sendMail ;		@Autowired	EquiptypeService typeService ;		@Description("页面请求更新数据")	@RequestMapping(value="socket8082")	@ResponseBody	public ResponseResult synchronizedEquipdata8082(@RequestParam Map map) throws ControllerException {				try {			String value = (String) map.get("param");			DataOutputStream out = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());    			out.write(value.getBytes());		} catch (IOException e) {			e.printStackTrace();		}		return ResponseResult.ok("刷新成功");	}			@RequestMapping(value="socket")	public ResponseResult socketRefresh(@RequestParam Map map) throws ControllerException {		EquiptypeService typeService  = SpringSocketUtil.getBean(EquiptypeServiceImpl.class) ;		String addPerson = "hs1188" ;		try {			String value = (String) map.get("param");			String ip = typeService.getIPByAddressCode(value);			//8082			logger.info("Receive code get ip : " + ip);			if("hs1188".equals(addPerson)){				DataOutputStream out = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());    				out.write(ip.getBytes());			}else if("dfsl".equalsIgnoreCase(addPerson)){				//8084			}else if(addPerson != null){				logger.info("send get data msg to 8082 and 8084");				DataOutputStream out8082 = new DataOutputStream(SingletonSocket.getInstance().getSocket8082().getOutputStream());				out8082.write(ip.getBytes());			}		} catch (IOException e) {			e.printStackTrace();		}		return ResponseResult.ok("刷新成功");	}			@Description("页面请求更新数据")	@RequestMapping(value="socket8084")	public Response synchronizedEquipdata8084(@RequestParam Map map) throws ControllerException {		try {			String value = (String) map.get("param");		} catch (Exception e) {			e.printStackTrace();		}		return null ;	}		}