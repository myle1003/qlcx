package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.WorkEmployeeMapper;
import com.qlcx.system.domain.WorkEmployee;
import com.qlcx.system.service.IWorkEmployeeService;
import com.qlcx.common.core.text.Convert;


@Service
public class WorkEmployeeServiceImpl implements IWorkEmployeeService 
{
    @Autowired
    private WorkEmployeeMapper workEmployeeMapper;

    /**
     * 查询 
     * 
     * @param idEmployee  ID
     * @return  
     */
    @Override
    public WorkEmployee selectWorkEmployeeById(Long idEmployee)
    {
        return workEmployeeMapper.selectWorkEmployeeById(idEmployee);
    }

    /**
     * 查询 Danh sách
     * 
     * @param workEmployee  
     * @return  
     */
    @Override
    public List<WorkEmployee> selectWorkEmployeeList(WorkEmployee workEmployee)
    {
        return workEmployeeMapper.selectWorkEmployeeList(workEmployee);
    }

    @Override
    public int insertWorkEmployee(WorkEmployee workEmployee)
    {
        return workEmployeeMapper.insertWorkEmployee(workEmployee);
    }

    @Override
    public int updateWorkEmployee(WorkEmployee workEmployee)
    {
        return workEmployeeMapper.updateWorkEmployee(workEmployee);
    }

    @Override
    public int deleteWorkEmployeeByIds(String ids)
    {
        return workEmployeeMapper.deleteWorkEmployeeByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa 信息
     * 
     * @param idEmployee  ID
     * @return 结果
     */
    @Override
    public int deleteWorkEmployeeById(Long idEmployee)
    {
        return workEmployeeMapper.deleteWorkEmployeeById(idEmployee);
    }
}
