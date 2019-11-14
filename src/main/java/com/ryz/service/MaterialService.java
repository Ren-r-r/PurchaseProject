package com.ryz.service;

import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Material;

import java.util.List;

/**
 * 供应商物资
 */
public interface MaterialService {

    /**
     * 根据当前用id查询产品信息并分页并按条件
     * @param pageNum
     * @param pageSize
     * @param material
     * @param userid
     * @return
     */
    EasyUIDataGrid findMathUserId(int pageNum, int pageSize,Material material,int userid);

    /**
     * 添加供应商产品
     * @param Material
     * @return
     */
    int addMath(Material Material);

    /**
     * 修改供应商商品
     * @param Material
     * @return
     */
    int updateMath(Material Material);

    /**
     * 删除供应商产品
     * @param Mid
     * @return
     */
    int deleMath(long Mid);

    /**
     * 查询首页供应商产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    EasyUIDataGrid findAllMaterial(int pageNum,int pageSize);

    /**
     * 根据物资编码查询对应供应商编号
     * @param mid
     * @return
     */
    Material findByMid(String mid);
}
