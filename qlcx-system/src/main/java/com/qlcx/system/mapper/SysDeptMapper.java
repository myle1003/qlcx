package com.qlcx.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.qlcx.system.domain.SysDept;

/**
 * Department management data layer
 */
public interface SysDeptMapper {
  /**
   * Check the number of departments
   *
   * @param dept Department information
   * @return result
   */
  public int selectDeptCount(SysDept dept);

  /**
   * Check if there is a user in the department
   *
   * @param deptId Department ID
   * @return result
   */
  public int checkDeptExistUser(Long deptId);

  /**
   * Query department management data
   *
   * @param dept Department information
   * @return Department Information Collection
   */
  public List<SysDept> selectDeptList(SysDept dept);

  /**
   * Delete department management information
   *
   * @param deptId Department ID
   * @return result
   */
  public int deleteDeptById(Long deptId);

  /**
   * Add department information
   *
   * @param dept Department information
   * @return result
   */
  public int insertDept(SysDept dept);

  /**
   * Modify department information
   *
   * @param dept Department information
   * @return result
   */
  public int updateDept(SysDept dept);

  /**
   * Modify child element relationship
   *
   * @param depts child element
   * @return result
   */
  public int updateDeptChildren(@Param("depts") List<SysDept> depts);

  /**
   * Query information based on department ID
   *
   * @param deptId Department ID
   * @return department information
   */
  public SysDept selectDeptById(Long deptId);

  /**
   * Check whether the department name is unique
   *
   * @param deptName Department name
   * @param parentId parent department ID
   * @return result
   */
  public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

  /**
   * Query department by role ID
   *
   * @param roleId role ID
   * @return Department List
   */
  public List<String> selectRoleDeptTree(Long roleId);

  /**
   * Modify the status of the parent department of the department
   *
   * @param dept department
   */
  public void updateDeptStatus(SysDept dept);

  /**
   * Query all sub-departments based on ID
   *
   * @param deptId Department ID
   * @return Department List
   */
  public List<SysDept> selectChildrenDeptById(Long deptId);

  /**
   * Query all sub-departments according to ID (normal state)
   *
   * @param deptId Department ID
   * @return number of sub departments
   */
  public int selectNormalChildrenDeptById(Long deptId);
}