package com.ryz.mapper;

import com.ryz.entity.SysCodes;
import com.ryz.entity.SysCodesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCodesMapper {
    int countByExample(SysCodesExample example);

    int deleteByExample(SysCodesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCodes record);

    int insertSelective(SysCodes record);

    List<SysCodes> selectByExample(SysCodesExample example);

    SysCodes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCodes record, @Param("example") SysCodesExample example);

    int updateByExample(@Param("record") SysCodes record, @Param("example") SysCodesExample example);

    int updateByPrimaryKeySelective(SysCodes record);

    int updateByPrimaryKey(SysCodes record);
}