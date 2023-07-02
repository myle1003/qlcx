package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.WorkEmployee;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-02-24
 */
public interface IWorkEmployeeService 
{
    public WorkEmployee selectWorkEmployeeById(Long idEmployee);

    public List<WorkEmployee> selectWorkEmployeeList(WorkEmployee workEmployee);

    public int insertWorkEmployee(WorkEmployee workEmployee);

    public int updateWorkEmployee(WorkEmployee workEmployee);
  
    public int deleteWorkEmployeeByIds(String ids);

    /**
     * Xóa 信息
     * 
     * @param idEmployee  ID
     * @return 结果
     */
    public int deleteWorkEmployeeById(Long idEmployee);
}
