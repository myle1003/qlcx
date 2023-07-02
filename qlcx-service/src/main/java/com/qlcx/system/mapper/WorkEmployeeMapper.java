package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.WorkEmployee;
 
public interface WorkEmployeeMapper 
{
    public WorkEmployee selectWorkEmployeeById(Long idEmployee);

    public List<WorkEmployee> selectWorkEmployeeList(WorkEmployee workEmployee);

    public int insertWorkEmployee(WorkEmployee workEmployee);

    public int updateWorkEmployee(WorkEmployee workEmployee);

    public int deleteWorkEmployeeById(Long idEmployee);

    public int deleteWorkEmployeeByIds(String[] idEmployees);
}
