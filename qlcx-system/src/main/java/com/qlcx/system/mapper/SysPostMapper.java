package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysPost;

/**
 * Post information data layer
 */
public interface SysPostMapper {
  /**
   * Query job data collection
   *
   * @param post post information
   * @return Post data collection
   */
  public List<SysPost> selectPostList(SysPost post);

  /**
   * Check all positions
   *
   * @return job list
   */
  public List<SysPost> selectPostAll();

  /**
   * Query posts based on user ID
   *
   * @param userId user ID
   * @return job list
   */
  public List<SysPost> selectPostsByUserId(Long userId);

  /**
   * Query post information by post ID
   *
   * @param postId post ID
   * @return character object information
   */
  public SysPost selectPostById(Long postId);

  /**
   * Delete post information in batch
   *
   * @param ids the data ID to be deleted
   * @return result
   */
  public int deletePostByIds(Long[] ids);

  /**
   * Modify post information
   *
   * @param post post information
   * @return result
   */
  public int updatePost(SysPost post);

  /**
   * New post information
   *
   * @param post post information
   * @return result
   */
  public int insertPost(SysPost post);

  /**
   * Check post name
   *
   * @param postName post name
   * @return result
   */
  public SysPost checkPostNameUnique(String postName);

  /**
   * Check post code
   *
   * @param postCode Post code
   * @return result
   */
  public SysPost checkPostCodeUnique(String postCode);
}