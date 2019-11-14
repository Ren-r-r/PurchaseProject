package com.ryz.service;

import com.ryz.entity.Contract;
import com.ryz.entity.EasyUIDataGrid;

/**
 * 合同业务接口
 */
public interface ContractService {

    /**
     * 根据供应商编号查询对应合同
     * @param suppNum
     * @return
     */
    EasyUIDataGrid findContractByEId(int pageNum, int pageSize, Contract contract, String suppNum);

    /**
     * 根据id查询具体信息
     * @param id
     * @return
     */
    Contract findById(long id);
}
