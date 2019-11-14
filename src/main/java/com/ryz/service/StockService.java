package com.ryz.service;

import com.ryz.entity.Stock;

import java.util.List;
import java.util.Map;

/**
 * 采购计划
 */
public interface StockService {

    /**
     * 根据编制人(当前供应商)查询采购类型
     * @param AutoId
     * @return
     */
    Stock findAutoById(String AutoId);

    /**
     * 生成采购流水号
     * @param StockNum
     * @return
     */
    List<Stock> CreateStockNum(String StockNum);

    /**
     * 添加采购计划
     * @param stock
     * @return
     */
    int addStock(Stock stock);

    /**
     * 根据当前采购计划编号查询
     * @param StockNum
     * @return
     */
    Stock findByStockNum(String StockNum);

    /**
     * 2表联查
     * 分页.查询所有状态
     * @return
     */
    List<Map> findStockByStates(String StockName,String State,String StockType);

    /**
     * g根据主键查询
     * @param id
     * @return
     */
    Stock findById(long id);

    /**
     * 查询状态为C001-40为审批的采购计划
     * @return
     */
    List<Map> findStockByC4();

    /**
     * 查询采购计划状态为C001-50
     * @return
     */
    List<Map> findStockByC5();

    /**
     * 查询状态为C001-51的采购计划,未通过审批的
     * @return
     */
    List<Map> findStockByC51();

    /**
     * 根据主键添加下达时间
     * @param  stock
     * @return
     */
    int UpdateSubmitDate(Stock stock);

    /**
     * 根据主键删除采购计划
     * @param id
     * @return
     */
    int deleteStock(long id);

}
