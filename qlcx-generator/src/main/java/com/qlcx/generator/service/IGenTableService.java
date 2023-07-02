package com.qlcx.generator.service;

import java.util.List;
import java.util.Map;

import com.qlcx.generator.domain.GenTable;

/**
 * 业务 服务层
 * 
 * @author qlcx
 */
public interface IGenTableService
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
     * 查询业务信息
     * 
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(Long id);

    /**
     *sửa đổi业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    public void updateGenTable(GenTable genTable);

    /**
     * Xóa业务信息
     * 
     * @param ids 需要Xóa的数据ID
     * @return 结果
     */
    public void deleteGenTableByIds(String ids);

    /**
     * 导入表结构
     * 
     * @param tableList 导入表Danh sách
     * @param operName Action人员
     */
    public void importGenTable(List<GenTable> tableList, String operName);

    /**
     * 预览代码
     * 
     * @param tableId 表编号
     * @return 预览数据Danh sách
     */
    public Map<String, String> previewCode(Long tableId);

    /**
     * 生成代码
     * 
     * @param tableName Bảng tên
     * @return 数据
     */
    public byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     * 
     * @param tableNames 表数组
     * @return 数据
     */
    public byte[] generatorCode(String[] tableNames);

    /**
     *sửa đổi参数校验
     * 
     * @param genTable 业务信息
     */
    public void validateEdit(GenTable genTable);
}
