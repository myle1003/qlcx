package com.qlcx.controller.contact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/contact")
public class ContactController 
{
    private String prefix = "contact";

    @GetMapping("")
    public String userSignIn()
    {
        return prefix + "/contact";
    }


}
