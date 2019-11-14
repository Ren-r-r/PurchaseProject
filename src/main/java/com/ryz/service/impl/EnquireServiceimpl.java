package com.ryz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Enquire;
import com.ryz.entity.EnquireExample;
import com.ryz.mapper.EnquireMapper;
import com.ryz.service.EnquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquireServiceimpl implements EnquireService {

    @Autowired(required = false)
    private EnquireMapper EnquireMapper;

    @Override
    public Enquire findEnquid(long eqid) {
        return EnquireMapper.selectByPrimaryKey(eqid);
    }

    @Override
    public EasyUIDataGrid findAllEnquire(Enquire enquire,int pageSize,int pageNum) {
        Page<Object> objects = PageHelper.startPage(pageSize, pageNum);
        EnquireExample example=null;
        if(enquire!=null){
            example= new EnquireExample();
            EnquireExample.Criteria criteria = example.createCriteria();
                if(enquire.getEnquireNum()!=null&&enquire.getEnquireNum()!=""){
                    criteria.andEnquireNumLike("%"+enquire.getEnquireNum()+"%");
                }
                if(enquire.getEnquireName()!=null&&enquire.getEnquireName()!=""){
                    criteria.andEnquireNameLike("%"+enquire.getEnquireName()+"%");
                }
        }
        List<Enquire> enquires = EnquireMapper.selectByExample(example);
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(enquires);
        easyUIDataGrid.setTotal((int) objects.getTotal());
        return easyUIDataGrid;
    }
}
