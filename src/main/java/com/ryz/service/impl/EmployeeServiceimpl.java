package com.ryz.service.impl;

import com.ryz.entity.Employee;
import com.ryz.entity.EmployeeExample;
import com.ryz.mapper.EmployeeMapper;
import com.ryz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired(required = false)
    private EmployeeMapper EmployeeMapper;

    @Override
    public Employee findEmployee(long userid) {
        EmployeeExample example=new EmployeeExample();
        example.createCriteria().andUserIdEqualTo(userid);
        List<Employee> employees = EmployeeMapper.selectByExample(example);
        return employees.size()>0?employees.get(0):null;
    }

    @Override
    public Employee findByid(long id) {
        return EmployeeMapper.selectByPrimaryKey(id);
    }
}
