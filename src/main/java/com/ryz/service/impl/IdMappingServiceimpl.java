package com.ryz.service.impl;

import com.ryz.entity.IdMapping;
import com.ryz.entity.IdMappingExample;
import com.ryz.mapper.IdMappingMapper;
import com.ryz.service.IdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdMappingServiceimpl implements IdMappingService {

    @Autowired(required = false)
    private IdMappingMapper IdMappingMapper;

    @Override
    public int addMapping(IdMapping idMapping) {
        return IdMappingMapper.insertSelective(idMapping);
    }

    @Override
    public IdMapping findMapping(long orderid) {
        IdMappingExample example=new IdMappingExample();
        IdMappingExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderid);
        List<IdMapping> idMappings = IdMappingMapper.selectByExample(example);
        return idMappings.size()>0?idMappings.get(0):null;
    }

    @Override
    public int updateState(IdMapping idMapping) {
        return IdMappingMapper.updateByPrimaryKeySelective(idMapping);
    }

    @Override
    public int deleteByorderId(long id) {
        IdMappingExample example=new IdMappingExample();
        example.createCriteria().andOrderIdEqualTo(id);
        return IdMappingMapper.deleteByExample(example);
    }

    @Override
    public IdMapping findByid(long id) {
        return IdMappingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteIdMapping(long id) {
        return IdMappingMapper.deleteByPrimaryKey(id);
    }


}
