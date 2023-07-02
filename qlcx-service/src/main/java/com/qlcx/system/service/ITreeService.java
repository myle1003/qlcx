package com.qlcx.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.dto.BarResponse;
import com.qlcx.system.domain.dto.LineResponse;
import com.qlcx.system.domain.dto.LngLatRequest;
import com.qlcx.system.domain.dto.StatisticalResponse;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-02-09
 */
public interface ITreeService 
{
 
    public Tree selectTreeById(Long id);

    //hiển thị cây xanh ở vị trí gần nhất
    public Tree selectTreeByLngLatAdjacent(String lng,String lat);

 
    public List<Tree> selectTreeList(Tree tree);
    
    public List<Tree> selectTreeListWork(Tree tree);
    
    public Boolean selectTreeLngLat(String lng,String lat,String treeId);
    
    public Tree selectTreeMapLngLat(String lng,String lat,String treeId);
    
    public Boolean selectTreeCode(String treeCode,String treeId);
    
    public String selectTreeImageByLngLat(String lng,String lat);
  
    public int insertTree(Tree tree,String[] listAttibute,MultipartFile file);
  
    public int updateTree(Tree tree,String[] listAttribute,String description,String rowsEdit,MultipartFile file);
  
    public int deleteTreeByIds(String ids);

    public int deleteTreeById(Long id);
    
    public StatisticalResponse selectTreeStatisticalResponse(String listTreeId);
    
    public List<BarResponse> selectTreeBarResponse(String listTreeId);
    
    public List<LineResponse> selectTreeLineResponse(Date startDay,Date endDay,String listTreeId);
    
    public List<Tree> selectTreeListBylistLngLat(List<LngLatRequest> listLngLat);
    
    public int checkStatus();
    
}
