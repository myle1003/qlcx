package com.qlcx.generator.mapper;

import java.util.List;

import com.qlcx.generator.domain.GenTableColumn;

/**
 * 业务字段 数据层
 * 
 * @author qlcx
 */
public interface GenTableColumnMapper
{
    /**
     * 根据Bảng tên查询列信息
     * 
     * @param tableName Bảng tên
     * @return 列信息
     */
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName);
    
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
     * 批量Xóa业务字段
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteGenTableColumnByIds(Long[] ids);
}