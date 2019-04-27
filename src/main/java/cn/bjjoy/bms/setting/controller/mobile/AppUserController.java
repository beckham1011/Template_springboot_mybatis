package cn.bjjoy.bms.setting.controller.mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bjjoy.bms.setting.controller.AbstractHosznController;

@CrossOrigin
@Controller
@RequestMapping("app/user")
public class AppUserController extends AbstractHosznController{

	@GetMapping(value = "/index" )
    public String index() {
        return "app/user/index";
    }
	
	@GetMapping(value = "/add" )
    public String appUserAdd() {
        return "app/user/add";
    }
	
}
