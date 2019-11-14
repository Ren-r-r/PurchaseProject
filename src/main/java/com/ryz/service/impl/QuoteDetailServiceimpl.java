package com.ryz.service.impl;

import com.ryz.entity.QuoteDetail;
import com.ryz.entity.QuoteDetailExample;
import com.ryz.mapper.QuoteDetailMapper;
import com.ryz.service.QuoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteDetailServiceimpl implements QuoteDetailService {

    @Autowired(required = false)
    private QuoteDetailMapper QuoteDetailMapper;

    @Override
    public QuoteDetail findQuoteById(long id) {
        QuoteDetailExample example=new QuoteDetailExample();
        example.createCriteria().andQuoteIdEqualTo(id);
        List<QuoteDetail> quoteDetails = QuoteDetailMapper.selectByExample(example);
        return quoteDetails.size()>0?quoteDetails.get(0):null;
    }

    @Override
    public int UpdateQuoteDetail(QuoteDetail quoteDetail) {
        return QuoteDetailMapper.updateByPrimaryKeySelective(quoteDetail);
    }
}
