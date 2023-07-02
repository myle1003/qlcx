package com.qlcx.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qlcx.common.config.Global;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.utils.file.FileUploadUtils;

class UserRequest{

}

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String home()
    {
        return  "index";
    }
    
    @GetMapping("/deploy")
    public String deploy()
    {
        return  "home";
    }
    
    @GetMapping("/operation")
    public String operation()
    {
        return  "home1";
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
        			return AjaxResult.error("errol",e);
        		}
            }
            return AjaxResult.error("errol","file không để trống");
            
        } catch (Exception e) {
            return AjaxResult.error("errol","Errol");
            
        } 
    }
}
