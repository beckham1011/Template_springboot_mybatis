package cn.bjjoy.bms.setting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.Codes;
import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.dto.EquiptypeDto;
import cn.bjjoy.bms.setting.dto.UserDto;
import cn.bjjoy.bms.setting.dto.UserRoleDto;
import cn.bjjoy.bms.setting.entity.Role;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.persist.model.Equiptype;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.DateUtils;
import cn.bjjoy.bms.util.EncryptUtils;
import cn.bjjoy.bms.util.UserUtils;

/**
 * @author bjjoy
 * @date 2017/8/28
 */
@CrossOrigin
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("user")
public class UserController extends AbstractHosznController {

	private static final Logger logger = LogManager.getLogger();

	/**
	 * 跳转添加用户页面
	 */
	@GetMapping("add")
	public String add(ModelMap map) {
		User user = UserUtils.getUer() ;
		map.put("parentId", user.getParentId()) ;
		int systemId = equiptypeService.getUserSystemId(UserUtils.getUer().getId()) ;
		if(systemId != 0){
			map.put("systemId", String.valueOf(systemId)) ;
		}
		
		map.put("currentOrg", equiptypeService.queryOne(String.valueOf(user.getParentId())));
		List<Map<String, Object>> types = equiptypeService.queryDirectSubTypes(map);
		LinkedList<EquiptypeDto> subTypeList = DataUtils.getDataArray(types, EquiptypeDto.class);
		map.addAttribute("subTypeList" + (map.get("typeLayer") == null ? "1" : map.get("typeLayer")),subTypeList) ;
		return "/user/add";
	}

