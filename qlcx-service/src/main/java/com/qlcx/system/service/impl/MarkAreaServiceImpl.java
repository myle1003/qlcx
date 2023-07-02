package com.qlcx.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlcx.common.core.text.Convert;
import com.qlcx.system.domain.MarkArea;
import com.qlcx.system.domain.MarkAreaDetail;
import com.qlcx.system.mapper.MarkAreaDetailMapper;
import com.qlcx.system.mapper.MarkAreaMapper;
import com.qlcx.system.service.IMarkAreaService;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2022-03-19
 */
@Service
public class MarkAreaServiceImpl implements IMarkAreaService {
	@Autowired
	private MarkAreaMapper markAreaMapper;

	@Autowired
	private MarkAreaDetailMapper markAreaDetailMapper;

	
	@Override
	public MarkArea selectMarkAreaById(Long id) {
		return markAreaMapper.selectMarkAreaById(id);
	}

	/**
	 * 查询 Danh sách
	 * 
	 * @param markArea  
	 * @return  
	 */
	@Override
	public List<MarkArea> selectMarkAreaList(MarkArea markArea) {
		return markAreaMapper.selectMarkAreaList(markArea);
	}

	/**
	 * Thêm vào 
	 * 
	 * @param markArea  
	 * @return 结果
	 */
	@Override
	public int insertMarkArea(MarkArea markArea) {
		int stt = markAreaMapper.insertMarkArea(markArea);
		for (MarkAreaDetail markAreaDetail : markArea.getListDetail()) {
			markAreaDetail.setIdMarkArea(markArea.getId());
			markAreaDetailMapper.insertMarkAreaDetail(markAreaDetail);
		}
		return stt;
	}

	/**
	 * sửa đổi 
	 * 
	 * @param markArea  
	 * @return 结果
	 */
	@Override
	public int updateMarkArea(MarkArea markArea) {
		int stt = markAreaMapper.updateMarkArea(markArea);
		markAreaDetailMapper.deleteMarkAreaDetailByIdParent(markArea.getId());
		for (MarkAreaDetail markAreaDetail : markArea.getListDetail()) {
			markAreaDetail.setIdMarkArea(markArea.getId());
			markAreaDetailMapper.insertMarkAreaDetail(markAreaDetail);
		}
		return stt;
	}

	/**
	 * Xóa 对象
	 * 
	 * @param ids 需要Xóa的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteMarkAreaByIds(String ids) {
		return markAreaMapper.deleteMarkAreaByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteMarkAreaById(Long id) {
		markAreaDetailMapper.deleteMarkAreaDetailByIdParent(id);
		return markAreaMapper.deleteMarkAreaById(id);
	}
}
