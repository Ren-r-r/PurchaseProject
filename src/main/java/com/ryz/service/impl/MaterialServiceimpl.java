package com.ryz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Material;
import com.ryz.entity.MaterialExample;
import com.ryz.mapper.MaterialMapper;
import com.ryz.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceimpl implements MaterialService {

    @Autowired(required = false)
    private MaterialMapper MaterialMapper;

    @Override
    public EasyUIDataGrid findMathUserId(int pageNum, int pageSize, Material material, int userid) {
        //整合分页插件(必须放在查询前面)
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        //根据用户id查询
        MaterialExample example= new MaterialExample();
        MaterialExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userid);
        if(material!=null){
            if(material.getMaterialNum()!=null&&material.getMaterialNum()!=""){
                criteria.andMaterialNumLike("%"+material.getMaterialNum()+"%");
            }
            if(material.getMaterialName()!=null&&material.getMaterialName()!=""){
                criteria.andMaterialNameLike("%"+material.getMaterialName()+"%");
            }
            if(material.getMaterialUnit()!=null&&material.getMaterialUnit()!=""){
                //排序
                if(material.getMaterialUnit().equals("产品编号")){
                    example.setOrderByClause("MATERIAL_NUM");
                }
                if(material.getMaterialUnit().equals("价格")){
                    example.setOrderByClause("MATERIAL_PRICE");
                }
                if(material.getMaterialUnit().equals("数量")){
                    example.setOrderByClause("MATERIAL_AM");
                }
            }
        }
        List<Material> materials = MaterialMapper.selectByExample(example);
        //创建自定义对象(EasyUIDataGrid所需)
        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setTotal((int)page.getTotal());
        dataGrid.setRows(materials);
        return dataGrid;
    }

    @Override
    public int addMath(Material Material) {
        return MaterialMapper.insertSelective(Material);
    }

    @Override
    public int updateMath(Material Material) {
        return MaterialMapper.updateByPrimaryKeySelective(Material);
    }

    @Override
    public int deleMath(long Mid) {
        return MaterialMapper.deleteByPrimaryKey(Mid);
    }

    @Override
    public EasyUIDataGrid findAllMaterial(int pageNum, int pageSize) {
        //整合分页插件(必须放在查询前面)
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Material> materials = MaterialMapper.selectByExample(null);
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        easyUIDataGrid.setRows(materials);
        easyUIDataGrid.setTotal((int) page.getTotal());
        return easyUIDataGrid;
    }

    @Override
    public Material findByMid(String mid) {
        MaterialExample example=new MaterialExample();
        example.createCriteria().andMaterialNumEqualTo(mid);
        List<Material> materials = MaterialMapper.selectByExample(example);
        return materials.size()>0?materials.get(0):null;
    }
}
