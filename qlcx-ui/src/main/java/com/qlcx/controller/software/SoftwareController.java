package com.qlcx.controller.software;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/software")
public class SoftwareController 
{
    private String prefix = "software";

    @GetMapping("")
    public String userSignIn()
    {
        return prefix + "/software";
    }


}
