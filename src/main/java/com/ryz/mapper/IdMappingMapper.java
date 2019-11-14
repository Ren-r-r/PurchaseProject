package com.ryz.mapper;

import com.ryz.entity.IdMapping;
import com.ryz.entity.IdMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdMappingMapper {
    int countByExample(IdMappingExample example);

    int deleteByExample(IdMappingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IdMapping record);

    int insertSelective(IdMapping record);

    List<IdMapping> selectByExample(IdMappingExample example);

    IdMapping selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IdMapping record, @Param("example") IdMappingExample example);

    int updateByExample(@Param("record") IdMapping record, @Param("example") IdMappingExample example);

    int updateByPrimaryKeySelective(IdMapping record);

    int updateByPrimaryKey(IdMapping record);
}