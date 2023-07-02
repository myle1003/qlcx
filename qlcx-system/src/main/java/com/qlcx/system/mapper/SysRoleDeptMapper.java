package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysRoleDept;

/**
 * Role and department association table data layer
 */
public interface SysRoleDeptMapper {
  /**
   * Delete role and department association by role ID
   *
   * @param roleId role ID
   * @return result
   */
  public int deleteRoleDeptByRoleId(Long roleId);

  /**
   * Batch delete role department related information
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deleteRoleDept(Long[] ids);

  /**
   * Query the quantity used by the department
   *
   * @param deptId Department ID
   * @return result
   */
  public int selectCountRoleDeptByDeptId(Long deptId);

  /**
   * Add role department information in batches
   *
   * @param roleDeptList role department list
   * @return result
   */
  public int batchRoleDept(List<SysRoleDept> roleDeptList);
}