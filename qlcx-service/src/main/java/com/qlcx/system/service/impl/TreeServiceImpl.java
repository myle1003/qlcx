package com.qlcx.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qlcx.common.config.Global;
import com.qlcx.common.core.text.Convert;
import com.qlcx.common.utils.DateUtils;
import com.qlcx.common.utils.file.FileUploadUtils;
import com.qlcx.system.domain.AttributeTreeArea;
import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.TreeAttributes;
import com.qlcx.system.domain.TreeCategory;
import com.qlcx.system.domain.TreeHistory;
import com.qlcx.system.domain.dto.BarResponse;
import com.qlcx.system.domain.dto.LineResponse;
import com.qlcx.system.domain.dto.LngLatRequest;
import com.qlcx.system.domain.dto.StatisticalResponse;
import com.qlcx.system.mapper.AttributeTreeAreaMapper;
import com.qlcx.system.mapper.TreeCategoryMapper;
import com.qlcx.system.mapper.TreeHistoryMapper;
import com.qlcx.system.mapper.TreeMapper;
import com.qlcx.system.service.ITreeService;

/**
 *  Service业务层处理
 * @date 2021-02-09
 */
@Service
public class TreeServiceImpl implements ITreeService {
    @Autowired
    private TreeMapper treeMapper;
    @Autowired
    private TreeCategoryMapper treeCategoryMapper;
    @Autowired
    private AttributeTreeAreaMapper attributeTreeAreaMapper;
    @Autowired
    private TreeHistoryMapper treeHistoryMapper;

    /**
     * 查询 
     *
     * @param id  ID
     * @return  
     */
    @Override
    public Tree selectTreeById(Long id) {
        return treeMapper.selectTreeById(id);
    }

    @Override
    public Tree selectTreeByLngLatAdjacent(String lng, String lat) {
        return treeMapper.selectTreeByLngLatAdjacent(lng, lat);
    }

