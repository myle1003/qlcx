package com.qlcx.system.service;

import java.util.List;

import javax.mail.MessagingException;

import com.qlcx.system.domain.Work;

public interface IWorkService 
{
 
    public Work selectWorkById(Long id);

    public List<Work> selectWorkList(Work work);

    public int insertWork(Work work,String[] listTree,String[] listTool,String[] listEmployee) throws MessagingException;

    public int updateWork(Work work,String[] listTree,String[] listTool,String[] listEmployee);
  
    public int deleteWorkByIds(String ids);

    public int deleteWorkById(Long id);
}
