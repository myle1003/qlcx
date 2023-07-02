package com.qlcx.web.controller.tree;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qlcx.common.core.text.Convert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.qlcx.common.annotation.Log;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.core.page.TableDataInfo;
import com.qlcx.common.enums.BusinessType;
import com.qlcx.common.utils.poi.ExcelUtil;
import com.qlcx.system.domain.Cities;
import com.qlcx.system.domain.Provinces;
import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.TreeAttributes;
import com.qlcx.system.domain.TreeCategory;
import com.qlcx.system.domain.Wards;
import com.qlcx.system.domain.dto.BarResponse;
import com.qlcx.system.domain.dto.LineResponse;
import com.qlcx.system.domain.dto.LngLatRequest;
import com.qlcx.system.domain.dto.StatisticalResponse;
import com.qlcx.system.service.ICitiesService;
import com.qlcx.system.service.IProvincesService;
import com.qlcx.system.service.ITreeAttributesService;
import com.qlcx.system.service.ITreeCategoryService;
import com.qlcx.system.service.ITreeService;
import com.qlcx.system.service.IWardsService;

 
@Controller
@RequestMapping("/system/tree")
public class TreeController extends BaseController
{
    private String prefix = "tree";

    @Autowired
    private ITreeService treeService;
    @Autowired
    private ITreeCategoryService treeCategoryService;
    @Autowired
    private IProvincesService provincesService;
    @Autowired
    private ICitiesService citiesService;
    @Autowired
    private IWardsService wardsService;
    @Autowired
    private ITreeAttributesService treeAttributesService;

