package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.Employee;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-02-09
 */
public interface IEmployeeService 
{
 
    public Employee selectEmployeeById(Long id);

    public List<Employee> selectEmployeeList(Employee employee);
    
    
    public List<Employee> selectEmployeeListMap(String employees);

    public int insertEmployee(Employee employee);

    public int updateEmployee(Employee employee);
  
    public int deleteEmployeeByIds(String ids);

    public int deleteEmployeeById(Long id);
}
