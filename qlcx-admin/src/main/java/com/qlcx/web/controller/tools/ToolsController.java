package com.qlcx.web.controller.tools;

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
import com.qlcx.system.domain.Tools;
import com.qlcx.system.service.IToolsService;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.common.core.page.TableDataInfo;
 
@Controller
@RequestMapping("/system/tools")
public class ToolsController extends BaseController
{
    private String prefix = "tools";

    @Autowired
    private IToolsService toolsService;

    @RequiresPermissions("system:tools:view")
    @GetMapping()
    public String tools()
    {
        return prefix + "/tools";
    }

     
    @RequiresPermissions("system:tools:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tools tools)
    {
        startPage();
        List<Tools> list = toolsService.selectToolsList(tools);
        return getDataTable(list);
    }
 
    @RequiresPermissions("system:tools:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Tools tools)
    {
        List<Tools> list = toolsService.selectToolsList(tools);
        ExcelUtil<Tools> util = new ExcelUtil<Tools>(Tools.class);
        return util.exportExcel(list, "tools");
    }
 
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
 
    @RequiresPermissions("system:tools:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tools tools)
    {
        return toAjax(toolsService.insertTools(tools));
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Tools tools = toolsService.selectToolsById(id);
        mmap.put("tools", tools);
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:tools:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tools tools)
    {
        return toAjax(toolsService.updateTools(tools));
    }
 
    @RequiresPermissions("system:tools:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(toolsService.deleteToolsByIds(ids));
    }
}
