package cn.bjjoy.bms.setting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.dto.UserDto;
import cn.bjjoy.bms.util.DataUtils;

@SuppressWarnings({"rawtypes"})
@Controller
@CrossOrigin
@RequestMapping("ajaxuser")
public class AjaxUserController extends AbstractHosznController{

    @GetMapping(value="getUserList")
    @ResponseBody
	public ResponseResult test(@RequestParam Map<String, Object> map){
		List<UserDto> userList = null;
		List<Map> userMapList = this.userService.getList(map);
		//没有用户数据直接返回
		if(userMapList == null || userMapList.size() == 0){
		    Map<String, Object> responseResult = new HashMap<>();
		    responseResult.put("userList", new ArrayList<>());
		    responseResult.put("count", 0);
		    return ResponseResult.ok(responseResult);
		}

		userList = DataUtils.getDataArray(userMapList, UserDto.class);

		List<Integer> userIdList = new ArrayList<>();
		for(UserDto userDto : userList){
		    userIdList.add(userDto.getId());
		}
		
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("userList",userList);
        return ResponseResult.ok(responseResult);
	}
	
	
}
