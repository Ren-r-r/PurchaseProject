package com.ryz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.*;
import com.ryz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 采购计划控制层
 */
@Controller
public class StockController {

    @Autowired
    private OrdersService OrdersService;//需求计划

    @Autowired
    private StockService StockService;//采购计划

    @Autowired
    private MaterialService MaterialService;//产品

    @Autowired
    private SupplierService SupplierService;//供应商

    @Autowired
    private IdMappingService idMappingService;//编号对应表

    /**
     * 生成采购计划流水号
     * @return
     */
    @ResponseBody
    public String CreateStock(){
        //前三位
        String Num="200";
        //最终用于拼接的采购计划流水号
        String StockNum="";
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String date = dateformat.format(now); //当前日期
        //后五位数
        String endNumStr = "";
        //根据传入的三位数加今天日期模糊查询数据库中的编号
        List<Stock> stocks = StockService.CreateStockNum(Num + date);
        if(stocks.size()==0){//没有数据则初始化
            endNumStr="00001";
        }else{
            //有数据取最大的流水号
            String str = stocks.get(0).getStockNum();
            //取最后5位数
            int endNum=Integer.parseInt(str.substring(11));
            if(endNum>=9999){
                //已经有五位数 不需要在前面加0
                endNumStr+=(endNum+1);
            }else if(endNum>=999){
                endNumStr+="0"+(endNum+1);
            }else if (endNum>=99){
                endNumStr+="00"+(endNum+1);
            }else if(endNum>=9){
                endNumStr+="000"+(endNum+1);
            }else{
                endNumStr+="0000"+(endNum+1);
            }
        }
        StockNum=Num+date+endNumStr;
        return StockNum;
    }

    /**
     * 编制采购计划
     * orderid:用户选中的需求计划id
     * mid:物资编码
     * iID:编号对应表id
     * @return
     */
    @GetMapping("/ItemsStock")
    public String ItemsStock(long id,String mid,long iID, HttpSession session){
        //根据产品编码获取产品表中供应商编号
        Material material = MaterialService.findByMid(mid);
        //根据供应商标号获取供应商名字
        Supplier bySuppler = SupplierService.findBySupplerId(material.getSupplierId());
        //查询需求计划
        Orders order = OrdersService.findById(id);
        //生成采购计划编号
        String StockNum = CreateStock();
        //当前时间
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //采购计划编号
        session.setAttribute("StockNum",StockNum);
        //采购计划编制时间
        session.setAttribute("date",date);
        //需求计划
        session.setAttribute("order",order);
        //供应商
        session.setAttribute("bySuppler",bySuppler);
        //编号对应表id
        session.setAttribute("iID",iID);
        return "redirect:/bianzhicaigoujihua";
    }

    /**
     * 添加采购计划
     * @return
     */
    @PostMapping("/addStock")
    @ResponseBody
    public String addStock(Stock stock,HttpSession session){
        Employee employee = (Employee)session.getAttribute("employee");
        stock.setAuthorId(employee.getEmpNum());
        stock.setAuthor(employee.getEmpName());
        stock.setEndDate(new Date());
        int i = StockService.addStock(stock);
        if(i>0){
            //根据当前采购编号查询采购id用于设置到编号对应表中
            String stockNum =(String) session.getAttribute("StockNum");
            Stock byStockNum = StockService.findByStockNum(stockNum);
            //修改编号对应表中对应的状态
            long iID = (long) session.getAttribute("iID");//编号对应表id
            IdMapping idMapping=new IdMapping();
            idMapping.setId(iID);
            idMapping.setStatus("C001-30");
            idMapping.setStockId(byStockNum.getId());
            idMappingService.updateState(idMapping);
        }
        return "success";
    }

    /**
     * 3表联查:需求,采购,编号对应
     * 查询状态不为C001-10,C001-20的采购计划
     * @return
     */
    @GetMapping("/findStockStates")
    @ResponseBody
    public EasyUIDataGrid findStockStates(@RequestParam(defaultValue ="2") int page,@RequestParam(defaultValue = "2") int rows,Stock stock,String status){
        Page<Object> objects = PageHelper.startPage(page, rows);
        List<Map> stockByStates = StockService.findStockByStates(stock.getStockName(),status,stock.getStockType());
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(stockByStates);
        easyUIDataGrid.setTotal((int)objects.getTotal());
        return easyUIDataGrid;
    }

    /**
     * 详细
     * @param id 采购计划id
     * @param oid 需求计划id
     * @return
     */
    @GetMapping("/findStockAndOrder")
    public String findStockAndOrder(long id,long oid,HttpSession session){
        Orders order = OrdersService.findById(oid);
        Stock stock = StockService.findById(id);
        session.setAttribute("ItemsOrder",order);//需求
        session.setAttribute("ItemsStock",stock);//采购
        return "redirect:/xjfatz_xjfamx";
    }

    /**
     * 根据主键修改状态为C001-40:待审批
     * @return
     */
    @GetMapping("/updateIdMappingState")
    @ResponseBody
    public String updateIdMappingState(long id){
        IdMapping idMapping=new IdMapping();
        idMapping.setId(id);
        idMapping.setStatus("C001-40");
        idMappingService.updateState(idMapping);
        return "success";
    }

