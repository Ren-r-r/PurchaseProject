package com.ryz.service;

import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Type;

/**
 * 物资类型
 */
public interface TypeService {

    /**
     * 分页and条件
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    EasyUIDataGrid findType(int pageNum, int pageSize, Type type);

    /**
     * 添加
     * @param type
     * @return
     */
    int addType(Type type);

    /**
     * 修改
     * @param type
     * @return
     */
    int updaType(Type type);

    /**
     * 删除
     * @param cId
     * @return
     */
    int deleType(Long cId);
}
