package com.ryz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.*;
import com.ryz.mapper.OrdersMapper;
import com.ryz.service.IdMappingService;
import com.ryz.service.OrdersService;
import javafx.scene.input.DataFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 需求计划
 */
@Controller
public class OrdersController {

    @Autowired
    private OrdersService OrdersService;

    @Autowired
    private IdMappingService IdMappingService;

    /**
     * 生成需求计划流水号
     * @return
     */
    @ResponseBody
    public String createOrderNum(){
        String num="100";
        String orderNum="";//最终数据用来拼接
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String date = dateformat.format(now); //当前日期
        //后五位数
        String endNumStr = "";
        //根据传入的三位数加今天日期模糊查询数据库中的编号
        List<Orders> allOrderDesc = OrdersService.findAllOrderDesc(num + date);
        //没有数据则查询一条新数据
        if(allOrderDesc.size()==0){
            endNumStr="00001";
        }else{
            //有数据则截取后面五位流水号加一
            String str = allOrderDesc.get(0).getOrderNum();
            int endNum=Integer.parseInt(str.substring(11));
            if(endNum>=9999){
                //已经有五位数 不需要在前面加0
                endNumStr+=(endNum+1);
            }else if(endNum>=999){
                //已经有四位数前面加0
                endNumStr+="0"+(endNum+1);
            }else if(endNum>=99){
                //已经有三位数前面加00
                endNumStr+="00"+(endNum+1);
            }else if(endNum>=9){
                //已经有三位数前面加00
                endNumStr+="000"+(endNum+1);
            }else{
                endNumStr = "0000"+(endNum+1);
            }
        }
        //最终数据为 传入的前缀 + 当前日期 + 处理后的五位流水号
        orderNum=num+date+endNumStr;
        return orderNum;
    }

    /**
     * 添加需求计划
     * @return
     */
    @PostMapping("/addOrder")
    @ResponseBody
    public String addOrder(Orders order, Material material, HttpSession session){
        //获取当前编制人从员工表中取(计划员-->员工-->当前登录用户id)
        Employee employee =(Employee) session.getAttribute("employee");
        //设置需求计划表所需内容
        BigDecimal amount=new BigDecimal(order.getAmount());
        BigDecimal SumPrice = order.getUnitPrice().multiply(amount);
        //生成需求计划编号
        String OrderNum = createOrderNum();
        order.setOrderNum(OrderNum);//需求计划编号
        order.setSumPrice(SumPrice);//小计
        order.setStartDate(new Date());//开始时间
        order.setAuthorId(employee.getEmpNum());//编制人编号
        order.setAuthor(employee.getEmpName());//编制人名字
        order.setPhone(employee.getPhone());//电话
        order.setFax(employee.getFax());//传真
        order.setEmail(employee.getEmail());//邮箱
        order.setMaterialCode(material.getMaterialNum());//物资编码
        order.setMaterialName(material.getMaterialName());//物资名称
        order.setMeasureUnit(material.getMaterialUnit());//计量单位
        OrdersService.addOrder(order);
        Orders orderId = OrdersService.findByOrderId(OrderNum);//获取需求计划序号
        //添加至编号对照表中并设置状态为(未编采购计划)C001-20
        IdMapping mapping=new IdMapping();
        mapping.setOrderId(orderId.getId());
        mapping.setStatus("C001-10");
        IdMappingService.addMapping(mapping);
        return "succees";
    }

    /**
     * 查询所有需求计划
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/showOrderState")
    @ResponseBody
    public EasyUIDataGrid showOrderState(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows,Orders orderss,String statuss){
        //整合分页插件(必须放在查询前面)
        Page<Object> pagepl = PageHelper.startPage(page, rows);
        //查询所有需求计划
        List<Orders> orders = OrdersService.findOrders(orderss);
        //创建list集合用于存储处理好的数据
        List list=new ArrayList();
        for (Orders order:orders){
            //根据需求计划id查询对应状态
            IdMapping mapping = IdMappingService.findMapping(order.getId());
            //遍历集合order处理后存入数据的Map集合
            Map itemsMap=new HashMap();
            itemsMap.put("id",order.getId());
            itemsMap.put("materialCode",order.getMaterialCode());
            itemsMap.put("materialName",order.getMaterialName());
            itemsMap.put("amount",order.getAmount());
            itemsMap.put("status",mapping.getStatus());
            list.add(itemsMap);
        }
        //将list集合存入easyUIDataGrid中
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(list);
        easyUIDataGrid.setTotal((int) pagepl.getTotal());
        return easyUIDataGrid;
    }

    /**
     * 根据需求id查询详细
     * @return
     */
    @GetMapping("/ShowItemsOrder")
    public String ShowItemsOrder(int id, Model model){
        Orders OrderItems = OrdersService.findById(id);
        model.addAttribute("OrderItems",OrderItems);
        return "forward:/print_order_detail";
    }

    /**
     * 根据需求id删除
     * @return
     */
    @PostMapping("/deleteById")
    @ResponseBody
    public String deleteById(long id){
        int i = OrdersService.deleteById(id);
        int a=IdMappingService.deleteByorderId(id);
        if(i>0&&a>0){
            return "success";
        }
        return "error";
    }


    /**
     * 根据需求id修改
     * @return
     */
    @PostMapping("/updateById")
    @ResponseBody
    public String updateById(Orders orders){
        int i = OrdersService.updateById(orders);
        if(i>0){
            return "success";
        }
        return "error";
    }

    /**
     * 查询需求状态为C001-20,未编制采购计划的需求
     * @return
     */
    @GetMapping("/ShowOrdersByState")
    @ResponseBody
    public EasyUIDataGrid ShowOrdersByState(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows,Orders orders){
        //整合分页插件(必须放在查询前面)
        Page<Object> pagepl = PageHelper.startPage(page,rows);
        //获取需求状态为C001-20的需求计划
        List<Map> orderByState = OrdersService.findOrderByState(orders.getMaterialCode(),orders.getMaterialName(),orders.getAddress());
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(orderByState);
        easyUIDataGrid.setTotal((int)pagepl.getTotal());
        return easyUIDataGrid;
    }


}
