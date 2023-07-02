package com.qlcx.framework.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.system.domain.SysDictData;
import com.qlcx.system.service.ISysDictDataService;
import com.qlcx.system.service.ISysDictTypeService;

/**
 * qlcx's first html call thymeleaf to achieve dictionary reading
*/
@Service("dict")
public class DictService
{
    @Autowired
    private ISysDictTypeService dictTypeService;

    @Autowired
    private ISysDictDataService dictDataService;

    /**
    * Query dictionary data information according to dictionary type
    *
    * @param dictType dictionary type
    * @return parameter key value
    */
    public List<SysDictData> getType(String dictType)
    {
        return dictTypeService.selectDictDataByType(dictType);
    }

    /**
    * Query dictionary data information according to dictionary type and dictionary key value
    *
    * @param dictType dictionary type
    * @param dictValue dictionary key value
    * @return dictionary tag
    */
    public String getLabel(String dictType, String dictValue)
    {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
