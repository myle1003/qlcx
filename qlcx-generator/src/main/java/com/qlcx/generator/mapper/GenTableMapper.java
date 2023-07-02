package com.qlcx.generator.mapper;

import java.util.List;

import com.qlcx.generator.domain.GenTable;

/**
 * 业务 数据层
 * 
 * @author qlcx
 */
public interface GenTableMapper
{
    /**
     * 查询业务Danh sách
     * 
     * @param genTable 业务信息
     * @return 业务集合
     */
    public List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库Danh sách
     * 
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库Danh sách
     * 
     * @param tableNames Bảng tên组
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询表ID业务信息
     * 
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(Long id);

    /**
     * 查询Bảng tên业务信息
     * 
     * @param tableName Bảng tên
     * @return 业务信息
     */
    public GenTable selectGenTableByName(String tableName);

    /**
     * Thêm vào业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    public int insertGenTable(GenTable genTable);

    /**
     *sửa đổi业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    public int updateGenTable(GenTable genTable);

    /**
     * 批量Xóa业务
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public int deleteGenTableByIds(Long[] ids);
}