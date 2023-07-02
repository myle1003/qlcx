package com.qlcx.system.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.core.domain.BaseEntity;

/**
 * Department table sys_dept
 */
public class SysDept extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /** Department ID */
  private Long deptId;

  /** Parent department ID */
  private Long parentId;

  /** List of ancestors */
  private String ancestors;

  /** Department name */
  private String deptName;

  /** display order */
  private String orderNum;

  /** principal */
  private String leader;

  /** contact number */
  private String phone;

  /** Email */
  private String email;

  /** Department status: 0 normal, 1 disabled */
  private String status;

  /** Delete flag (0 means existing, 2 means delete) */
  private String delFlag;

  /** Name of parent department */
  private String parentName;

  public Long getDeptId() {
    return deptId;
  }

  public void setDeptId(Long deptId) {
    this.deptId = deptId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getAncestors() {
    return ancestors;
  }

  public void setAncestors(String ancestors) {
    this.ancestors = ancestors;
  }

  @NotBlank(message = "Department name cannot be empty")
  @Size(min = 0, max = 30, message = "Department name cannot exceed 30 characters")
  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  @NotBlank(message = "The display order cannot be empty")
  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public String getLeader() {
    return leader;
  }

  public void setLeader(String leader) {
    this.leader = leader;
  }

  @Size(min = 0, max = 11, message = "Contact phone length cannot exceed 11 characters")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Email(message = "Mailbox format is incorrect")
  @Size(min = 0, max = 50, message = "mailbox length cannot exceed 50 characters")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("deptId", getDeptId())
        .append("parentId", getParentId()).append("ancestors", getAncestors()).append("deptName", getDeptName())
        .append("orderNum", getOrderNum()).append("leader", getLeader()).append("phone", getPhone())
        .append("email", getEmail()).append("status", getStatus()).append("delFlag", getDelFlag())
        .append("createBy", getCreateBy()).append("createTime", getCreateTime()).append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime()).toString();
  }
}
