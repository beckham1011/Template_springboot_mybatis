package cn.bjjoy.bms.setting.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.bjjoy.bms.setting.poi.Equiptype;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.util.UserChangeVo;

@RestController
public class ExportController extends AbstractHosznController{

	private static final Logger logger = LogManager.getLogger();
		
	@GetMapping("/exportEquiptype")
    public void exportUserInfo(HttpServletResponse response,UserChangeVo userChangeVo) throws Exception {
		Map<String , Object> map = new HashMap<>();
		List<Map<String, Object>> types = equiptypeService.exportTypeList(map);
		
		List<Equiptype> typeList = DataUtils.getDataArray(types, Equiptype.class);
		ExportParams exportParams = new ExportParams();
		exportParams.setSheetName("泵站列表");//工作表名称
		exportParams.setTitle("泵站列表");//导出表名
		Workbook workbook=ExcelExportUtil.exportExcel(exportParams, Equiptype.class, typeList);//导出结果集
		response.setContentType("applicationnd.ms-excel"); // 改成输出excel文件
		String fileName = java.net.URLEncoder.encode("高岗区泵站统计表", "UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="+ fileName + ".xls");// 03版本后缀xls，之后的xlsx
		OutputStream out = response.getOutputStream();
        workbook.write(out);//输出结果集
        
        logger.info("export user info success");
	}
	
	
}
