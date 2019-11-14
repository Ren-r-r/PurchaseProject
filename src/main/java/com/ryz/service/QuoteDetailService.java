package com.ryz.service;

import com.ryz.entity.QuoteDetail;

/**
 * 报价详情
 */
public interface QuoteDetailService {

    /**
     * 根据报价id查询报价详情
     * @param id
     * @return
     */
    QuoteDetail findQuoteById(long id);

    /**
     * 根据id修改报价详情表
     * @param quoteDetail
     * @return
     */
    int UpdateQuoteDetail(QuoteDetail quoteDetail);
}
