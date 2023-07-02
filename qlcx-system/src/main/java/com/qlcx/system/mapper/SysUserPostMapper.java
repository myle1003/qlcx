package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysUserPost;

/**
 * User and job association table data layer
 */
public interface SysUserPostMapper {
  /**
   * Delete user and post association by user ID
   *
   * @param userId user ID
   * @return result
   */
  public int deleteUserPostByUserId(Long userId);

  /**
   * Query the number of post usage by post ID
   *
   * @param postId post ID
   * @return result
   */
  public int countUserPostById(Long postId);

  /**
   * Delete users and posts in batches
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deleteUserPost(Long[] ids);

  /**
   * Add user post information in batches
   *
   * @param userPostList User role list
   * @return result
   */
  public int batchUserPost(List<SysUserPost> userPostList);
}