package com.ryz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.*;
import com.ryz.service.EnquireService;
import com.ryz.service.QuoteDetailService;
import com.ryz.service.QuoteService;
import com.ryz.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报价控制层
 */
@Controller
public class QuoteController {

    //报价
    @Autowired
    private QuoteService QuoteService;

    //询价
    @Autowired
    private EnquireService EnquireService;

    //采购计划
    @Autowired
    private StockService StockService;

    //报价详情
    @Autowired
    private com.ryz.service.QuoteDetailService QuoteDetailService;

    /**
     * 分页 查询
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @PostMapping("/ShowQuote")
    @ResponseBody
    public EasyUIDataGrid ShowQuote(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows, HttpServletRequest request,String QueTitle,String EnquireName){
        //整合分页插件(必须放在查询前面)
        Page<Object> pagepl = PageHelper.startPage(page, rows);
        //获取供应商id
        Supplier supplier = (Supplier) request.getSession().getAttribute("supplier");
        //根据供应商id查询对应报价
        List<Quote> quote = QuoteService.findQuote(supplier.getId(),QueTitle);
        //创建list集合用于存储处理好的数据
        List list=new ArrayList();
        //遍历报价:需要询价书id
        for (Quote qute:quote){
            //根据报价表中的询价id查询对应询价书的编号
            Enquire enquire = EnquireService.findEnquid(qute.getEnquireId());
            //根据报价表中编制人(供应商序号)查询对应采购类型
            Stock stock = StockService.findAutoById(qute.getStockId() + "");
            //遍历集合qute处理后存入数据的Map集合
            Map itemsMap=new HashMap();
            //设置页面需要显示的对应key和value
            itemsMap.put("ID",qute.getId());//报价id
            itemsMap.put("QueTitle",qute.getQueTitle());//报价书标题
            itemsMap.put("QueDate",qute.getQueDate());//报价提交时间
            itemsMap.put("EnquireName",enquire.getEnquireName());//询价书名称
            itemsMap.put("EndDate",qute.getEndDate());//询价截止时间
            itemsMap.put("StockType",stock.getStockType());//采购类型
            //将Map集合存入到list集合中
            list.add(itemsMap);
        }
        //将list存入到EasyUIDataGrid中
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(list);
        easyUIDataGrid.setTotal((int)pagepl.getTotal());
        return easyUIDataGrid;
    }

    /**
     * 根据id删除报价维护
     * @param id
     * @return
     */
    @GetMapping("/deleteQuote")
    public String deleteQuote(int id){
        QuoteService.deleteQuote(id);
        return "redirect:/Project_list";
    }

    /**
     * 根据报价维护id查询详细报价维护
     * @return
     */
    @GetMapping("/findQuoteItems")
    public String findQuoteItems(int id,HttpServletRequest request){
        Quote quote = QuoteService.findQuoteBy(id);
        QuoteDetail quoteById = QuoteDetailService.findQuoteById(id);
        request.getSession().setAttribute("quoteById",quoteById);
        request.getSession().setAttribute("quote",quote);
        return "redirect:/updateQuote";
    }

    /**
     * 修改报价表和报价详情表
     * @param
     * @param
     * @return
     */
    @PostMapping("/UpdateQuoteD")
    public String UpdateQuoteD(Quote quote,QuoteDetail quoteDetail){
        QuoteService.UpdateQuote(quote);
        QuoteDetailService.UpdateQuoteDetail(quoteDetail);
        return "redirect:/Project_list";
    }

}
