package com.qlcx.web.controller.map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlcx.common.core.controller.BaseController;
import com.qlcx.system.service.ITreeService;

@Controller
@RequestMapping("/system/map")
public class MapController extends BaseController
{
	@Autowired
    private ITreeService treeService;
	
    private String prefix = "map";
    @RequiresPermissions("system:map:view")
    @GetMapping()
    public String showMap()
    {
        return prefix + "/map";
    }
    
    @GetMapping("/image360")
    public String showImage360(String lat,String lng,ModelMap mmap)
    {
    	mmap.addAttribute("image360",treeService.selectTreeImageByLngLat(lng, lat));
        return prefix + "/image360";
    }

}
