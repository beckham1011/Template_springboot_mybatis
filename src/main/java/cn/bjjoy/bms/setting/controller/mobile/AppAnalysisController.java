package cn.bjjoy.bms.setting.controller.mobile;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bjjoy.bms.setting.controller.AbstractHosznController;
import cn.bjjoy.bms.util.DateUtils;

@SuppressWarnings({"rawtypes"})
@CrossOrigin
@Controller
@RequestMapping(value="app/analysis")
public class AppAnalysisController extends AbstractHosznController{

    @GetMapping(value = "/index" )
    public String index( @RequestParam Map paramMap ,ModelMap modelMap) {
		modelMap.addAttribute("parentId", paramMap.containsKey("parentId") ? paramMap.get("parentId") : "1") ;
		String dayMonthAgo = DateUtils.formatDate(DateUtils.addDays(new Date(), -31), DateUtils.YYYYMMDDHHMMSS) ;
		String today = DateUtils.formatDate(DateUtils.addDays(new Date(), -1), DateUtils.YYYYMMDDHHMMSS) ;
		modelMap.addAttribute("dayMonthAgo" , dayMonthAgo);
		modelMap.addAttribute("today" , today);
        return "app/analysis/index";
    }
	
	
}
