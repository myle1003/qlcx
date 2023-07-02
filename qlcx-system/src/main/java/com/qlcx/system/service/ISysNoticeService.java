package com.qlcx.system.service;

import java.util.List;

import com.qlcx.system.domain.SysNotice;

/**
 * 公告 服务层
 * 
 * @author qlcx
 */
public interface ISysNoticeService
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告Danh sách
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * Thêm vào公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     *sửa đổi公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * Xóa公告信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String ids);
}
