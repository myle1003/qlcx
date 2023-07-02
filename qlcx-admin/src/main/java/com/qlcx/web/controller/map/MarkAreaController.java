package com.qlcx.web.controller.map;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlcx.common.annotation.Log;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.enums.BusinessType;
import com.qlcx.system.domain.MarkArea;
import com.qlcx.system.service.IMarkAreaService;

@Controller
@RequestMapping("/system/map/area")
public class MarkAreaController extends BaseController
{

    @Autowired
    private IMarkAreaService markAreaService;
    
    @RequiresPermissions("system:area:list")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(MarkArea markArea)
    {
        startPage();
        List<MarkArea> list = markAreaService.selectMarkAreaList(markArea);
        return AjaxResult.success(list);
    }
 
    @RequiresPermissions("system:area:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody MarkArea markArea)
    {
        return AjaxResult.success(markAreaService.insertMarkArea(markArea));
    }
 
    @RequiresPermissions("system:area:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody MarkArea markArea)
    {
    	markArea.setDescription(null);
    	markArea.setFillColor(null);
    	markArea.setLineColor(null);
        return toAjax(markAreaService.updateMarkArea(markArea));
    }
 
    @RequiresPermissions("system:area:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult delete(@RequestBody Long id)
    {
        return toAjax(markAreaService.deleteMarkAreaById(id));
    }
}
