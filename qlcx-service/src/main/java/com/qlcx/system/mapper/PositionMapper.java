package com.qlcx.system.mapper;

import java.util.List;
import com.qlcx.system.domain.Position;

public interface PositionMapper 
{
 
    public Position selectPositionById(Long id);

    public List<Position> selectPositionList(Position position);

    public int insertPosition(Position position);
 
    public int updatePosition(Position position);
  
    public int deletePositionById(Long id);
  
    public int deletePositionByIds(String[] ids);
}
