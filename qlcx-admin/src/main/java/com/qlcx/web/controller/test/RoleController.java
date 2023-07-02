package com.qlcx.web.controller.test;

import com.qlcx.common.constant.UserConstants;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysRole;
import com.qlcx.system.service.ISysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController{
    @Autowired
    private ISysRoleService roleService;

    /**
     * 
     * @param role
     * @return new role
     */
    @PostMapping("/test-add-role")
    @ResponseBody
    public AjaxResult addRole(@Validated SysRole role) {
        if(UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            return error("Add role " + role.getRoleName() + " failed, role name already existed");
        }else if(UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))){
            return error("Add role" + role.getRoleName() + " failed, role permissrions already existed");
        }
        role.setCreateBy(ShiroUtils.getLoginName());
        roleService.insertRole(role);
        return AjaxResult.success("Add role successfully", roleService.selectRoleById(role.getRoleId()));
    }

    /**
     * 
     * @param roleId
     * @param role
     * @return role
     */
    @PutMapping("/test-update-role/")
    @ResponseBody
    public AjaxResult updateRole(Long roleId, @Validated SysRole role){
        if(UserConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            return error("Update role " + role.getRoleName() + " failed, role name already existed");
        }else if(UserConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))){
            return error("Update role" + role.getRoleName() + " failed, role permissrions already existed");
        }
        role.setUpdateBy(ShiroUtils.getLoginName());
        roleService.updateRole(role);
        return AjaxResult.success("Update role successfully", roleService.selectRoleById(roleId));
    }

    /**
     * 
     * @param roleId
     * @return null
     */
    @DeleteMapping("/test-delete-role")
    @ResponseBody
    public AjaxResult deleteRole(Long roleId){
        roleService.deleteRoleById(roleId);
        return AjaxResult.success("Delete role successfully");
    }
    
    /**
     * 
     * @param roleId
     * @return role
     */
    @GetMapping("/test-get-role/{roleId}")
    @ResponseBody
    public AjaxResult getRole(@PathVariable("roleId") Long roleId){
        return AjaxResult.success("Get role successfully", roleService.selectRoleById(roleId));
    }

    /**
     * 
     * @param role
     * @return role list
     */
    @GetMapping("/test-get-role-list")
    @ResponseBody
    public AjaxResult getRoleList(SysRole role){
        return AjaxResult.success("Get role list successfully", roleService.selectRoleList(role));
    }
}