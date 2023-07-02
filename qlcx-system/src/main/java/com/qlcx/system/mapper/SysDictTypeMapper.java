package com.qlcx.system.mapper;

import java.util.List;

import com.qlcx.system.domain.SysDictType;

/**
 * Dictionary table data layer
 */
public interface SysDictTypeMapper {
  /**
   * Query dictionary type based on conditional paging
   *
   * @param dictType dictionary type information
   * @return dictionary type collection information
   */
  public List<SysDictType> selectDictTypeList(SysDictType dictType);

  /**
   * According to all dictionary types
   *
   * @return dictionary type collection information
   */
  public List<SysDictType> selectDictTypeAll();

  /**
   * Query information based on dictionary type ID
   *
   * @param dictId Dictionary type ID
   * @return dictionary type
   */
  public SysDictType selectDictTypeById(Long dictId);

  /**
   * Query information according to dictionary type
   *
   * @param dictType dictionary type
   * @return dictionary type
   */
  public SysDictType selectDictTypeByType(String dictType);

  /**
   * Delete dictionary information by dictionary ID
   *
   * @param dictId dictionary ID
   * @return result
   */
  public int deleteDictTypeById(Long dictId);

  /**
   * Delete dictionary types in batch
   *
   * @param ids data to be deleted
   * @return result
   */
  public int deleteDictTypeByIds(Long[] ids);

  /**
   * Added dictionary type information
   *
   * @param dictType dictionary type information
   * @return result
   */
  public int insertDictType(SysDictType dictType);

  /**
   * Modify dictionary type information
   *
   * @param dictType dictionary type information
   * @return result
   */
  public int updateDictType(SysDictType dictType);

  /**
   * Check whether the dictionary type is unique
   *
   * @param dictType dictionary type
   * @return result
   */
  public SysDictType checkDictTypeUnique(String dictType);
}