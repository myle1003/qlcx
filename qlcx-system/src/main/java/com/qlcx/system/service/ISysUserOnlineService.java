package com.qlcx.system.service;

import java.util.Date;
import java.util.List;

import com.qlcx.system.domain.SysUserOnline;

/**
 * 在线用户 服务层
 * 
 * @author qlcx
 */
public interface ISysUserOnlineService
{
    /**
     * 通过会话id查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SysUserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话idXóa信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId);

    /**
     * 通过会话idXóa信息
     * 
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions);

    /**
     * 会话信息
     * 
     * @param online 会话信息
     */
    public void saveOnline(SysUserOnline online);

    /**
     * 查询会话集合
     * 
     * @param userOnline 分页参数
     * @return 会话集合
     */
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline);

    /**
     * 强退用户
     * 
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId);

    /**
     * 查询会话集合
     * 
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate);
}
