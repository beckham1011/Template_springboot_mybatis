package cn.bjjoy.bms.setting.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bjjoy.bms.setting.service.AlarmService;
import cn.bjjoy.bms.setting.service.AuthorityService;
import cn.bjjoy.bms.setting.service.DatacardService;
import cn.bjjoy.bms.setting.service.DeclarationrecordService;
import cn.bjjoy.bms.setting.service.EquipdataService;
import cn.bjjoy.bms.setting.service.EquiptypeService;
import cn.bjjoy.bms.setting.service.MaintainrecordService;
import cn.bjjoy.bms.setting.service.MaintenanceService;
import cn.bjjoy.bms.setting.service.MenuService;
import cn.bjjoy.bms.setting.service.RoleService;
import cn.bjjoy.bms.setting.service.SystemService;
import cn.bjjoy.bms.setting.service.TenantService;
import cn.bjjoy.bms.setting.service.UserRoleService;
import cn.bjjoy.bms.setting.service.UserService;
import cn.bjjoy.bms.task.SaveHistoryDataEveryDay;

public abstract class AbstractHosznController extends BaseController {

	@Autowired
	public EquiptypeService equiptypeService;
	
	@Autowired
	public TenantService tenantService;
	
	@Autowired
	public SystemService systemService ;
	
	@Autowired
	public EquipdataService equipdataService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public AlarmService alarmService;

	@Autowired
	public AuthorityService authorityService;

	@Autowired
	public DatacardService datacardService;
	
	@Autowired
	public DeclarationrecordService declarationrecordService;
	
	@Autowired
	public MaintainrecordService maintainrecordService;

	@Autowired
	public MaintenanceService maintenanceService;

    @Autowired
    public MenuService menuService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public UserRoleService userRoleService;

    @Autowired
    SaveHistoryDataEveryDay saveService;
    
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ;
	
	
}
