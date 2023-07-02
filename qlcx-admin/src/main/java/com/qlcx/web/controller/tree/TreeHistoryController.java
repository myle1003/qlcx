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
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.core.page.TableDataInfo;
import com.qlcx.common.enums.BusinessType;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.system.domain.TreeCategory;
import com.qlcx.system.domain.TreeHistory;
import com.qlcx.system.service.ITreeCategoryService;
import com.qlcx.system.service.ITreeHistoryService;

   
@Controller
@RequestMapping("/system/history")
public class TreeHistoryController extends BaseController
{
    private String prefix = "history";

    @Autowired
    private ITreeHistoryService treeHistoryService;
    @Autowired
    private ITreeCategoryService treeCategoryService;

    @RequiresPermissions("system:history:view")
    @GetMapping()
    public String history(ModelMap mmap)
    {
    	TreeCategory treeCategory = new TreeCategory();
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
        return prefix + "/history";
    }

     
    @RequiresPermissions("system:history:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TreeHistory treeHistory)
    {
        startPage();
        List<TreeHistory> list = treeHistoryService.selectTreeHistoryList(treeHistory);
        return getDataTable(list);
    }
 
    @RequiresPermissions("system:history:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TreeHistory treeHistory)
    {
        List<TreeHistory> list = treeHistoryService.selectTreeHistoryList(treeHistory);
        ExcelUtil<TreeHistory> util = new ExcelUtil<TreeHistory>(TreeHistory.class);
        return util.exportExcel(list, "history");
    }
 
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
 
    @RequiresPermissions("system:history:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TreeHistory treeHistory)
    {
        return toAjax(treeHistoryService.insertTreeHistory(treeHistory));
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TreeHistory treeHistory = treeHistoryService.selectTreeHistoryById(id);
        mmap.put("treeHistory", treeHistory);
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:history:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TreeHistory treeHistory)
    {
        return toAjax(treeHistoryService.updateTreeHistory(treeHistory));
    }
 
    @RequiresPermissions("system:history:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(treeHistoryService.deleteTreeHistoryByIds(ids));
    }
}
