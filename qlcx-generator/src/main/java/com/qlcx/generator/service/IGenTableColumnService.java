package com.qlcx.generator.service;

import java.util.List;

import com.qlcx.generator.domain.GenTableColumn;

/**
 * 业务字段 服务层
 * 
 * @author qlcx
 */
public interface IGenTableColumnService
{
    /**
     * 查询业务字段Danh sách
     * 
     * @param genTableColumn 业务字段信息
     * @return 业务字段集合
     */
    public List<GenTableColumn> selectGenTableColumnListByTableId(GenTableColumn genTableColumn);

    /**
     * Thêm vào业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    public int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     *sửa đổi业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    public int updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * Xóa业务字段信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteGenTableColumnByIds(String ids);
}
