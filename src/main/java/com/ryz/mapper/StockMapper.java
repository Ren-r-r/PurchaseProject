package com.ryz.mapper;

import com.ryz.entity.Stock;
import com.ryz.entity.StockExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import javax.naming.Name;

public interface StockMapper {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    //查询所有采购计划
    List<Map> findStockByStates(@Param("StockName") String StockName,@Param("state") String State,@Param("StockType") String StockType);

    //查询状态为未审批的采购计划
    List<Map>findStockByC4();

    //查询状态为C001-50未下达的采购计划,部长审批通过状态为:C001-50,只有通过的才能是未下达
    List<Map> findStockByC5();

    //查询状态为C001-51审批为通过的采购计划
    List<Map> findStockByC51();

}