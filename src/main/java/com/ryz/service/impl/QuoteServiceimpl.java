package com.ryz.service.impl;

import com.ryz.entity.Quote;
import com.ryz.entity.QuoteExample;
import com.ryz.mapper.QuoteMapper;
import com.ryz.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceimpl implements QuoteService {

    @Autowired(required = false)
    private QuoteMapper QuoteMapper;


    @Override
    public List<Quote> findQuote(Long Eid,String QueTitle) {
        QuoteExample example=new QuoteExample();
        QuoteExample.Criteria criteria = example.createCriteria();
        criteria.andSupplierIdEqualTo(Eid);
        if(QueTitle!=null&&QueTitle!=""){
            criteria.andQueTitleLike("%"+QueTitle+"%");
        }
        return QuoteMapper.selectByExample(example);
    }

    @Override
    public int deleteQuote(long id) {
        return QuoteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Quote findQuoteBy(long id) {
        return QuoteMapper.selectByPrimaryKey(id);
    }

    @Override
    public int UpdateQuote(Quote quote) {
        return QuoteMapper.updateByPrimaryKeySelective(quote);
    }


}
