package com.qlcx.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.qlcx.common.config.Global;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysMenu;
import com.qlcx.system.domain.SysUser;
import com.qlcx.system.service.ISysConfigService;
import com.qlcx.system.service.ISysMenuService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Home Business Processing
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    // System home page
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        System.out.println("sss1111");
        // Get identity information
        SysUser user = ShiroUtils.getSysUser();
        // Take out the menu based on the user id
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // switch theme
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // system introduction
    @GetMapping("/system/main")
    public String main(ModelMap mmap,@RequestParam(required = false) String listTreeId)
    {
        mmap.put("version", Global.getVersion());
        mmap.addAttribute("listTreeId", listTreeId);
        return "main";
    }
}