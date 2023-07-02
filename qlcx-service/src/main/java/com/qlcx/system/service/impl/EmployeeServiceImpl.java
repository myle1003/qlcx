package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.EmployeeMapper;
import com.qlcx.system.domain.Employee;
import com.qlcx.system.service.IEmployeeService;
import com.qlcx.common.core.text.Convert;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2021-02-09
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
    @Autowired
    private EmployeeMapper employeeMapper;

 
    @Override
    public Employee selectEmployeeById(Long id)
    {
        return employeeMapper.selectEmployeeById(id);
    }

    /**
     * 查询 Danh sách
     * 
     * @param employee  
     * @return  
     */
    @Override
    public List<Employee> selectEmployeeList(Employee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }
    
    @Override
    public List<Employee> selectEmployeeListMap(String employees)
    {
        return employeeMapper.selectEmployeeListMap(Convert.toStrArray(employees));
    }


    @Override
    public int insertEmployee(Employee employee)
    {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee)
    {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployeeByIds(String ids)
    {
        return employeeMapper.deleteEmployeeByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteEmployeeById(Long id)
    {
        return employeeMapper.deleteEmployeeById(id);
    }
}
