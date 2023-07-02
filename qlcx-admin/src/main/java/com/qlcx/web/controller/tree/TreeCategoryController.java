package com.qlcx.web.controller.tree;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.qlcx.system.domain.TreeAttributes;
import com.qlcx.system.domain.TreeCategory;
import com.qlcx.system.service.ITreeAttributesService;
import com.qlcx.system.service.ITreeCategoryService;

@Controller
@RequestMapping("/system/category")
public class TreeCategoryController extends BaseController {
    private String prefix = "treeCategory";

    @Autowired
    private ITreeCategoryService treeCategoryService;
    
    @Autowired
    private ITreeAttributesService treeAttributesService;

    @RequiresPermissions("system:category:view")
    @GetMapping()
    public String category() {
        return prefix + "/category";
    }

    @RequiresPermissions("system:category:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TreeCategory treeCategory) {
        startPage();
        List<TreeCategory> list = treeCategoryService.selectTreeCategoryList(treeCategory);
        return getDataTable(list);
    }

    @RequiresPermissions("system:category:export")
    @Log(title = "【】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TreeCategory treeCategory) {
        List<TreeCategory> list = treeCategoryService.selectTreeCategoryList(treeCategory);
        ExcelUtil<TreeCategory> util = new ExcelUtil<TreeCategory>(TreeCategory.class);
        return util.exportExcel(list, "category");
    }

    @GetMapping("/add")
    public String add(ModelMap mmap) {
    	TreeAttributes treeAttributes = new TreeAttributes();	
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes));
        return prefix + "/add";
    }

    @RequiresPermissions("system:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TreeCategory treeCategory,HttpServletRequest request) 
    {
    	String[] listAttribute = request.getParameterValues("lAttribute");
        return toAjax(treeCategoryService.insertTreeCategory(treeCategory,listAttribute));
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
    	TreeAttributes treeAttributes = new TreeAttributes();	
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes));
        mmap.put("treeCategory", treeCategoryService.selectTreeCategoryById(id));
        return prefix + "/edit";
    }

    @RequiresPermissions("system:category:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TreeCategory treeCategory,HttpServletRequest request)
    {
    	String[] listAttribute = request.getParameterValues("lAttribute");
        return toAjax(treeCategoryService.updateTreeCategory(treeCategory,listAttribute));
    }

    @RequiresPermissions("system:category:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(treeCategoryService.deleteTreeCategoryByIds(ids));
    }
}
