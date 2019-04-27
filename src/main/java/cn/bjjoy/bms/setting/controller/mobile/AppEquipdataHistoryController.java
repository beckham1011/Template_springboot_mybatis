package cn.bjjoy.bms.setting.controller.mobile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bjjoy.bms.setting.controller.AbstractHosznController;

@SuppressWarnings({"rawtypes"})
@Controller
@RequestMapping("app/history")
public class AppEquipdataHistoryController extends AbstractHosznController{

	private static final Logger log = LogManager.getLogger();
	
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ;
	
    @GetMapping("index" )
    public String index(@RequestParam Map paramMap , ModelMap modelMap) {
		modelMap.addAttribute("parentId", paramMap.containsKey("parentId") ? paramMap.get("parentId") : "1") ;
		log.info("app/history index page....");
		return "app/history/index";
    }

}
