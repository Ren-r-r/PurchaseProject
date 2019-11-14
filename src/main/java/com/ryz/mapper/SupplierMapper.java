package com.ryz.mapper;

import com.ryz.entity.Supplier;
import com.ryz.entity.SupplierExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    int countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    //查询供应商和用户
    List<Map> findSupplierAndUser(@Param("SuppliNum") String SuppliNum,@Param("comPany") String comPany,@Param("UserName") String UserName);
}