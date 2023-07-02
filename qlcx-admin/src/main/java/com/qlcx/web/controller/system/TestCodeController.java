package com.qlcx.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlcx.system.domain.SysUser;
import com.qlcx.system.service.ISysUserService;

@Controller
@RequestMapping("/testcode")
public class TestCodeController {

    final String PREFIX = "system/testcode";

    @Autowired
    private ISysUserService userService;

    @GetMapping()
    public String testCode(ModelMap mmap)
    {
        SysUser list = userService.selectUserByEmail("ir@163.com");
        mmap.put("name", list);
        return PREFIX + "/index";
    }
}