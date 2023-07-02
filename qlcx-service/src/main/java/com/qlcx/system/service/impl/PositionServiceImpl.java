package com.qlcx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qlcx.system.mapper.PositionMapper;
import com.qlcx.system.domain.Position;
import com.qlcx.system.service.IPositionService;
import com.qlcx.common.core.text.Convert;

/**
 *  Service业务层处理
 * 
 * @author qlcx
 * @date 2021-02-10
 */
@Service
public class PositionServiceImpl implements IPositionService 
{
    @Autowired
    private PositionMapper positionMapper;

 
    @Override
    public Position selectPositionById(Long id)
    {
        return positionMapper.selectPositionById(id);
    }

    /**
     * 查询 Danh sách
     * 
     * @param position  
     * @return  
     */
    @Override
    public List<Position> selectPositionList(Position position)
    {
        return positionMapper.selectPositionList(position);
    }

    @Override
    public int insertPosition(Position position)
    {
        return positionMapper.insertPosition(position);
    }
 
    @Override
    public int updatePosition(Position position)
    {
        return positionMapper.updatePosition(position);
    }

    @Override
    public int deletePositionByIds(String ids)
    {
        return positionMapper.deletePositionByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deletePositionById(Long id)
    {
        return positionMapper.deletePositionById(id);
    }
}
