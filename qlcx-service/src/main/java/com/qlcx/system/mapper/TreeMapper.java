package com.qlcx.system.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qlcx.system.domain.Tree;
import com.qlcx.system.domain.dto.BarResponse;
import com.qlcx.system.domain.dto.LineResponse;
import com.qlcx.system.domain.dto.StatisticalResponse;

  
public interface TreeMapper 
{
 
    public Tree selectTreeById(Long id);
    
    public Tree selectTreeLngLat(@Param("lng") String lng,@Param("lat") String lat,@Param("treeId") String treeId);

    public Tree selectTreeByLngLatAdjacent(@Param("lng") String lng,@Param("lat") String lat);

    public Tree selectTreeCode(@Param("treeCode") String treeCode,@Param("treeId") String treeId);
    
    public String selectTreeImageByLngLat(@Param("lng") String lng,@Param("lat") String lat);
 
    public List<Tree> selectTreeList(Tree tree);
    
    public List<Tree> selectTreeListWork(Tree tree);
  
    public int insertTree(Tree tree);
  
    public int updateTree(Tree tree);

    //cập nhật thông tin nhiều cây cùng lúc
    public int updateTrees(Tree tree);
  
    public int deleteTreeById(Long id);
  
    public int deleteTreeByIds(String[] ids);
    
    public StatisticalResponse selectTreeStatisticalResponse(@Param("listTreeId") String[] listTreeId);
    
    public List<BarResponse> selectTreeBarResponse(@Param("listTreeId") String[] listTreeId);
    
    public List<LineResponse> selectTreeLineResponse(@Param("startDay") Date startDay,@Param("endDay") Date endDay,@Param("listTreeId") String[] listTreeId);
    
    public int checkStatus();
    
    public int checkStatusCleanTheStump();
    
    public int checkStatusFertilize();
    
    public int checkStatusPrune01();
    
    public int checkStatusPrune23();
    
    public int checkStatusAgainstTree();
    
    public int checkStatusWaterTheTree();
    
    //update sau 1 năm
    public int checkStatusOneYearOnType0();
    
    public int checkStatusOneYearOnType1();
    
    public int checkStatusOneYearOnType23();
    
}