    /**
     * 查询状态为C001-40为未审批的采购计划
     * @return
     */
    @GetMapping("/findStockByC4")
    @ResponseBody
    public EasyUIDataGrid findStockByC4(@RequestParam(defaultValue ="3") int page,@RequestParam(defaultValue = "3") int rows){
        Page<Object> objects = PageHelper.startPage(page, rows);
        List<Map> findStockByC4 = StockService.findStockByC4();
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(findStockByC4);
        easyUIDataGrid.setTotal((int)objects.getTotal());
        return easyUIDataGrid;
    }


    /**
     * 修改状态为C001-51部长审批未通过
     * @param iid
     * @param id
     * @return
     */
    @GetMapping("/updateIdMappingStateNoConsent")
    @ResponseBody
    public String updateIdMappingStateNoConsent(long iid,long id){
        //修改状态编号对应表状态
        IdMapping idMapping=new IdMapping();
        idMapping.setId(iid);
        idMapping.setStatus("C001-51");
        idMappingService.updateState(idMapping);
        //修改采购计划表中部长是否同意
        Stock stock=new Stock();
        stock.setId(id);
        stock.setLeadAgree("S002-0");
        stock.setLeadDate(new Date());
        StockService.UpdateSubmitDate(stock);
        return "success";
    }


    /**
     * iid:编码对应表id
     * id:采购计划id
     * 修改状态为C001-50部长审批通过
     * @return
     */
    @GetMapping("/updateIdMappingStateByAppy")
    @ResponseBody
    public String updateIdMappingStateByAppy(long iid,long id){
        //修改状态编号对应表状态
        IdMapping idMapping=new IdMapping();
        idMapping.setId(iid);
        idMapping.setStatus("C001-50");
        idMappingService.updateState(idMapping);
        //修改采购计划表中部长是否同意
        Stock stock=new Stock();
        stock.setId(id);
        stock.setLeadAgree("S002-1");
        stock.setLeadDate(new Date());
        stock.setLeadOpinion("毛问题呀");
        StockService.UpdateSubmitDate(stock);
        return "success";
    }



    /**
     * 查询采购计划状态为C001-50，部长审批通过后但未下达
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/findStockByC5")
    @ResponseBody
    public EasyUIDataGrid findStockByC5(@RequestParam(defaultValue ="2") int page,@RequestParam(defaultValue = "2") int rows){
        Page<Object> objects = PageHelper.startPage(page, rows);
        List<Map> stockByC5 = StockService.findStockByC5();
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(stockByC5);
        easyUIDataGrid.setTotal((int)objects.getTotal());
        return easyUIDataGrid;
    }

    @GetMapping("/findStockByC51")
    @ResponseBody
    public EasyUIDataGrid findStockByC51(@RequestParam(defaultValue ="2") int page,@RequestParam(defaultValue = "2") int rows){
        Page<Object> objects = PageHelper.startPage(page, rows);
        List<Map> stockByC51 = StockService.findStockByC51();
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(stockByC51);
        easyUIDataGrid.setTotal((int)objects.getTotal());
        return easyUIDataGrid;
    }



    /**
     * 修改状态为C001-60,已下达
     * @return
     *iid:编号对应表
     * id:采购计划表
     */
    @GetMapping("/UpdateIdMappingC6")
    @ResponseBody
    public String UpdateIdMappingC6(long iid,long id){
        //根据主键添加下达时间
        Stock stock=new Stock();
        stock.setId(id);
        stock.setSubmitDate(new Date());
        StockService.UpdateSubmitDate(stock);
        //修改编号对应表中状态
        IdMapping idMapping=new IdMapping();
        idMapping.setId(iid);
        idMapping.setStatus("C001-60");
        idMappingService.updateState(idMapping);
        return "success";
    }

    /**
     * 详细
     * @param id 采购计划id
     * @param oid 需求计划id
     * @param iid 编号对应表id
     * @return
     */
    @GetMapping("/findStockAndOrderItems")
    public String findStockAndOrderItems(long id,long oid,long iid,HttpSession session){
        Orders order = OrdersService.findById(oid);
        Stock stock = StockService.findById(id);
        session.setAttribute("ItemsOrders",order);//需求
        session.setAttribute("ItemsStocks",stock);//采购
        session.setAttribute("iid",iid);//将编号对应表中的id存入,用于修改状态为审批
        return "redirect:/xjfatz_xjfamx3";
    }

    /**
     * 详细中的报批:修改状态为待审批
     * @return
     */
    @GetMapping("/updateIdMapingState")
    public String updateIdMapingState(HttpSession session){
        //获取需要修改状态为审批的采购计划编号对应表id
        long iid = (long)session.getAttribute("iid");
        IdMapping idMapping=new IdMapping();
        idMapping.setId(iid);
        idMapping.setStatus("C001-40");
        idMappingService.updateState(idMapping);
        return "redirect:/Project_lists";//跳转至查询页面
    }

    /**删除未通过审批采购计划
     * @param iid 编号对应表id
     * @return
     */
    @GetMapping("/deleteStock")
    @ResponseBody
    public String deleteStock(long iid){
        //根据编号对应表id查询
        IdMapping idMapping = idMappingService.findByid(iid);
        //根据编号对应表中的orderid删除需求
        int i = OrdersService.deleteById(idMapping.getOrderId());
        //根据编号对应表中的Stockid删除采购
        int i1 = StockService.deleteStock(idMapping.getStockId());
        //根据主键删除编号对应
        int i2 = idMappingService.deleteIdMapping(iid);
        return "success";
    }
}
