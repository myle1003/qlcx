package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysUser;

/**
 * User table data layer
 */
public interface SysUserMapper {
  /**
   * Query user list based on conditional pagination
   *
   * @param sysUser user information
   * @return user information collection information
   */
  public List<SysUser> selectUserList(SysUser sysUser);

  /**
   * Query the list of unassigned user roles according to the conditions
   *
   * @param user user information
   * @return user information collection information
   */
  public List<SysUser> selectAllocatedList(SysUser user);

  /**
   * Query the list of unassigned user roles based on conditional paging
   *
   * @param user user information
   * @return user information collection information
   */
  public List<SysUser> selectUnallocatedList(SysUser user);

  /**
   * Query users by user name
   *
   * @param userName username
   * @return user object information
   */
  public SysUser selectUserByLoginName(String userName);

  /**
   * Query users by mobile phone number
   *
   * @param phoneNumber phone number
   * @return user object information
   */
  public SysUser selectUserByPhoneNumber(String phoneNumber);

  /**
   * Inquire users by email
   *
   * @param email
   * @return user object information
   */
  public SysUser selectUserByEmail(String email);

  /**
   * Query users by user ID
   *
   * @param userId user ID
   * @return user object information
   */
  public SysUser selectUserById(Long userId);

  /**
   * Delete user by user ID
   *
   * @param userId user ID
   * @return result
   */
  public int deleteUserById(Long userId);

  /**
   * Delete user information in batch
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deleteUserByIds(Long[] ids);

  /**
   * Modify user information
   *
   * @param user user information
   * @return result
   */
  public int updateUser(SysUser user);

  /**
   * New user information
   *
   * @param user user information
   * @return result
   */
  public int insertUser(SysUser user);

  /**
   * Verify that the user name is unique
   *
   * @param loginName login name
   * @return result
   */
  public int checkLoginNameUnique(String loginName);

  /**
   * Check if the mobile phone number is unique
   *
   * @param phonenumber phone number
   * @return result
   */
  public SysUser checkPhoneUnique(String phonenumber);

  /**
   * Check if email is unique
   *
   * @param email user email
   * @return result
   */
  public SysUser checkEmailUnique(String email);
}