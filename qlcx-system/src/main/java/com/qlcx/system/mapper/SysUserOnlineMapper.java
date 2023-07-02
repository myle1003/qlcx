package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysUserOnline;

/**
 * Online user data layer
 */
public interface SysUserOnlineMapper {
  /**
   * Query information by session number
   *
   * @param sessionId session ID
   * @return online user information
   */
  public SysUserOnline selectOnlineById(String sessionId);

  /**
   * Delete information by session number
   *
   * @param sessionId session ID
   * @return online user information
   */
  public int deleteOnlineById(String sessionId);

  /**
   * Save session information
   *
   * @param online session information
   * @return result
   */
  public int saveOnline(SysUserOnline online);

  /**
   * Query session collection
   *
   * @param userOnline session parameters
   * @return session collection
   */
  public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline);

  /**
   * Query expired session collection
   *
   * @param lastAccessTime expiration time
   * @return session collection
   */
  public List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);
}