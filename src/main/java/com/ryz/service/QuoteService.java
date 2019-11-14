package com.ryz.service;

import com.ryz.entity.Quote;

import java.util.List;

/**
 * 报价
 */
public interface QuoteService {

    /**
     * 根据供应商id查询对应报价信息
     * @return
     */
    List<Quote> findQuote(Long Eid,String QueTitle);

    /**
     * 根据id删除报价信息
     * @param id
     * @return
     */
    int deleteQuote(long id);

    /**
     * 根据报价id查询报价表中需要显示的对应列
     * @param id
     * @return
     */
    Quote findQuoteBy(long id);

    /**
     * 根据id修改报价表
     * @param quote
     * @return
     */
    int UpdateQuote(Quote quote);

}
