package com.qlcx.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.common.utils.DateUtils;
import com.qlcx.system.domain.Employee;
import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.Work;
import com.qlcx.system.domain.WorkEmployee;
import com.qlcx.system.domain.WorkTools;
import com.qlcx.system.domain.WorkTree;
import com.qlcx.system.mapper.EmployeeMapper;
import com.qlcx.system.mapper.TreeMapper;
import com.qlcx.system.mapper.WorkEmployeeMapper;
import com.qlcx.system.mapper.WorkMapper;
import com.qlcx.system.mapper.WorkToolsMapper;
import com.qlcx.system.mapper.WorkTreeMapper;
import com.qlcx.system.service.IWorkService;

@Service
public class WorkServiceImpl implements IWorkService {
	@Autowired
	private WorkMapper workMapper;

	@Autowired
	private WorkTreeMapper workTreeMapper;

	@Autowired
	private WorkToolsMapper workToolsMapper;

	@Autowired
	private WorkEmployeeMapper workEmployeeMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private TreeMapper treeMapper;

	@Autowired
	private JavaMailSender javaMailSender;

	
	@Override
	public Work selectWorkById(Long id) {
		return workMapper.selectWorkById(id);
	}

	@Override
	public List<Work> selectWorkList(Work work) {
		return workMapper.selectWorkList(work);
	}


	@Override
	public int insertWork(Work work, String[] listTree, String[] listTool, String[] listEmployee)
			throws MessagingException {
		if (work.getExcep() == null) {
			if (work.getEndDay().compareTo(work.getStartDay()) < 0) {
				return 6;
			}

			Work workCheck = new Work();
			for (Work w : workMapper.selectWorkList(workCheck)) {
				for (Employee e : w.getListEmployee()) {
					for (int i = 0; i < listEmployee.length; i++) {
						if (Long.parseLong(listEmployee[i]) == e.getId()
								&& work.getStartDay().compareTo(w.getEndDay()) <= 0) {
							return 7;
						}
					}

				}
				for (Tree t : w.getListTree()) {
					for (int i = 0; i < listTree.length; i++) {
						if (Long.parseLong(listTree[i]) == t.getId()
								&& work.getStartDay().compareTo(w.getEndDay()) <= 0) {
							return 8;
						}
					}
				}

			}
		}
		List<String> email = new ArrayList<String>();
		work.setCreateTime(DateUtils.getNowDate());
		int idWork = workMapper.insertWork(work);

		if (listTree != null) {
			for (int i = 0; i < listTree.length; i++) {
				WorkTree workTree = new WorkTree();
				workTree.setIdTree(Long.parseLong(listTree[i]));
				workTree.setIdWork(work.getId());
				workTreeMapper.insertWorkTree(workTree);
			}
		}
		if (listTool != null) {
			for (int i = 0; i < listTool.length; i++) {
				WorkTools workTools = new WorkTools();
				workTools.setIdTools(Long.parseLong(listTool[i]));
				workTools.setIdWork(work.getId());
				workToolsMapper.insertWorkTools(workTools);
			}
		}
		if (listEmployee != null) {
			for (int i = 0; i < listEmployee.length; i++) {
				email.add(employeeMapper.selectEmployeeById(Long.parseLong(listEmployee[i])).getEmail());
				WorkEmployee workEmployee = new WorkEmployee();
				workEmployee.setIdEmployee(Long.parseLong(listEmployee[i]));
				workEmployee.setIdWork(work.getId());
				workEmployeeMapper.insertWorkEmployee(workEmployee);
			}
		}
//        SendMail sm = new SendMail();
//		sm.sendEmail(email,javaMailSender);
		return idWork;
	}


