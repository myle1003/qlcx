package com.qlcx.web.controller.customer;

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
import com.qlcx.system.domain.CustomerAccount;
import com.qlcx.system.service.ICustomerAccountService;

@Controller
@RequestMapping("/system/account")
public class CustomerAccountController extends BaseController
{
    private String prefix = "system/account";

    @Autowired
    private ICustomerAccountService customerAccountService;

    @RequiresPermissions("system:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    @RequiresPermissions("system:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CustomerAccount customerAccount)
    {
        startPage();
        List<CustomerAccount> list = customerAccountService.selectCustomerAccountList(customerAccount);
        return getDataTable(list);
    }

    @RequiresPermissions("system:account:export")
    @Log(title = "Customer account", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CustomerAccount customerAccount)
    {
        List<CustomerAccount> list = customerAccountService.selectCustomerAccountList(customerAccount);
        ExcelUtil<CustomerAccount> util = new ExcelUtil<CustomerAccount>(CustomerAccount.class);
        return util.exportExcel(list, "account");
    }

    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @RequiresPermissions("system:account:add")
    @Log(title = "Customer account", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CustomerAccount customerAccount)
    {
        return toAjax(customerAccountService.insertCustomerAccount(customerAccount));
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CustomerAccount customerAccount = customerAccountService.selectCustomerAccountById(id);
        mmap.put("customerAccount", customerAccount);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:account:edit")
    @Log(title = "Customer account", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CustomerAccount customerAccount)
    {
        return toAjax(customerAccountService.updateCustomerAccount(customerAccount));
    }

    @RequiresPermissions("system:account:remove")
    @Log(title = "Customer account", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(customerAccountService.deleteCustomerAccountByIds(ids));
    }
}
