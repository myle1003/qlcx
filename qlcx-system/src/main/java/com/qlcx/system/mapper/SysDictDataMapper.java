package com.qlcx.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.qlcx.system.domain.SysDictData;

/**
 * Dictionary table data layer
 */
public interface SysDictDataMapper {
  /**
   * Query dictionary data based on conditional paging
   *
   * @param dictData dictionary data information
   * @return dictionary data collection information
   */
  public List<SysDictData> selectDictDataList(SysDictData dictData);

  /**
   * Query dictionary data according to dictionary type
   *
   * @param dictType dictionary type
   * @return dictionary data collection information
   */
  public List<SysDictData> selectDictDataByType(String dictType);

  /**
   * Query dictionary data information according to dictionary type and dictionary
   * key value
   *
   * @param dictType  dictionary type
   * @param dictValue dictionary key value
   * @return dictionary tag
   */
  public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

  /**
   * Query information based on dictionary data ID
   *
   * @param dictCode dictionary data ID
   * @return dictionary data
   */
  public SysDictData selectDictDataById(Long dictCode);

  /**
   * Query dictionary data
   *
   * @param dictType dictionary type
   * @return dictionary data
   */
  public int countDictDataByType(String dictType);

  /**
   * Delete dictionary data information by dictionary ID
   *
   * @param dictCode dictionary data ID
   * @return result
   */
  public int deleteDictDataById(Long dictCode);

  /**
   * Delete dictionary data in batch
   *
   * @param ids data to be deleted
   * @return result
   */
  public int deleteDictDataByIds(String[] ids);

  /**
   * Added dictionary data information
   *
   * @param dictData dictionary data information
   * @return result
   */
  public int insertDictData(SysDictData dictData);

  /**
   * Modify dictionary data information
   *
   * @param dictData dictionary data information
   * @return result
   */
  public int updateDictData(SysDictData dictData);

  /**
   * Simultaneously modify the dictionary type
   *
   * @param oldDictType old dictionary type
   * @param newDictType new and old dictionary types
   * @return result
   */
  public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}