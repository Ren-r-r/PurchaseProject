package com.ryz.controller;

import com.ryz.entity.QuoteDetail;
import com.ryz.service.QuoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 报价详情
 */
@Controller
public class QuoteDetailController {

    @Autowired
    private QuoteDetailService QuoteDetailService;

/*    @GetMapping("/findQuoteItems")
    public String ShowQuote(int id, HttpSession Session){
        QuoteDetail quoteById = QuoteDetailService.findQuoteById(id);
        Session.setAttribute("quoteById",quoteById);
        return "/updateQuote";
    }*/
}
