package com.ryz.service.impl;

import com.ryz.entity.Supplier;
import com.ryz.entity.SupplierExample;
import com.ryz.mapper.SupplierMapper;
import com.ryz.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceimpl implements SupplierService {

    @Autowired(required = false)
    private SupplierMapper SupplierMapper;

    @Override
    public Supplier findsupler(Long userid) {
        SupplierExample example=new SupplierExample();
        example.createCriteria().andUserIdEqualTo(userid);
        List<Supplier> suppliers = SupplierMapper.selectByExample(example);
        return suppliers.size()>0?suppliers.get(0):null;
    }

    @Override
    public Supplier findBySupplerId(String Sid) {
        SupplierExample example=new SupplierExample();
        example.createCriteria().andSupplierNumEqualTo(Sid);
        List<Supplier> suppliers = SupplierMapper.selectByExample(example);
        return suppliers.size()>0?suppliers.get(0):null;
    }

    @Override
    public List<Map> findSupplierAndUser(String SuppliNum,String comPany,String UserName) {
        return SupplierMapper.findSupplierAndUser( SuppliNum, comPany, UserName);
    }

    @Override
    public Supplier findByid(long id) {
        return SupplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addSuppler(Supplier supplier) {
        return SupplierMapper.insertSelective(supplier);
    }
}
