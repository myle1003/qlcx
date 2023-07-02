package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.Employee;

  
public interface EmployeeMapper 
{
 
    public Employee selectEmployeeById(Long id);

    public List<Employee> selectEmployeeList(Employee employee);

    public List<Employee> selectEmployeeListMap(String[] employees);

    public int insertEmployee(Employee employee);

    public int updateEmployee(Employee employee);
  
    public int deleteEmployeeById(Long id);
  
    public int deleteEmployeeByIds(String[] ids);
}
