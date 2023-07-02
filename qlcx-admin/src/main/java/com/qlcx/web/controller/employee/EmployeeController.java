package com.qlcx.web.controller.employee;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qlcx.common.annotation.Log;
import com.qlcx.common.enums.BusinessType;
import com.qlcx.system.domain.Employee;
import com.qlcx.system.domain.Position;
import com.qlcx.system.service.IEmployeeService;
import com.qlcx.system.service.IPositionService;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.common.core.page.TableDataInfo;

 
@Controller
@RequestMapping("/system/employee")
public class EmployeeController extends BaseController
{
    private String prefix = "employee";

    @Autowired
    private IEmployeeService employeeService;
    
    @Autowired
    private IPositionService positionService;

    @RequiresPermissions("system:employee:view")
    @GetMapping()
    public String employee()
    {
        return prefix + "/employee";
    }

     
    @RequiresPermissions("system:employee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Employee employee)
    {
        startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }
 
    @RequiresPermissions("system:employee:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Employee employee)
    {
        List<Employee> list = employeeService.selectEmployeeList(employee);
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        return util.exportExcel(list, "employee");
    }
 
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	Position position = new Position();
    	mmap.addAttribute("position",positionService.selectPositionList(position));
        return prefix + "/add";
    }
 
    @RequiresPermissions("system:employee:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Employee employee)
    {
        return toAjax(employeeService.insertEmployee(employee));
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Employee employee = employeeService.selectEmployeeById(id);
        mmap.put("employee", employee);
        Position position = new Position();
    	mmap.addAttribute("position",positionService.selectPositionList(position));
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:employee:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Employee employee)
    {
        return toAjax(employeeService.updateEmployee(employee));
    }
 
    @RequiresPermissions("system:employee:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(employeeService.deleteEmployeeByIds(ids));
    }
}