    /**
     * 生成用户
     * @param userDto
     */
    @ResponseBody
    @PostMapping("insert")
    public ResponseResult insertOrUpdate(UserDto userDto){
    	String currentTime = DateUtils.getCurrentDate();
    	userDto.setCreateDate(currentTime);
    	userDto.setUpdateDate(currentTime);
		if(StringUtils.isNotBlank(userDto.getPassword())){
			userDto.setPassword(EncryptUtils.encryptMD5(userDto.getPassword()));
		}else {
			return ResponseResult.error(Codes.PARAM_ERROR);
		}
		
		try {
			this.userService.insert(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
			e.printStackTrace();
		}
		Integer userId = userDto.getId();
        return ResponseResult.ok(userId);
    }

    /**
     * 获取用户详情
     */
    @GetMapping("getUser")
    public ResponseResult getUser(@RequestParam Integer id){
        UserDto userDto = null;
		try {
			User user = this.userService.getUserDetail(id);
			userDto = DataUtils.getData(user, UserDto.class);
			List<Role> roleList = this.roleService.getList(new HashMap());
			List<UserRoleDto> userRoleList = DataUtils.getDataArray(roleList, UserRoleDto.class);
			userDto.setRoleList(userRoleList);

			//获取角色信息
			List<Integer> userIdList = new ArrayList<>();
			userIdList.add(id);
			List<UserRoleDto> userRoleDtoList = this.userRoleService.getUserRoleList(userIdList);

			//用户对应角色列表是否被选中
			for (UserRoleDto userRoleDto : userDto.getRoleList()){
			    for (UserRoleDto selectRole : userRoleDtoList){
			        if (userRoleDto.getId().equals(selectRole.getRoleId())){
			            userRoleDto.setIsSelect(1);
			        }
			    }
			}
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}

        return ResponseResult.ok(userDto);
    }

	/**
	 * 跳转到修改用户页面
	 * @param id
	 */
	@GetMapping("edit/{id}")
	public String toEdit(@PathVariable Integer id, ModelMap map){
		User user = userService.getUserDetail(id);
		map.addAttribute("userDto", user);
		Equiptype typeToEdit = equiptypeService.queryOne(String.valueOf(user.getParentId()));
		map.put("userSystemId", equiptypeService.getUserSystemId(id)) ;
		map.addAttribute("systemList" , equiptypeService.getSystems()) ;
		if("4".equals(typeToEdit.getTypeLayer())){
			map.addAttribute("parentParentTypes" , equiptypeService.queryOne(String.valueOf(equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId())).getParentId()))) ;
			map.addAttribute("parentTypes" , equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId()))) ;
		}else if("3".equals(typeToEdit.getTypeLayer())){
			map.addAttribute("parentParentTypes" , equiptypeService.queryOne(String.valueOf(typeToEdit.getParentId()))) ;
			map.addAttribute("parentTypes" , null) ;
		}else if("2".equals(typeToEdit.getTypeLayer())){
			map.addAttribute("parentParentTypes" , null) ;
			map.addAttribute("parentTypes" , null) ;
		}
		return "/user/edit";
	}

    /**
     * 更新用户信息
     * @param userDto
     */
    @PostMapping("update")
	@ResponseBody
    public ResponseResult update(UserDto userDto){

        if(userDto.getId() == null){
            return ResponseResult.error(Codes.PARAM_ERROR);
        }
        int count = userService.updateUser(userDto);
        return ResponseResult.ok(count);
    }

    /**
     * 用户管理初始化页面
     * @return
     */
    @GetMapping("index" )
    public String index() {
        return "/user/index";
    }

    /**
     * 获取用户列表
     * @param userDtoParam
     */
	
	@SuppressWarnings("unchecked")
	@ResponseBody
    @GetMapping("getList")
    public ResponseResult getList(UserDto userDtoParam, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize){
		Integer count = 0;
		List<UserDto> userList = null;
		try {
			count = 0; 
			
			Map param = new HashMap();
			param.put("pageSize", pageSize);
			param.put("startRow", (pageNumber - 1) * pageSize);
			if (StringUtils.isNotBlank(userDtoParam.getLoginName())){
			    param.put("loginName", userDtoParam.getLoginName());
			}
			User user = UserUtils.getUer() ;
			List<Integer> ids = equiptypeService.getSubTypeIds2(user.getParentId() , null) ;
			param.put("ids", ids);
			List<Map> userMapList = this.userService.getList(param);
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
			//获取用户角色信息
			List<UserRoleDto> userRoleVOList = this.userRoleService.getUserRoleList(userIdList);
			for(UserDto userDto : userList){
			    //添加角色list
			    for(UserRoleDto userRoleDto : userRoleVOList){
			        if(userDto.getId().equals(userRoleDto.getUserId())){
			            if(userDto.getRoleList() == null){
			                userDto.setRoleList(new ArrayList<>());
			                userDto.getRoleList().add(userRoleDto);
			            }else{
			                userDto.getRoleList().add(userRoleDto);
			            }
			        }
			    }
			}

			count = this.userService.getCount(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("userList",userList);
        responseResult.put("count", count);
        return ResponseResult.ok(responseResult);
    }

    /**
     * 删除用户
     * @param userId
     */
    @ResponseBody
    @PostMapping("delete/{userId}")
    public ResponseResult delete(@PathVariable Integer userId){

        if(userId == null){
            return ResponseResult.error(Codes.PARAM_ERROR);
        }
        User user = new User();
        user.setId(userId);
        user.setDelFlag(1);
        userService.deleteUser(user);
        return ResponseResult.ok(user.getId());
    }

    @GetMapping("grant/{id}")
    public String grant(@PathVariable Integer id, ModelMap map) {
        User user = userService.getUserDetail(id);
        map.put("user", user);

        List<Role> roleList = roleService.getListByUserId(id);
        List<Integer> roleIds = new ArrayList<Integer>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        map.put("roleIds", roleIds);

        List<Role> roles = roleService.getList(new HashMap());
        map.put("roles", roles);
        return "/user/grant";
    }

    @Description("打开修改密码页面")
    @GetMapping(value = "changePwd" )
    public String changePwd(@RequestParam Integer id, ModelMap map) {
        User user = userService.getUserDetail(id);
        map.put("password", user.getPassword()) ;
        return "/user/changePwd";
    }

    @Description("修改密码保存")
    @ResponseBody
    @PostMapping(value = "changePwd")
    public ResponseResult changePwdSave(@RequestParam Map map ) {
    	User user = UserUtils.getUer() ;
    	String newPwdAfterEncrypt = EncryptUtils.encryptMD5((String)map.get("newPwd1"));
    	user.setPassword(newPwdAfterEncrypt);
    	try {
			userService.update(user);
			logger.info("Change user password success");
		} catch (Exception e) {
			logger.error("Update user password error: {}" + e.getStackTrace());
			return ResponseResult.error();
		}
        return ResponseResult.ok("");
    }

    
    
//    @RequestMapping("get", method = RequestMethod.GET)
//    public ResponseResult get(User user){
//
//        return ResponseResult.ok(userService.findUserByName("test2"));
//    }
}
