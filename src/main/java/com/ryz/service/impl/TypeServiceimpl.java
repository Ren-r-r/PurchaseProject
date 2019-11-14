package com.ryz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Type;
import com.ryz.entity.TypeExample;
import com.ryz.mapper.TypeMapper;
import com.ryz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceimpl implements TypeService {

    @Autowired(required = false)
    private TypeMapper TypeMapper;

    @Override
    public EasyUIDataGrid findType(int pageNum, int pageSize, Type type) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        TypeExample example= null;
        if(type!=null){
            example=new TypeExample();
            TypeExample.Criteria criteria = example.createCriteria();
            if(type.getcNumMaterialType()!=null&&type.getcNumMaterialType()!=""){
                criteria.andCNumMaterialTypeLike("%"+type.getcNumMaterialType()+"%");
            }
            if(type.getcName()!=null&&type.getcName()!=""){
                criteria.andCNameLike("%"+type.getcName()+"%");
            }
        }
        List<Type> types = TypeMapper.selectByExample(example);
        EasyUIDataGrid EasyUIDataGrid=new EasyUIDataGrid();
        EasyUIDataGrid.setRows(types);
        EasyUIDataGrid.setTotal((int)page.getTotal());
        return EasyUIDataGrid;
    }

    @Override
    public int addType(Type type) {
        return TypeMapper.insertSelective(type);
    }

    @Override
    public int updaType(Type type) {
        return TypeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int deleType(Long cId) {
        return TypeMapper.deleteByPrimaryKey(cId);
    }
}
