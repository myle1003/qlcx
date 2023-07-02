package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.core.domain.BaseEntity;

/**
 * Notification announcement form sys_notice
 */
public class SysNotice extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /** Announcement ID */
  private Long noticeId;

  /** Announcement title */
  private String noticeTitle;

  /** Announcement type (1 notice 2 announcement) */
  private String noticeType;

  /** announcement content */
  private String noticeContent;

  /** Announcement status (0 normal 1 closed) */
  private String status;

  public Long getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(Long noticeId) {
    this.noticeId = noticeId;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }

  @NotBlank(message = "Announcement title cannot be empty")
  @Size(min = 0, max = 50, message = "Announcement title cannot exceed 50 characters")
  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeType(String noticeType) {
    this.noticeType = noticeType;
  }

  public String getNoticeType() {
    return noticeType;
  }

  public void setNoticeContent(String noticeContent) {
    this.noticeContent = noticeContent;
  }

  public String getNoticeContent() {
    return noticeContent;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("noticeId", getNoticeId())
        .append("noticeTitle", getNoticeTitle()).append("noticeType", getNoticeType())
        .append("noticeContent", getNoticeContent()).append("status", getStatus()).append("createBy", getCreateBy())
        .append("createTime", getCreateTime()).append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime())
        .append("remark", getRemark()).toString();
  }
}
