package com.qlcx.web.controller.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/modal")
public class DemoDialogController {
  private String prefix = "demo/modal";

  @GetMapping("/dialog")
  public String dialog() {
    return prefix + "/dialog";
  }

  @GetMapping("/layer")
  public String layer() {
    return prefix + "/layer";
  }

  @GetMapping("/form")
  public String form() {
    return prefix + "/form";
  }

  @GetMapping("/table")
  public String table() {
    return prefix + "/table";
  }

  @GetMapping("/check")
  public String check() {
    return prefix + "/table/check";
  }

  @GetMapping("/radio")
  public String radio() {
    return prefix + "/table/radio";
  }

  @GetMapping("/parent")
  public String parent() {
    return prefix + "/table/parent";
  }
}
