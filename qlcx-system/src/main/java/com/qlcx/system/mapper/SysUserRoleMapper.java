package com.qlcx.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.qlcx.system.domain.SysUserRole;

/**
 * User and role association table data layer
 */
public interface SysUserRoleMapper {
  /**
   * Query user and role association by user ID
   *
   * @param userId user ID
   * @return User and role association list
   */
  public List<SysUserRole> selectUserRoleByUserId(Long userId);

  /**
   * Delete user and role association by user ID
   *
   * @param userId user ID
   * @return result
   */
  public int deleteUserRoleByUserId(Long userId);

  /**
   * Batch delete users and role associations
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deleteUserRole(Long[] ids);

  /**
   * Query the number of roles used by role ID
   *
   * @param roleId role ID
   * @return result
   */
  public int countUserRoleByRoleId(Long roleId);

  /**
   * Add user role information in batches
   *
   * @param userRoleList User role list
   * @return result
   */
  public int batchUserRole(List<SysUserRole> userRoleList);

  /**
   * Delete user and role association information
   *
   * @param userRole user and role association information
   * @return result
   */
  public int deleteUserRoleInfo(SysUserRole userRole);

  /**
   * Batch deauthorize user roles
   *
   * @param roleId  role ID
   * @param userIds User data ID to be deleted
   * @return result
   */
  public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}