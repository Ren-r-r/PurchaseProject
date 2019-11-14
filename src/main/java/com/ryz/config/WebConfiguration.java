package com.ryz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    //添加视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //首页
        registry.addViewController("index").setViewName("index");
        //动态百叶窗
        registry.addViewController("nav").setViewName("nav");
        //主页面
        registry.addViewController("mainRequire").setViewName("mainRequire");

        /**
         * 供应商平台
         */
        //供应商信息查看
        registry.addViewController("jiffprov_look").setViewName("supplyman/jiffprov_look");
        //修改登录密码
        registry.addViewController("ProviderContract").setViewName("supplyman/ProviderContract");
        //供应商产品维护
        registry.addViewController("proSelect").setViewName("supplyman/proSelect");
        //产品类别的维护
        registry.addViewController("category").setViewName("supplyman/category");
        //报价维护
        registry.addViewController("Project_list").setViewName("supplyman/Project_list");
        //报价维护详细
        registry.addViewController("updateQuote").setViewName("supplyman/updateQuote");
        //询价书报价
        registry.addViewController("Order_wbxjfa_list").setViewName("supplyman/Order_wbxjfa_list");
        //合同查询
        registry.addViewController("ProviderConsignment").setViewName("supplyman/ProviderConsignment");
        //查看合同要素
        registry.addViewController("contract_view").setViewName("supplyman/contract_view");


        /**
         * 计划员
         */
        //需求计划录入
        registry.addViewController("pclass_select").setViewName("planman/pclass_select");
        //录入采购需求
        registry.addViewController("Order_newform").setViewName("planman/Order_newform");
        //需求计划查询
        registry.addViewController("Order_ytb_list").setViewName("planman/Order_ytb_list");
        //需求计划查询详细
        registry.addViewController("print_order_detail").setViewName("planman/print_order_detail");
        //制造中心采购计划编制
        registry.addViewController("Order_wbxjfa_lists").setViewName("planman/Order_wbxjfa_list");
        //编制采购计划
        registry.addViewController("bianzhicaigoujihua").setViewName("planman/bianzhicaigoujihua");
        //采购计划查询
        registry.addViewController("Project_lists").setViewName("planman/Project_list");
        //采购计划明细信息
        registry.addViewController("xjfatz_xjfamx").setViewName("planman/xjfatz_xjfamx");
        //采购计划下达
        registry.addViewController("Project_list4").setViewName("planman/Project_list4");
        //未通过审批采购计划
        registry.addViewController("Project_list3").setViewName("planman/Project_list3");
        //未通过审批采购计划修改
        registry.addViewController("xjfatz_xjfamx3").setViewName("planman/xjfatz_xjfamx3");
        //供应商基本信息查询
        registry.addViewController("provider_cx").setViewName("planman/provider_cx");
        //供应商基本信息查询中的基本信息
        registry.addViewController("Supplier_look").setViewName("planman/Supplier_look");
        //供应商基本信息查询中的注册信息
        registry.addViewController("ProviderContract_look").setViewName("planman/ProviderContract");
        //供应商产品类别管理
        registry.addViewController("categorys").setViewName("planman/category");
        //添加供应商
        registry.addViewController("supply").setViewName("planman/supply");


        /**
         * 财务部长业务处理
         */
        //待审批合同申请
        registry.addViewController("Apply_daishencaiwu").setViewName("contractmanager/Apply_daishencaiwu");
        //采购申请明细
        registry.addViewController("Apply_list_do").setViewName("contractmanager/Apply_list_do");
    }

}
