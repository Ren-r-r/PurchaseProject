package com.ryz.service.impl;

import com.ryz.entity.Orders;
import com.ryz.entity.OrdersExample;
import com.ryz.mapper.OrdersMapper;
import com.ryz.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceimpl implements OrdersService {

    @Autowired(required = false)
    private OrdersMapper OrdersMapper;

    @Override
    public List<Orders> findAllOrderDesc(String num) {
        OrdersExample example=new OrdersExample();
        example.createCriteria().andOrderNumLike(num+"%");
        //排序
        example.setOrderByClause("ORDER_NUM DESC");
        List<Orders> orders = OrdersMapper.selectByExample(example);
        return orders;
    }

    @Override
    public int addOrder(Orders orders) {
        return OrdersMapper.insertSelective(orders);
    }

    @Override
    public Orders findByOrderId(String orderNum) {
        OrdersExample example=new OrdersExample();
        example.createCriteria().andOrderNumEqualTo(orderNum);
        List<Orders> orders = OrdersMapper.selectByExample(example);
        return orders.size()>0?orders.get(0):null;
    }

    @Override
    public List<Orders> findOrders(Orders orders) {
        OrdersExample example=null;
        if(orders!=null){
            example=new OrdersExample();
            OrdersExample.Criteria criteria = example.createCriteria();
            if(orders.getMaterialCode()!=null&&orders.getMaterialCode()!="") {
                criteria.andMaterialCodeLike("%" + orders.getMaterialCode() + "%");
            }
            if(orders.getMaterialName()!=null&&orders.getMaterialName()!=""){
                criteria.andMaterialNameLike("%"+orders.getMaterialName()+"%");
            }
        }
        return OrdersMapper.selectByExample(example);
    }

    @Override
    public Orders findById(long id) {
        return OrdersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(long id) {
        return OrdersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Orders orders) {
        return OrdersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public List<Map> findOrderByState(String MaterialCode, String MaterialName, String Address) {
        return OrdersMapper.findOrderByState(MaterialCode,MaterialName,Address);
    }

}
