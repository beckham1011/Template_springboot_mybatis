package cn.bjjoy.bms.setting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.bjjoy.bms.setting.entity.Menu;

@Mapper
@Repository
public interface MenuMapper {

    int deleteById(Integer id);

    int insertMenu(Menu record);

    Menu getById(Integer id);

    int updateById(Menu record);

    /**
     * 更新父菜单下所有子菜单信息
     * @param parentMenu
     * @return
     */
    int updateByParentId(Menu parentMenu);

    /**
     * 查询用户可见的菜单项
     *
     * @param userUuid 用户ID
     * @return
     */
//    List<Menu> queryMenuByUser(@Param("userUuid") String userUuid);

    /**
     * 获取菜单列表
     * @param menu
     * @return
     */
    List<Menu> getList(Menu menu);

    /**
     * 获取目录和菜单（type=0,1）
     */
    List<Menu> getMenuList();

    /**
     * 根据角id获取menu
     * @param roleId
     */
    List<Menu> getListByRoleId(Integer roleId);

    /**
     * 获取菜单和按钮（type=1,2）
     */
	List<Integer> getMenuList12(@Param("menuIdList") List<String> menuIdList);

}