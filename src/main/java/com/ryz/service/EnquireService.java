package com.ryz.service;

import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Enquire;

/**
 * 询价
 */
public interface EnquireService {

    /**
     * 根据报价表中询价id查询对应询价
     * @param eqid
     * @return
     */
    Enquire findEnquid(long eqid);

    /**
     * 查询所有询价书
     * @return
     */
    EasyUIDataGrid findAllEnquire(Enquire enquire,int pageSize,int pageNum);
}
