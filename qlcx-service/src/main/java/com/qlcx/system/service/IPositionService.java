package com.qlcx.system.service;

import java.util.List;
import com.qlcx.system.domain.Position;

/**
 *  Service接口
 * 
 * @author qlcx
 * @date 2021-02-10
 */
public interface IPositionService 
{
 
    public Position selectPositionById(Long id);

    public List<Position> selectPositionList(Position position);

    public int insertPosition(Position position);
 
    public int updatePosition(Position position);
  
    public int deletePositionByIds(String ids);

    public int deletePositionById(Long id);
}
