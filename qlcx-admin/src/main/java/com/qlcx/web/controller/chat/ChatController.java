package com.qlcx.web.controller.chat;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qlcx.common.config.Global;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.file.FileUploadUtils;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysUser;

class UserRequest{

}

@Controller
@RequestMapping("/chat")
public class ChatController {
    private String prefix = "chat";

    @RequiresPermissions("system:chat:view")
    @GetMapping()
    public String chat(ModelMap modelMap)
    {
        SysUser user = ShiroUtils.getSysUser();
        modelMap.put("email", user.getEmail());
        modelMap.put("userName", user.getUserName());
//        return prefix + "/chatroom";
        return "http://localhost:8080/chatroom";
    }

    @GetMapping("/chat-detail/{id}")
    public String chatDetail(@PathVariable("id") String id, ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("email", user.getEmail());
        mmap.put("userName", user.getUserName());
        mmap.put("roomId", id);
        return prefix + "/chat-detail";
    }
    
    @GetMapping("/map")
    public String map(String location,ModelMap mmap)
    {
    	mmap.put("location", location);
        return prefix + "/map";
    }
    
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {      	
            if (!file.isEmpty()) {

            	try {			
                    String pathBuildingFile = FileUploadUtils.upload(Global.getUploadMessage(), file);
                    
                    //super.getAbsoluteFile(pathBuildingFile.replace("profile", Global.getProfile() + "/thumb/"));
                    return AjaxResult.success("Success",pathBuildingFile);
        		} catch (Exception e) {
        			System.out.println("sssssss"+e);
        			return AjaxResult.error("error",e);
        		}
            }
            return AjaxResult.error("error","file không để trống");
            
        } catch (Exception e) {
            return AjaxResult.error("error","error");
            
        } 
    }
}
