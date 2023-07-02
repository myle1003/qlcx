package com.qlcx.system.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qlcx.common.annotation.Excel;
import com.qlcx.common.core.domain.BaseEntity;

public class Work extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date startDay;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date endDay;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long level;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String listWork;
    
    private Long status;
    
    private Long excep;
    
    public Long getExcep() {
		return excep;
	}

	public void setExcep(Long excep) {
		this.excep = excep;
	}

	private List<Tree> listTree;
    
    private List<Tools> listTool;
    
    private List<Employee> listEmployee;
    

	public String getListWork() {
		return listWork;
	}

	public void setListWork(String listWork) {
		this.listWork = listWork;
	}

	public List<Tree> getListTree() {
		return listTree;
	}

	public void setListTree(List<Tree> listTree) {
		this.listTree = listTree;
	}

	public List<Tools> getListTool() {
		return listTool;
	}

	public void setListTool(List<Tools> listTool) {
		this.listTool = listTool;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStartDay(Date startDay) 
    {
        this.startDay = startDay;
    }

    public Date getStartDay() 
    {
        return startDay;
    }
    public void setEndDay(Date endday) 
    {
        this.endDay = endday;
    }

    public Date getEndDay() 
    {
        return endDay;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("startday", getStartDay())
            .append("endday", getEndDay())
            .append("level", getLevel())
            .append("status", getStatus())
            .toString();
    }
}