    @Override
    public Boolean selectTreeLngLat(String lng, String lat, String treeId) {
        if (treeMapper.selectTreeLngLat(lng, lat, treeId) != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Tree selectTreeMapLngLat(String lng, String lat, String treeId) {
        return treeMapper.selectTreeLngLat(lng, lat, treeId);
    }

    @Override
    public Boolean selectTreeCode(String treeCode, String treeId) {
        if (treeMapper.selectTreeCode(treeCode, treeId) != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String selectTreeImageByLngLat(String lng, String lat) {

        return treeMapper.selectTreeImageByLngLat(lng, lat);
    }

    /**
     * 查询 Danh sách
     *
     * @param tree  
     * @return  
     */
    @Override
    public List<Tree> selectTreeList(Tree tree) {
        return treeMapper.selectTreeList(tree);
    }

    @Override
    public List<Tree> selectTreeListWork(Tree tree) {
        return treeMapper.selectTreeListWork(tree);
    }

    /**
     * Thêm vào 
     *
     * @param tree  
     * @return 结果
     */
    @Override
    public int insertTree(Tree tree, String[] listAttribute, MultipartFile file) {
        if (selectTreeLngLat(tree.getLongitude(), tree.getLatitude(), null)) {
            return 6;
        }
        if (selectTreeCode(tree.getTreeCode(), null)) {
            return 7;
        }
        try {
            if (!file.isEmpty()) {
                String pathBuildingFile = FileUploadUtils.upload(Global.getUploadTree(), file);
                tree.setImage(pathBuildingFile);
            }
        } catch (Exception e) {
            return 5;
        }

        if (tree.getType() == 0) {
            tree.setWaterTheTree(120L);
            tree.setPrune(4L);
            tree.setFertilize(1L);
            tree.setCleanTheStump(12L);
            tree.setAgainstTree(0L);
        } else if (tree.getType() == 1) {
            tree.setWaterTheTree(0L);
            tree.setPrune(4L);
            tree.setFertilize(1L);
            tree.setCleanTheStump(12L);
            tree.setAgainstTree(1L);
        } else {
            tree.setWaterTheTree(0L);
            tree.setPrune(2L);
            tree.setFertilize(0L);
            tree.setCleanTheStump(12L);
            tree.setAgainstTree(1L);
        }
        tree.setCreateTime(DateUtils.getNowDate());
        tree.setTakeCareDay(DateUtils.getNowDate());
        TreeCategory treeCategory = treeCategoryMapper.selectTreeCategoryById(tree.getIdCategory());
        if (listAttribute != null && treeCategory.getListAttribute() != null) {
            float ratio = 0;
            for (TreeAttributes treeAttribute : treeCategory.getListAttribute()) {
                for (int i = 0; i < listAttribute.length; i++) {
                    if (Long.parseLong(listAttribute[i]) == treeAttribute.getId()) {
                        ratio += 1;
                    }
                }
            }
            System.out.println("333" + ratio / treeCategory.getListAttribute().size());
            if ((ratio / treeCategory.getListAttribute().size()) < 0.5) {

                return 3;
            }
        }
        int stt = treeMapper.insertTree(tree);
        if (listAttribute != null) {
            for (int i = 0; i < listAttribute.length; i++) {
                AttributeTreeArea attributeTreeArea = new AttributeTreeArea();
                attributeTreeArea.setIdAttribute(Long.parseLong(listAttribute[i]));
                attributeTreeArea.setIdTree(tree.getId());
                attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
            }
        }
        return stt;

    }

    /**
     * sửa đổi 
     *
     * @param tree  
     * @return 结果
     */
    @Override
    public int updateTree(Tree tree, String[] listAttribute, String description, String rowsEdit, MultipartFile file) {
        if (selectTreeLngLat(tree.getLongitude(), tree.getLatitude(), tree.getId().toString())) {
            return 6;
        }
        if (selectTreeCode(tree.getTreeCode(), tree.getId().toString())) {
            return 7;
        }
        try {
            if (!file.isEmpty()) {
                String pathBuildingFile = FileUploadUtils.upload(Global.getUploadTree(), file);
                tree.setImage(pathBuildingFile);
            }
        } catch (Exception e) {
            return 5;
        }


        tree.setUpdateTime(DateUtils.getNowDate());
        if (tree.getQttUpdate() == 1) {
            TreeHistory treeHistory = new TreeHistory();
            treeHistory.setName(tree.getName());
            treeHistory.setIdCategory(tree.getIdCategory());
            treeHistory.setType(tree.getType());
            treeHistory.setIdTree(tree.getId());
            treeHistory.setDescription(description);
            treeHistoryMapper.insertTreeHistory(treeHistory);
            if (tree.getType() == 0) {
                tree.setWaterTheTree(120L);
                tree.setPrune(4L);
                tree.setFertilize(1L);
                tree.setCleanTheStump(12L);
                tree.setAgainstTree(0L);
            } else if (tree.getType() == 1) {
                tree.setWaterTheTree(0L);
                tree.setPrune(4L);
                tree.setFertilize(1L);
                tree.setCleanTheStump(12L);
                tree.setAgainstTree(1L);
            } else {
                tree.setWaterTheTree(0L);
                tree.setPrune(2L);
                tree.setFertilize(0L);
                tree.setCleanTheStump(12L);
                tree.setAgainstTree(1L);
            }
            tree.setTakeCareDay(DateUtils.getNowDate());
            tree.setCreateNew(DateUtils.getNowDate());
            tree.setStatusAgainstTree(0L);
            tree.setStatusCleanTheStump(0L);
            tree.setStatusFertilize(0L);
            tree.setStatusPrune(0L);
            tree.setStatusWaterTheTree(0L);
            return treeMapper.updateTree(tree);

        }

        TreeCategory treeCategory = treeCategoryMapper.selectTreeCategoryById(tree.getIdCategory());
        if (listAttribute != null && treeCategory.getListAttribute() != null) {
            float ratio = 0;
            for (TreeAttributes treeAttribute : treeCategory.getListAttribute()) {
                for (int i = 0; i < listAttribute.length; i++) {
                    if (Long.parseLong(listAttribute[i]) == treeAttribute.getId()) {
                        ratio += 1;
                    }
                }
            }
            //tỉ lệ cho phép trồng cây
            System.out.println("ssss+" + ratio / treeCategory.getListAttribute().size());
            if ((ratio / treeCategory.getListAttribute().size()) < 0.5) {

                return 3;
            }
        }

        int stt;
        Tree tree2 = treeMapper.selectTreeById(tree.getId());
        if (tree.getType() == tree2.getType()) {
            stt = treeMapper.updateTree(tree);
        } else if (tree.getType() == 1 && tree2.getType() == 0) {
            tree.setWaterTheTree(0L);
            tree.setAgainstTree(1L);
            stt = treeMapper.updateTree(tree);
        } else if ((tree.getType() == 2 || tree.getType() == 3) && tree2.getType() == 1) {
            tree.setPrune(tree2.getPrune() - 2);
            stt = treeMapper.updateTree(tree);
        } else {
            stt = 4;
        }
        //nếu như edit list aprtment
        if (!rowsEdit.equals("null") && !rowsEdit.equals(tree.getId() + "") && stt != 4) {
            Map<String, Object> map = new HashMap<>();
            map.put("rows", Convert.toStrArray(rowsEdit));
            tree.setParams(map);
            treeMapper.updateTrees(tree);

            if (listAttribute != null) {
                for (int j = 0; j < Convert.toStrArray(rowsEdit).length; j++) {
                    attributeTreeAreaMapper.deleteAttributeTreeAreaByIdTree(Long.parseLong(Convert.toStrArray(rowsEdit)[j]));
                    for (int i = 0; i < listAttribute.length; i++) {
                        AttributeTreeArea attributeTreeArea = new AttributeTreeArea();
                        attributeTreeArea.setIdAttribute(Long.parseLong(listAttribute[i]));
                        attributeTreeArea.setIdTree(Long.parseLong(Convert.toStrArray(rowsEdit)[j]));
                        attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
                    }
                }
            }
        } else {
            if (listAttribute != null && stt != 4) {
                attributeTreeAreaMapper.deleteAttributeTreeAreaByIdTree(tree.getId());
                for (int i = 0; i < listAttribute.length; i++) {
                    AttributeTreeArea attributeTreeArea = new AttributeTreeArea();
                    attributeTreeArea.setIdAttribute(Long.parseLong(listAttribute[i]));
                    attributeTreeArea.setIdTree(tree.getId());
                    attributeTreeAreaMapper.insertAttributeTreeArea(attributeTreeArea);
                }
            }
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
    public int deleteTreeByIds(String ids) {
        return treeMapper.deleteTreeByIds(Convert.toStrArray(ids));
    }

    /**
     * Xóa 信息
     *
     * @param id  ID
     * @return 结果
     */
    @Override
    public int deleteTreeById(Long id) {
        return treeMapper.deleteTreeById(id);
    }

    @Override
    public StatisticalResponse selectTreeStatisticalResponse(String listTreeId ) {
        if(listTreeId.equals("null"))
        {
        	return treeMapper.selectTreeStatisticalResponse(null);
        }else {
        	return treeMapper.selectTreeStatisticalResponse(Convert.toStrArray(listTreeId));
        }
        
    }

    @Override
    public List<BarResponse> selectTreeBarResponse(String listTreeId) {
        if(listTreeId.equals("null"))
        {
        	return treeMapper.selectTreeBarResponse(null);
        }else {
        	return treeMapper.selectTreeBarResponse(Convert.toStrArray(listTreeId));
        }
        
    }

    @Override
    public List<LineResponse> selectTreeLineResponse(Date startDay, Date endDay, String listTreeId) {
        if(listTreeId.equals("null"))
        {
        	return treeMapper.selectTreeLineResponse(startDay, endDay,null);
        }else {
        	return treeMapper.selectTreeLineResponse(startDay, endDay,Convert.toStrArray(listTreeId));
		}
        
    }
    
    
    @Override
    public List<Tree> selectTreeListBylistLngLat(List<LngLatRequest> listLngLat) {
    	Tree tree = new Tree();
    	List<Tree> listByLngLat = new ArrayList<Tree>();
    	for (Tree tr : treeMapper.selectTreeList(tree)) {
			if(IsPointInPolygon(tr,listLngLat))
			{
				listByLngLat.add(tr);
			}
		}
        return listByLngLat;
    }
    
    public Boolean IsPointInPolygon( Tree tree, List<LngLatRequest> listLngLat )
    {
    	Double lng = Double.parseDouble(tree.getLongitude());
    	Double lat = Double.parseDouble(tree.getLatitude());
    	Double minX = listLngLat.get(0).getLng();
    	Double maxX =listLngLat.get(0).getLng();
    	Double minY =listLngLat.get(0).getLat();
    	Double maxY = listLngLat.get(0).getLat();
        for (LngLatRequest lngLatRequest : listLngLat) 
        {
            minX = Math.min(lngLatRequest.getLng(), minX );
            maxX = Math.max(lngLatRequest.getLng(), maxX );
            minY = Math.min(lngLatRequest.getLat(), minY );
            maxY = Math.max(lngLatRequest.getLat(), maxY );	
        }
     
        if ( lng < minX || lng > maxX || lat < minY || lat > maxY )
        {
            return false;
        }
     
        // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
        Boolean inside = false;
        for ( int i = 0, j = listLngLat.size() - 1 ; i < listLngLat.size() ; j = i++ )
        {
            if ( ( listLngLat.get(i).getLat() > lat ) != ( listLngLat.get(j).getLat() > lat ) &&
            		lng < ( listLngLat.get(j).getLng() - listLngLat.get(i).getLng() ) * ( lat - 
            				listLngLat.get(i).getLat() ) / ( listLngLat.get(j).getLat() - listLngLat.get(i).getLat() ) + listLngLat.get(i).getLng() )
            {
                inside = !inside;
            }
        }
     
        return inside;
    }

    @Override
    public int checkStatus() {
        //update trạng thái cây theo chu kỳ
        treeMapper.checkStatusAgainstTree();
        treeMapper.checkStatusCleanTheStump();
        treeMapper.checkStatusFertilize();
        treeMapper.checkStatusPrune01();
        treeMapper.checkStatusPrune23();
        treeMapper.checkStatusWaterTheTree();
        //update sau 1 năm
        treeMapper.checkStatusOneYearOnType0();
        treeMapper.checkStatusOneYearOnType1();
        treeMapper.checkStatusOneYearOnType23();
        return treeMapper.checkStatus();
    }
}
