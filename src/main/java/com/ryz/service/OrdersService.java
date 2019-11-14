package com.ryz.service;

import com.ryz.entity.Orders;

import java.util.List;
import java.util.Map;

/**
 * 需求计划
 */
public interface OrdersService {

    /**
     * 根据传入的三位数加今天日期模糊查询数据库中的编号
     * @param num
     * @return
     */
    List<Orders> findAllOrderDesc(String num);

    /**
     * 添加需求计划
     * @param orders
     * @return
     */
    int addOrder (Orders orders);

    /**
     * 根据需求计划编码查询需求计划id
     * @param orderNum
     * @return
     */
    Orders findByOrderId(String orderNum);

    /**
     * 查询所有需求计划 分页，条件
     * @return
     */
    List<Orders> findOrders(Orders orders);

    /**
     * 根据需求id查询详情需求
     * @param id
     * @return
     */
    Orders findById(long id);

    /**
     * 根据需求id删除
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 根据需求id修改
     * @param orders
     * @return
     */
    int updateById(Orders orders);

    /**
     * 2表连查询查询状态为C001-20的
     * @param MaterialCode
     * @param MaterialName
     * @param Address
     * @return
     */
    List<Map> findOrderByState(String MaterialCode,String MaterialName,String Address);

}