    @RequiresPermissions("system:tree:view")
    @GetMapping()
    public String tree(ModelMap mmap,@RequestParam(required = false) String listTreeId)
    {
    	TreeCategory treeCategory = new TreeCategory();
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
        mmap.addAttribute("listTreeId", listTreeId);
        return prefix + "/tree";
    }

     
    @RequiresPermissions("system:tree:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tree tree,String listTreeId)
    {
        //thống kê ở dạng map
        if(!listTreeId.equals("null"))
        {
            Map<String, Object> map = new HashMap<>();
            map.put("listTreeId", Convert.toStrArray(listTreeId));
            System.out.println(listTreeId);
            tree.setParams(map);
        }
        startPage();
        List<Tree> list = treeService.selectTreeList(tree);
        return getDataTable(list);
    }
    
    @RequiresPermissions("system:tree:list")
    @GetMapping("/listTreeByMapAjax")
    @ResponseBody
    public AjaxResult listTreeByMapAjax(Tree tree)
    {
        startPage();
        List<Tree> list = treeService.selectTreeList(tree);
        return AjaxResult.success(list);
    }

    @RequiresPermissions("system:tree:list")
    @GetMapping("/listLineAjax")
    @ResponseBody
    public AjaxResult listLine(Date startDay,Date endDay,@RequestParam String listTreeId)
    {
        startPage();
        List<LineResponse> list = treeService.selectTreeLineResponse(startDay,endDay,listTreeId);
        return AjaxResult.success(list);
    }

    @RequiresPermissions("system:tree:list")
    @GetMapping("/listBarAjax")
    @ResponseBody
    public AjaxResult listBar(@RequestParam String listTreeId)
    {
        startPage();
        List<BarResponse> list = treeService.selectTreeBarResponse(listTreeId);
        return AjaxResult.success(list);
    }

    @RequiresPermissions("system:tree:list")
    @GetMapping("/listAjax")
    @ResponseBody
    public AjaxResult listAjax(@RequestParam String listTreeId)
    {
        startPage();
        StatisticalResponse statisticalResponse = treeService.selectTreeStatisticalResponse(listTreeId);
        return AjaxResult.success(statisticalResponse);
    }

    @RequiresPermissions("system:tree:list")
    @PostMapping("/statisticalAjax")
    @ResponseBody
    public AjaxResult statisticalMap(@RequestBody List<LngLatRequest> listLngLat)
    {
        List<Tree> list = treeService.selectTreeListBylistLngLat(listLngLat);
        return AjaxResult.success(list);
    } 
    @RequiresPermissions("system:tree:export")
    @Log(title = " ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Tree tree)
    {
        List<Tree> list = treeService.selectTreeList(tree);
        ExcelUtil<Tree> util = new ExcelUtil<Tree>(Tree.class);
        return util.exportExcel(list, "tree");
    }
 
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	TreeCategory treeCategory = new TreeCategory();
    	TreeAttributes  treeAttributes = new TreeAttributes();
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes));    
    	return prefix + "/add";
    }
    
    @GetMapping("/addTreeMap/{lng}/{lat}")
    public String addTreeMap(ModelMap mmap,@PathVariable String lng,@PathVariable String lat)
    {
    	TreeCategory treeCategory = new TreeCategory();
    	TreeAttributes  treeAttributes = new TreeAttributes();
    	Tree tree = treeService.selectTreeByLngLatAdjacent(lng,lat);
    	tree.setLongitude(lng.substring(0,10));
    	tree.setLatitude(lat.substring(0,9));
    	mmap.addAttribute("tree", tree);
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes));    
    	return prefix + "/add";
    }
 
    @RequiresPermissions("system:tree:add")
    @Log(title = " ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tree tree,HttpServletRequest request,MultipartFile file)
    {
    	String[] listAttribute = request.getParameterValues("lAttribute");
    	int stt = treeService.insertTree(tree,listAttribute,file);
    	if(stt==3)
    	{
    		return AjaxResult.error("Cây không đủ điều kiện");
    	}else if(stt==0){
    		return AjaxResult.error("Hoạt động không thành công");
		}if(stt==5){
    		return AjaxResult.error("Lỗi ảnh");
		}else if(stt==6)
		{
			return AjaxResult.error("Đã tồn tại vị trí cần thêm");
		}else if(stt==7)
		{
			return AjaxResult.error("Đã tồn tại mã cần thêm");
		}
    	return AjaxResult.success();
    }
 
    @GetMapping("/edit/{id}/{rowsEdit}")
    public String edit(@PathVariable("id") Long id,@PathVariable("rowsEdit") String rowsEdit, ModelMap mmap)
    {  	  
        Tree tree = treeService.selectTreeById(id);
        mmap.put("tree", tree);
        TreeCategory treeCategory = new TreeCategory();
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
    	TreeAttributes  treeAttributes = new TreeAttributes();
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes));
        mmap.put("rowsEdit",rowsEdit);
        return prefix + "/edit";
    }
    
    @GetMapping("/editTreeMap/{lng}/{lat}")
    public String editTreeMap(@PathVariable("lng") String lng,@PathVariable("lat") String lat, ModelMap mmap)
    {  	 
        Tree tree = treeService.selectTreeMapLngLat(lng,lat,null);
        mmap.put("tree", tree);
        TreeCategory treeCategory = new TreeCategory();
    	mmap.addAttribute("category", treeCategoryService.selectTreeCategoryList(treeCategory));
    	TreeAttributes  treeAttributes = new TreeAttributes();
    	mmap.addAttribute("listAttribute", treeAttributesService.selectTreeAttributesList(treeAttributes)); 
        return prefix + "/edit";
    }
 
    @RequiresPermissions("system:tree:edit")
    @Log(title = " ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tree tree,String description,String rowsEdit,HttpServletRequest request,MultipartFile file)
    {
    	String[] listAttribute = request.getParameterValues("lAttribute");
    	int stt = treeService.updateTree(tree, listAttribute, description,rowsEdit,file);
    	if(stt==3)
    	{  
    		return AjaxResult.error("Cây không đủ điều kiện");
    	}else if(stt==4){
    		return AjaxResult.error("Loại cây phải lớn hơn hoặc bằng cây hiện tại");
		}else if(stt==5){
    		return AjaxResult.error("Lỗi ảnh");
		}else if(stt==6){
    		return AjaxResult.error("Đã tồn tại vị trí cần thêm");
		}else if(stt==7){
    		return AjaxResult.error("Đã tồn tại mã cần thêm");
		}else if (stt==1) {
			return AjaxResult.success();
		}else {
			return AjaxResult.error();
		} 	
    }
 
    @RequiresPermissions("system:tree:remove")
    @Log(title = " ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(treeService.deleteTreeByIds(ids));
    }
    
    @RequiresPermissions("system:tree:list")
    @GetMapping("/checkStatus")
    @ResponseBody
    public AjaxResult checkStatus()
    {
    	
        return AjaxResult.success("Thành công",treeService.checkStatus());
    }
    
    @GetMapping("/getProvinces")
    @ResponseBody
    public AjaxResult getProvinces(Provinces provinces)
    {
        return AjaxResult.success(provincesService.selectProvincesList(provinces));
    }

    @GetMapping("/getCities")
    @ResponseBody
    public AjaxResult getCities(Cities cities,Long provincesId)
    {
        cities.setProvinceId(provincesId);
        return AjaxResult.success(citiesService.selectCitiesList(cities));
    }

    @GetMapping("/getWards")
    @ResponseBody
    public AjaxResult getWards(Wards wards,Long citiesId)
    {
        wards.setCityId(citiesId);
        return AjaxResult.success(wardsService.selectWardsList(wards));
    }
}
