package com.qlcx.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.qlcx.system.domain.SysMenu;

/**
 * Menu table data layer
 */
public interface SysMenuMapper {
  /**
   * Query all menus of the system (including buttons)
   *
   * @return menu list
   */
  public List<SysMenu> selectMenuAll();

  /**
   * Query menu based on user ID
   *
   * @param userId user ID
   * @return menu list
   */
  public List<SysMenu> selectMenuAllByUserId(Long userId);

  /**
   * Inquiry system displays menu normally (without buttons)
   *
   * @return menu list
   */
  public List<SysMenu> selectMenuNormalAll();

  /**
   * Query menu based on user ID
   *
   * @param userId user ID
   * @return menu list
   */
  public List<SysMenu> selectMenusByUserId(Long userId);

  /**
   * Query permissions based on user ID
   *
   * @param userId user ID
   * @return permission list
   */
  public List<String> selectPermsByUserId(Long userId);

  /**
   * Query menu based on role ID
   *
   * @param roleId role ID
   * @return menu list
   */
  public List<String> selectMenuTree(Long roleId);

  /**
   * Query system menu list
   *
   * @param menu menu information
   * @return menu list
   */
  public List<SysMenu> selectMenuList(SysMenu menu);

  /**
   * Query system menu list
   *
   * @param menu menu information
   * @return menu list
   */
  public List<SysMenu> selectMenuListByUserId(SysMenu menu);

  /**
   * Delete menu management information
   *
   * @param menuId menu ID
   * @return result
   */
  public int deleteMenuById(Long menuId);

  /**
   * Query information based on menu ID
   *
   * @param menuId menu ID
   * @return menu information
   */
  public SysMenu selectMenuById(Long menuId);

  /**
   * Check the number of menus
   *
   * @param parentId menu parent ID
   * @return result
   */
  public int selectCountMenuByParentId(Long parentId);

  /**
   * Added menu information
   *
   * @param menu menu information
   * @return result
   */
  public int insertMenu(SysMenu menu);

  /**
   * Modify menu information
   *
   * @param menu menu information
   * @return result
   */
  public int updateMenu(SysMenu menu);

  /**
   * Verify that the menu name is unique
   *
   * @param menuName menu name
   * @param parentId parent menu ID
   * @return result
   */
  public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}