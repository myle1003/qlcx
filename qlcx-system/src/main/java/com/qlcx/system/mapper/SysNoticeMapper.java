package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysNotice;

/**
 * Announcement data layer
 */
public interface SysNoticeMapper {
  /**
   * Query announcement information
   *
   * @param noticeId Announcement ID
   * @return announcement information
   */
  public SysNotice selectNoticeById(Long noticeId);

  /**
   * Check the announcement list
   *
   * @param notice
   * @return announcement collection
   */
  public List<SysNotice> selectNoticeList(SysNotice notice);

  /**
   * New announcement
   *
   * @param notice
   * @return result
   */
  public int insertNotice(SysNotice notice);

  /**
   * Amendment announcement
   *
   * @param notice
   * @return result
   */
  public int updateNotice(SysNotice notice);

  /**
   * Bulk delete announcement
   *
   * @param noticeIds the data ID to be deleted
   * @return result
   */
  public int deleteNoticeByIds(String[] noticeIds);
}