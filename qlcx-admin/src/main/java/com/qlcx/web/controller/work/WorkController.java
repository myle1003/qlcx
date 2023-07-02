package com.qlcx.web.controller.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.qlcx.common.core.text.Convert;
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
import com.qlcx.system.domain.Employee;
import com.qlcx.system.domain.Tools;
import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.Work;
import com.qlcx.system.service.IEmployeeService;
import com.qlcx.system.service.IToolsService;
import com.qlcx.system.service.ITreeService;
import com.qlcx.system.service.IWorkService;

@Controller
@RequestMapping("/system/work")
public class WorkController extends BaseController {
	private String prefix = "work";

	@Autowired
	private IWorkService workService;

	@Autowired
	private ITreeService treeService;

	@Autowired
	private IToolsService toolsService;

	@Autowired
	private IEmployeeService employeeService;

	@RequiresPermissions("system:work:view")
	@GetMapping()
	public String work() {
		return prefix + "/work";
	}

	@RequiresPermissions("system:work:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Work work) {
		startPage();
		List<Work> list = workService.selectWorkList(work);
		return getDataTable(list);
	}

	@RequiresPermissions("system:work:export")
	@Log(title = " ", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Work work) {
		List<Work> list = workService.selectWorkList(work);
		ExcelUtil<Work> util = new ExcelUtil<Work>(Work.class);
		return util.exportExcel(list, "work");
	}

	@GetMapping("/add")
	public String add(ModelMap mmap) {
		Tree tree = new Tree();
		mmap.addAttribute("listTree", treeService.selectTreeListWork(tree));
		Tools tools = new Tools();
		mmap.addAttribute("listTool", toolsService.selectToolsList(tools));
		Employee employee = new Employee();
		mmap.addAttribute("listEmployee", employeeService.selectEmployeeList(employee));
		return prefix + "/add";
	}


	@RequiresPermissions("system:work:add")
	@Log(title = " ", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Work work, HttpServletRequest request) throws MessagingException {
		String[] listTree = request.getParameterValues("lTree");
		String[] listTool = request.getParameterValues("lTool");
		String[] listEmployee = request.getParameterValues("lEmployee");
		if (listTree != null) {
			for (int i = 0; i < listTree.length; i++) {
				Tree treeExTree = treeService.selectTreeById(Long.parseLong(listTree[i]));
				if (work.getListWork().contains("1") && treeExTree.getStatusWaterTheTree() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc tưới nước chưa có");
				}
				if (work.getListWork().contains("2") && treeExTree.getStatusFertilize() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc bón phân chưa có");
				}
				if (work.getListWork().contains("3") && treeExTree.getStatusPrune() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc tỉa cây chưa có");
				}
				if (work.getListWork().contains("4") && treeExTree.getStatusAgainstTree() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc chống cây chưa có");
				}
				if (work.getListWork().contains("5") && treeExTree.getStatusCleanTheStump() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc vs gốc cây chưa có");
				}
				if (work.getListWork().contains("6") && treeExTree.getStatusUpdate() != 0) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " không phải cây đang chờ trồng");
				}

			}
		}
		int stt = workService.insertWork(work, listTree, listTool, listEmployee);
		if (stt == 6) {
			return AjaxResult.error("Ngày kết thúc không được nhỏ hơn bắt đầu");
		} else if (stt == 7) {
			return AjaxResult.error("Nhân viên này đã có công việc");
		} else if (stt == 8) {
			return AjaxResult.error("Cây này đã có lịch chăm sóc");
		} else {
			return AjaxResult.success();
		}

	}

	/**
	 * sửa đổi 
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {

		Work work = workService.selectWorkById(id);
		mmap.put("work", work);
		Tree tree = new Tree();
		mmap.addAttribute("listTree", treeService.selectTreeListWork(tree));
		Tools tools = new Tools();
		mmap.addAttribute("listTool", toolsService.selectToolsList(tools));
		Employee employee = new Employee();
		mmap.addAttribute("listEmployee", employeeService.selectEmployeeList(employee));
		return prefix + "/edit";
	}

	/**
	 * sửa đổi 
	 */
	@RequiresPermissions("system:work:edit")
	@Log(title = " ", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Work work, HttpServletRequest request) {
		String[] listTree = request.getParameterValues("lTree");
		String[] listTool = request.getParameterValues("lTool");
		String[] listEmployee = request.getParameterValues("lEmployee");

		if (listTree != null) {

			for (int i = 0; i < listTree.length; i++) {
				Tree treeExTree = treeService.selectTreeById(Long.parseLong(listTree[i]));
				if (work.getListWork().contains("1") && treeExTree.getStatusWaterTheTree() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc tưới nước chưa có");
				}

				if (work.getListWork().contains("2") && treeExTree.getStatusFertilize() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc bón phân chưa có");
				}
				if (work.getListWork().contains("3") && treeExTree.getStatusPrune() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc tỉa cây chưa có");
				}
				if (work.getListWork().contains("4") && treeExTree.getStatusAgainstTree() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc chống cây chưa có");
				}
				if (work.getListWork().contains("5") && treeExTree.getStatusCleanTheStump() != 1) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " công việc vs gốc cây chưa có");
				}
				if (work.getListWork().contains("6") && treeExTree.getStatusUpdate() != 0) {
					return AjaxResult.error("Cây có mã " + treeExTree.getTreeCode() + " không phải cây đang chờ trồng");
				}

			}
		}
		int stt = workService.updateWork(work, listTree, listTool, listEmployee);
		if (stt == 3) {
			return AjaxResult.error("Công việc đã được hoàn thành..");
		} else if (stt == 6) {
			return AjaxResult.error("Ngày bắt đầu không thể nhỏ hơn ngày kết thúc");
		}else if (stt == 7) {
			return AjaxResult.error("Nhân viên này đã có công việc");
		} else if (stt == 8) {
			return AjaxResult.error("Cây này đã có lịch chăm sóc");
		} else {
			return AjaxResult.success();
		}

	}

	/**
	 * Xóa 
	 */
	@RequiresPermissions("system:work:remove")
	@Log(title = " ", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(workService.deleteWorkByIds(ids));
	}

	// hiện thị list cây xanh trên bản đồ công việc tìm đường
	@GetMapping("/map")
	public String treeMap(Tree tree,String trees, ModelMap mmap) {
		Map<String, Object> map = new HashMap<>();
		map.put("listTreeId", Convert.toStrArray(trees));
		tree.setParams(map);
		mmap.addAttribute("listTree", treeService.selectTreeList(tree));
		return prefix + "/map";
	}
	
	// hiện thị list cây xanh và nhân viên trên bản đồ công việc tìm đường
		@GetMapping("/mapEmployee")
		public String mapEmployee(Tree tree,String trees,String employees, ModelMap mmap) {
			Map<String, Object> map = new HashMap<>();
			map.put("listTreeId", Convert.toStrArray(trees));
			tree.setParams(map);
			mmap.addAttribute("listEmployee", employeeService.selectEmployeeListMap(employees));
			mmap.addAttribute("listTree", treeService.selectTreeList(tree));
			return prefix + "/mapEmployee";
		}
}
