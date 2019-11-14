package com.ryz.service;

import com.ryz.entity.IdMapping;
import com.ryz.entity.Orders;

/**
 * 编号对照
 */
public interface IdMappingService {

    /**
     * 添加状态
     * @param idMapping
     * @return
     */
    int addMapping(IdMapping idMapping);

    /**
     * 根据需求计划id查询
     * @param orderid
     * @return
     */
    IdMapping findMapping(long orderid);

    /**
     * 根据需求计划id修改state
     * @param
     * @return
     */
    int updateState(IdMapping idMapping);

    /**
     * 根据需求id删除
     * @param id
     * @return
     */
    int deleteByorderId(long id);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    IdMapping findByid(long id);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteIdMapping(long id);

}