	@Override
	public int updateWork(Work work, String[] listTree, String[] listTool, String[] listEmployee) {
		
		if (workMapper.selectWorkById(work.getId()).getStatus() != 0) {
			return 3;
		}
		if (work.getExcep() == null) {

			if (work.getEndDay().compareTo(work.getStartDay()) < 0) {
				return 6;
			}

			Work workCheck = new Work();
			for (Work w : workMapper.selectWorkList(workCheck)) {
				for (Employee e : w.getListEmployee()) {
					for (int i = 0; i < listEmployee.length; i++) {
						if (Long.parseLong(listEmployee[i]) == e.getId()
								&& work.getStartDay().compareTo(w.getEndDay()) <= 0
								&& w.getId() !=  work.getId()) {
							return 7;
						}
					}

				}
				for (Tree t : w.getListTree()) {
					for (int i = 0; i < listTree.length; i++) {
						if (Long.parseLong(listTree[i]) == t.getId()
								&& work.getStartDay().compareTo(w.getEndDay()) <= 0
								&& w.getId() !=  work.getId()) {
							return 8;
						}
					}
				}

			}
		}
		work.setUpdateTime(DateUtils.getNowDate());
		workTreeMapper.deleteWorkTreeById(work.getId());

		if (listTree != null) {
			for (int i = 0; i < listTree.length; i++) {
				WorkTree workTree = new WorkTree();
				workTree.setIdTree(Long.parseLong(listTree[i]));
				workTree.setIdWork(work.getId());
				workTreeMapper.insertWorkTree(workTree);
			}
		}

		workToolsMapper.deleteWorkToolsById(work.getId());
		if (listTool != null) {
			for (int i = 0; i < listTool.length; i++) {
				WorkTools workTools = new WorkTools();
				workTools.setIdTools(Long.parseLong(listTool[i]));
				workTools.setIdWork(work.getId());
				workToolsMapper.insertWorkTools(workTools);
			}
		}

		workEmployeeMapper.deleteWorkEmployeeById(work.getId());
		if (listEmployee != null) {
			for (int i = 0; i < listEmployee.length; i++) {
				WorkEmployee workEmployee = new WorkEmployee();
				workEmployee.setIdEmployee(Long.parseLong(listEmployee[i]));
				workEmployee.setIdWork(work.getId());
				workEmployeeMapper.insertWorkEmployee(workEmployee);
			}
		}
		//cập nhật lại các công việc đã làm
		if (work.getStatus() != null) {

			if (listTree != null) {

				for (int i = 0; i < listTree.length; i++) {

					Tree tree = treeMapper.selectTreeById(Long.parseLong(listTree[i]));

					if (work.getListWork().indexOf("1") != -1) {
						tree.setStatusWaterTheTree(0L);
					}
					if (work.getListWork().indexOf("2") != -1) {
						tree.setStatusFertilize(0L);
					}
					if (work.getListWork().indexOf("3") != -1) {
						tree.setStatusPrune(0L);
					}
					if (work.getListWork().indexOf("4") != -1) {
						tree.setStatusAgainstTree(0L);
					}
					if (work.getListWork().indexOf("5") != -1) {
						tree.setStatusCleanTheStump(0L);
					}
					if (work.getListWork().indexOf("6") != -1) {
						tree.setStatusUpdate(1L);
					}
					tree.setTakeCareDay(DateUtils.getNowDate());
					tree.setStatus(0L);
					treeMapper.updateTree(tree);

				}
			}
		}

		return workMapper.updateWork(work);
	}

	@Override
	public int deleteWorkByIds(String ids) {
		for (int i = 0; i < Convert.toStrArray(ids).length; i++) {
			workTreeMapper.deleteWorkTreeById(Long.parseLong(Convert.toStrArray(ids)[i]));
			workToolsMapper.deleteWorkToolsById(Long.parseLong(Convert.toStrArray(ids)[i]));
			workEmployeeMapper.deleteWorkEmployeeById(Long.parseLong(Convert.toStrArray(ids)[i]));
		}
		return workMapper.deleteWorkByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteWorkById(Long id) {
		return workMapper.deleteWorkById(id);
	}
}
