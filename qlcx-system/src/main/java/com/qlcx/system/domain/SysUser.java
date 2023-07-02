package com.qlcx.system.domain;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.qlcx.common.annotation.Excel;
import com.qlcx.common.annotation.Excels;
import com.qlcx.common.annotation.Excel.ColumnType;
import com.qlcx.common.annotation.Excel.Type;
import com.qlcx.common.core.domain.BaseEntity;

/**
 * User object sys_user
 */
public class SysUser extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /** User ID */
  @Excel(name = "user serial number", cellType = ColumnType.NUMERIC, prompt = "user number")
  private Long userId;

  /** Department ID */
  @Excel(name = "Department Number", type = Type.IMPORT)
  private Long deptId;

  /** Department parent ID */
  private Long parentId;

  /** Character ID */
  private Long roleId;

  /** Login name */
  @Excel(name = "Login Name")
  private String loginName;

  /** user name */
  @Excel(name = "user name")
  private String userName;

  /** user type */
  private String userType;

  /** User email */
  @Excel(name = "User Mailbox")
  private String email;

  /** cellphone number */
  @Excel(name = "Mobile Number")
  private String phonenumber;

  /** User gender */
  @Excel(name = "User Gender", readConverterExp = "0=Male, 1=Female, 2=Unknown")
  private String sex;

  /** profile picture */
  private String avatar;

  /** Password */
  private String password;

  /** Salt encryption */
  private String salt;

  /** Account status (0 normal 1 disabled) */
  @Excel(name = "Account Status", readConverterExp = "0=normal, 1=disabled")
  private String status;

  /** Delete flag (0 means existing, 2 means delete) */
  private String delFlag;

  /** Last login IP */
  @Excel(name = "Last login IP", type = Type.EXPORT)
  private String loginIp;

  /** Last login time */
  @Excel(name = "Last login time", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
  private Date loginDate;

  /** Department objects */
  @Excels({ @Excel(name = "Department name", targetAttr = "deptName", type = Type.EXPORT),
      @Excel(name = "Head of Department", targetAttr = "leader", type = Type.EXPORT) })
  private SysDept dept;

  private List<SysRole> roles;

  /** role group */
  private Long[] roleIds;

  /** Job group */
  private Long[] postIds;

  public SysUser() {

  }

  public SysUser(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public boolean isAdmin() {
    return isAdmin(this.userId);
  }

  public static boolean isAdmin(Long userId) {
    return userId != null && 1L == userId;
  }

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

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  @NotBlank(message = "Login account cannot be empty")
  @Size(min = 0, max = 30, message = "login account length cannot exceed 30 characters")
  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  @Size(min = 0, max = 30, message = "User nickname cannot exceed 30 characters")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Email(message = "Mailbox format is incorrect")
  @Size(min = 0, max = 50, message = "mailbox length cannot exceed 50 characters")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Size(min = 0, max = 11, message = "Mobile phone number cannot exceed 11 characters")
  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
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

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public SysDept getDept() {
    if (dept == null) {
      dept = new SysDept();
    }
    return dept;
  }

  public void setDept(SysDept dept) {
    this.dept = dept;
  }

  public List<SysRole> getRoles() {
    return roles;
  }

  public void setRoles(List<SysRole> roles) {
    this.roles = roles;
  }

  public Long[] getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(Long[] roleIds) {
    this.roleIds = roleIds;
  }

  public Long[] getPostIds() {
    return postIds;
  }

  public void setPostIds(Long[] postIds) {
    this.postIds = postIds;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
        .append("deptId", getDeptId()).append("loginName", getLoginName()).append("userName", getUserName())
        .append("userType", getUserType()).append("email", getEmail()).append("phonenumber", getPhonenumber())
        .append("sex", getSex()).append("avatar", getAvatar()).append("password", getPassword())
        .append("salt", getSalt()).append("status", getStatus()).append("delFlag", getDelFlag())
        .append("loginIp", getLoginIp()).append("loginDate", getLoginDate()).append("createBy", getCreateBy())
        .append("createTime", getCreateTime()).append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime())
        .append("remark", getRemark()).append("dept", getDept()).append("roles", getRoles()).toString();
  }
}
