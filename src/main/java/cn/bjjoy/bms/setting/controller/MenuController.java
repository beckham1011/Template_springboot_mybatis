package cn.bjjoy.bms.setting.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bjjoy.bms.base.Codes;
import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.entity.Menu;
import cn.bjjoy.bms.setting.vo.ZtreeView;
import cn.bjjoy.bms.util.DataUtils;

import com.github.pagehelper.PageInfo;

/**
 *
 * @author bjjoy
 * @date 2018/11/05
 */
@SuppressWarnings({"unchecked"})
@Controller
@CrossOrigin
@RequestMapping("menu")
public class MenuController extends AbstractHosznController{

	/**
	 * 到新建菜单页
	 */
	@GetMapping(value = "add" )
	public String toAdd(ModelMap map) {
		map.addAttribute("list", this.getMenuList());
		return "/menu/add";
	}

    /**
     * 新建菜单
     * @param menu
     */
    @ResponseBody
    @PostMapping(value = "create")
    public ResponseResult createMenu(Menu menu){
    	if (menu.getParentId() != null && menu.getParentId() == 0){
    		menu.setParentId(null);
		}
    	menu.setCreateDate(new Date());
    	menu.setUpdateDate(new Date());
        menuService.insert(menu);
        return ResponseResult.ok(menu.getId());
    }

	/**
	 * 到资源列表页
	 */
	@GetMapping(value = "index" )
	public String toIndex() {
		return "/menu/index";
	}

    /**
     * 菜单列表
     */
    @ResponseBody
    @GetMapping("getList")
    public ResponseResult getList(Menu menu,
								  @RequestParam(defaultValue = "1") Integer pageNumber,
								  @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Menu> menuPage = menuService.getPage(menu, pageNumber, pageSize);
        return ResponseResult.ok(menuPage);
    }

    /**
     * 菜单tree列表
     */
    @ResponseBody
    @GetMapping("tree/{roleId}")
    public List<ZtreeView> getList(@PathVariable Integer roleId){
        List<ZtreeView> resultTreeNodes = new ArrayList<>();
        resultTreeNodes.add(new ZtreeView(0, null, "系统菜单", true));
        List<Menu> menuList = menuService.getListByRoleId(roleId);
        //用户已分配资源id
		Set<Integer> roleMenuIdSet = new HashSet<>();
		for (Menu roleMenu : menuList){
			roleMenuIdSet.add(roleMenu.getId());
		}
		//所有资源
		List<Menu> allMenu = menuService.getList(new Menu());
		for (Menu menu : allMenu){
			ZtreeView node = new ZtreeView();
			node.setId(menu.getId());
			if (menu.getParentId() == null) {
				node.setpId(0);
			} else {
				node.setpId(menu.getParentId());
			}
			node.setName(menu.getName());
			if (roleMenuIdSet != null && roleMenuIdSet.contains(menu.getId())) {
				node.setChecked(true);
			}
			resultTreeNodes.add(node);
		}
        return resultTreeNodes;
    }

    /**
     * 菜单详情
     */
    @GetMapping(value = "getMenu")
    public ResponseResult getMenu(@RequestParam Integer id, String traceID){
        Menu menu = menuService.getMenu(id);
        Map<String , Object> resultMap = DataUtils.getData(menu, Map.class);
        if(null != menu.getParentId()) {
            Menu parentMenu = menuService.getMenu(menu.getParentId());
            resultMap.put("parentName", parentMenu.getName());
        }
        return ResponseResult.ok(resultMap);
    }

	/**
	 * 到新建菜单页
	 */
    @GetMapping("edit/{menuId}" )
	public String toEdit(@PathVariable Integer menuId, ModelMap map) {
		Menu menu = this.menuService.getMenu(menuId);
		map.addAttribute("list", this.getMenuList());
		map.addAttribute("menu", menu);
		return "/menu/edit";
	}

    /**
     * 菜单详情
     */
    @ResponseBody
    @PostMapping("update")
    public ResponseResult update(Menu menu){
    	if (menu.getParentId() != null && menu.getParentId() == 0){
    		menu.setParentId(null);
		}
        int count = menuService.update(menu);
        return ResponseResult.ok(count);
    }

    /**
     * 菜单删除
     */
    @ResponseBody
    @DeleteMapping("delete/{menuId}")
    public ResponseResult delete(@PathVariable Integer menuId){
    	int count = this.menuService.getRoleMenuCountByMenuId(menuId);
    	if (count > 0){
			return ResponseResult.error(Codes.ROLE_IS_USED);
		}
    	Menu menu = new Menu();
        menu.setDelFlag("1");
        menuService.delete(menu);
        return ResponseResult.ok(count);
    }

	/**
	 * 构造上级资源列表getMenuList
	 */
	private List<Menu> getMenuList(){
		List<Menu> list = menuService.getMenuList();
		List<Menu> resultList = new ArrayList<>();
		Menu head = new Menu();
		head.setName("系统菜单");
		head.setId(0);
		head.setType(3);
		resultList.add(head);
		resultList.addAll(list);
		return resultList;
	}
}