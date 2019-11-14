package com.ryz.service;

import com.ryz.entity.Employee;

/**
 * 员工信息
 */
public interface EmployeeService {

    /**
     * 根据当前登录用户查询员工信息
     * @param userid
     * @return
     */
    Employee findEmployee (long userid);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Employee findByid(long id);
}
