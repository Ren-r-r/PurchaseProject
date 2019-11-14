package com.ryz.service.impl;

import com.ryz.entity.ContractDetail;
import com.ryz.entity.ContractDetailExample;
import com.ryz.mapper.ContractDetailMapper;
import com.ryz.service.ContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractDetailServiceimpl implements ContractDetailService {

    @Autowired(required = false)
    private ContractDetailMapper ContractDetailMapper;

    @Override
    public ContractDetail findByContID(long Cid) {
        ContractDetailExample example=new ContractDetailExample();
        example.createCriteria().andContIdEqualTo(Cid);
        List<ContractDetail> contractDetails = ContractDetailMapper.selectByExample(example);
        return contractDetails.size()>0?contractDetails.get(0):null;
    }
}
