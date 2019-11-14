package com.ryz.service;

import com.ryz.entity.Supplier;
import com.ryz.entity.SysUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 供应商基本信息
 */
public interface SupplierService {

    /**
     * 根据当前登录用户查询供应商
     * @param userid
     * @return
     */
    Supplier findsupler(Long userid);

    /**
     * 根据供应商编号查询对应供应商
     * @param Sid
     * @return
     */
    Supplier findBySupplerId(String Sid);

    /**
     * 查询供应商和用户信息
     * @return
     */
    List<Map> findSupplierAndUser(String SuppliNum,String comPany,String UserName);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Supplier findByid(long id);

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    int addSuppler(Supplier supplier);
}
