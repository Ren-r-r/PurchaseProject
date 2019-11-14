package com.ryz.service.impl;

import com.ryz.entity.Stock;
import com.ryz.entity.StockExample;
import com.ryz.mapper.StockMapper;
import com.ryz.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockServiceimpl implements StockService {

    @Autowired(required = false)
    private StockMapper StockMapper;

    @Override
    public Stock findAutoById(String AutoId) {
        StockExample example=new StockExample();
        example.createCriteria().andAuthorIdEqualTo(AutoId);
        List<Stock> stocks = StockMapper.selectByExample(example);
        return stocks.size()>0?stocks.get(0):null;
    }

    @Override
    public List<Stock> CreateStockNum(String StockNum) {
        StockExample example=new StockExample();
        example.createCriteria().andStockNumLike(StockNum+"%");
        example.setOrderByClause("STOCK_NUM DESC");
        return StockMapper.selectByExample(example);
    }

    @Override
    public int addStock(Stock stock) {
        return StockMapper.insertSelective(stock);
    }

    @Override
    public Stock findByStockNum(String StockNum) {
        StockExample example=new StockExample();
        example.createCriteria().andStockNumEqualTo(StockNum);
        List<Stock> stocks = StockMapper.selectByExample(example);
        return stocks.size()>0?stocks.get(0):null;
    }

    @Override
    public List<Map> findStockByStates(String StockName,String State,String StockType) {
        return StockMapper.findStockByStates(StockName,State,StockType);
    }

    @Override
    public Stock findById(long id) {
        return StockMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> findStockByC4() {
        return StockMapper.findStockByC4();
    }

    @Override
    public List<Map> findStockByC5() {
        return StockMapper.findStockByC5();
    }

    @Override
    public List<Map> findStockByC51() {
        return StockMapper.findStockByC51();
    }

    @Override
    public int UpdateSubmitDate(Stock stock) {
        return StockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int deleteStock(long id) {
        return StockMapper.deleteByPrimaryKey(id);
    }
}
