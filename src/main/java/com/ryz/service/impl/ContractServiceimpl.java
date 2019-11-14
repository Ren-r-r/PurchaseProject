package com.ryz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.Contract;
import com.ryz.entity.ContractExample;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.mapper.ContractMapper;
import com.ryz.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceimpl implements ContractService {

    @Autowired(required = false)
    private ContractMapper ContractMapper;

    @Override
    public EasyUIDataGrid findContractByEId(int pageNum, int pageSize, Contract contract, String suppNum) {
        //分页插件放第一
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        ContractExample example=new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andSupplierNumEqualTo(suppNum);
        if(contract!=null){
            if(contract.getContNum()!=null&&contract.getContNum()!=""){
                criteria.andContNumLike("%"+contract.getContNum()+"%");
            }
        }
        List<Contract> contracts = ContractMapper.selectByExample(example);
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(contracts);
        easyUIDataGrid.setTotal((int)objects.getTotal());
        return easyUIDataGrid;
    }

    @Override
    public Contract findById(long id) {
        return ContractMapper.selectByPrimaryKey(id);
    }
}
