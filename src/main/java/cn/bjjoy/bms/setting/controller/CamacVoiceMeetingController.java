package cn.bjjoy.bms.setting.controller;import java.util.List;import java.util.Map;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Description;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;import cn.bjjoy.bms.exception.ControllerException;import cn.bjjoy.bms.setting.entity.CamacVoiceMeeting;import cn.bjjoy.bms.setting.service.CamacVoiceMeetingService;import cn.bjjoy.bms.util.MapUtils;import cn.bjjoy.bms.util.Page;import cn.bjjoy.bms.util.PageUtils;import cn.bjjoy.bms.util.Response;import cn.bjjoy.bms.util.StringUtils;/** * 类描述   : CAMAC_会议 * 创建人	：system * 创建时间 ：2018-09-12 16:16:57 * @version 1.0 */@Controller@CrossOriginpublic class CamacVoiceMeetingController extends BaseController {	@Autowired	private CamacVoiceMeetingService camacVoiceMeetingService;		@Description("CAMAC_会议-添加")	@RequestMapping(value="camac/voice/meeting.save")	public Response save(CamacVoiceMeeting camacVoiceMeeting) throws ControllerException {		camacVoiceMeetingService.save(camacVoiceMeeting);		return Response.success(camacVoiceMeeting.getMeetingId());	}		@Description("CAMAC_会议-删除")	@RequestMapping(value="camac/voice/meeting.delete")	public Response delete(String meetingId) throws ControllerException {		if(StringUtils.isEmpty(meetingId))			return Response.error("meetingId不能为空");		camacVoiceMeetingService.deleteOne(meetingId);		return Response.success();	}	@Description("CAMAC_会议-根据主键查询")	@RequestMapping(value="camac/voice/meeting.one")	public Response one(String meetingId) throws ControllerException {		if(StringUtils.isEmpty(meetingId))			return Response.error("meetingId不能为空");		CamacVoiceMeeting camacVoiceMeeting = camacVoiceMeetingService.queryOne(meetingId);		return Response.success(camacVoiceMeeting);	}		@Description("CAMAC_会议-查询列表")	@RequestMapping(value="camac/voice/meeting.list")	public Response list(@RequestParam Map<String, Object> map) throws ControllerException {		boolean pageOK = PageUtils.isOK(map);				if(!pageOK)			return Response.error("分页参数错误");				long total = camacVoiceMeetingService.queryMapListCount(map);				Page page = PageUtils.initPage(map, total);				List<Map<String,Object>> list = camacVoiceMeetingService.queryMapList(map);		return Response.success(list, page);	}		@Description("CAMAC_会议-根据主键修改")	@RequestMapping(value="camac/voice/meeting.update")	public Response update(@RequestParam Map<String, Object> map) throws ControllerException {		if(MapUtils.isEmpty(map, "meetingId"))			return Response.error("meetingId不能为空");		camacVoiceMeetingService.updateOne(map);		return Response.success();	}}