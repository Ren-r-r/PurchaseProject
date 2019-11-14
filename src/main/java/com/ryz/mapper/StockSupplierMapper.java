package com.ryz.mapper;

import com.ryz.entity.StockSupplier;
import com.ryz.entity.StockSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockSupplierMapper {
    int countByExample(StockSupplierExample example);

    int deleteByExample(StockSupplierExample example);

    int deleteByPrimaryKey(Long stockId);

    int insert(StockSupplier record);

    int insertSelective(StockSupplier record);

    List<StockSupplier> selectByExample(StockSupplierExample example);

    StockSupplier selectByPrimaryKey(Long stockId);

    int updateByExampleSelective(@Param("record") StockSupplier record, @Param("example") StockSupplierExample example);

    int updateByExample(@Param("record") StockSupplier record, @Param("example") StockSupplierExample example);

    int updateByPrimaryKeySelective(StockSupplier record);

    int updateByPrimaryKey(StockSupplier record);
}