package com.qlcx.controller.map;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.domain.Tree;
import com.qlcx.service.TreeService;


@Controller
@RequestMapping("/map")
public class MapController extends BaseController
{
	@Autowired
	private TreeService treeService;

    @GetMapping()
    public String position()
    {
        return  "map/map";
    }


    @GetMapping("/listTreeByMapAjax")
    @ResponseBody
    public AjaxResult listTreeByMapAjax(Tree tree)
    {
        startPage();
        List<Tree> list = treeService.selectTreeList(tree);
        return AjaxResult.success(list);
    }

  

}
