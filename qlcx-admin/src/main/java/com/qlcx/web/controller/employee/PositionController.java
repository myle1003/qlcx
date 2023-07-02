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
import com.qlcx.system.domain.Position;
import com.qlcx.system.service.IPositionService;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.common.core.page.TableDataInfo;


@Controller
@RequestMapping("/system/position")
public class PositionController extends BaseController
{
    private String prefix = "position";

    @Autowired
    private IPositionService positionService;

    @RequiresPermissions("system:position:view")
    @GetMapping()
    public String position()
    {
        return prefix + "/position";
    }

     
    @RequiresPermissions("system:position:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Position position)
    {
        startPage();
        List<Position> list = positionService.selectPositionList(position);
        return getDataTable(list);
    }
 
    @RequiresPermissions("system:position:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Position position)
    {
        List<Position> list = positionService.selectPositionList(position);
        ExcelUtil<Position> util = new ExcelUtil<Position>(Position.class);
        return util.exportExcel(list, "position");
    }
 
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
 
    @RequiresPermissions("system:position:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Position position)
    {
        return toAjax(positionService.insertPosition(position));
    }
 
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Position position = positionService.selectPositionById(id);
        mmap.put("position", position);
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:position:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Position position)
    {
        return toAjax(positionService.updatePosition(position));
    }
 
    @RequiresPermissions("system:position:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(positionService.deletePositionByIds(ids));
    }
}
