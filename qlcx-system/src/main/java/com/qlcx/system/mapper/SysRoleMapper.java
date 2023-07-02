package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysRole;

/**
 * Role table data layer
 */
public interface SysRoleMapper {
  /**
   * Query role data based on conditional paging
   *
   * @param role role information
   * @return Character data collection information
   */
  public List<SysRole> selectRoleList(SysRole role);

  /**
   * Query role based on user ID
   *
   * @param userId user ID
   * @return character list
   */
  public List<SysRole> selectRolesByUserId(Long userId);

  /**
   * Query role by role ID
   *
   * @param roleId role ID
   * @return character object information
   */
  public SysRole selectRoleById(Long roleId);

  /**
   * Delete role by role ID
   *
   * @param roleId role ID
   * @return result
   */
  public int deleteRoleById(Long roleId);

  /**
   * Batch role user information
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deleteRoleByIds(Long[] ids);

  /**
   * Modify character information
   *
   * @param role role information
   * @return result
   */
  public int updateRole(SysRole role);

  /**
   * Added role information
   *
   * @param role role information
   * @return result
   */
  public int insertRole(SysRole role);

  /**
   * Verify that the role name is unique
   *
   * @param roleName role name
   * @return character information
   */
  public SysRole checkRoleNameUnique(String roleName);

  /**
   * Verify that the role permissions are unique
   *
   * @param roleKey role permissions
   * @return character information
   */
  public SysRole checkRoleKeyUnique(String roleKey);
}