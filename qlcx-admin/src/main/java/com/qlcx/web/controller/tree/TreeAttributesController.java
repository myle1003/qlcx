package com.qlcx.web.controller.tree;

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
import com.qlcx.system.domain.TreeAttributes;
import com.qlcx.system.service.ITreeAttributesService;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.common.core.page.TableDataInfo;

@Controller
@RequestMapping("/system/attributes")
public class TreeAttributesController extends BaseController
{
    private String prefix = "attributes";

    @Autowired
    private ITreeAttributesService treeAttributesService;

    @RequiresPermissions("system:attributes:view")
    @GetMapping()
    public String attributes()
    {
        return prefix + "/attributes";
    }

     
    @RequiresPermissions("system:attributes:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TreeAttributes treeAttributes)
    {
        startPage();
        List<TreeAttributes> list = treeAttributesService.selectTreeAttributesList(treeAttributes);
        return getDataTable(list);
    }
 
    @RequiresPermissions("system:attributes:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TreeAttributes treeAttributes)
    {
        List<TreeAttributes> list = treeAttributesService.selectTreeAttributesList(treeAttributes);
        ExcelUtil<TreeAttributes> util = new ExcelUtil<TreeAttributes>(TreeAttributes.class);
        return util.exportExcel(list, "attributes");
    }
 
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
 
    @RequiresPermissions("system:attributes:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TreeAttributes treeAttributes)
    {
        return toAjax(treeAttributesService.insertTreeAttributes(treeAttributes));
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TreeAttributes treeAttributes = treeAttributesService.selectTreeAttributesById(id);
        mmap.put("treeAttributes", treeAttributes);
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:attributes:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TreeAttributes treeAttributes)
    {
        return toAjax(treeAttributesService.updateTreeAttributes(treeAttributes));
    }
 
    @RequiresPermissions("system:attributes:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(treeAttributesService.deleteTreeAttributesByIds(ids));
    }
}
