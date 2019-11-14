package com.ryz.service;

import com.ryz.entity.ContractDetail;

/**
 * 合同详情
 */
public interface ContractDetailService {

    /**
     * 根据合同id查询合同详情
     * @param Cid
     * @return
     */
    ContractDetail findByContID(long Cid);
}
