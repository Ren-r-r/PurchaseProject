package com.ryz.service.impl;

import com.ryz.entity.Attributes;
import com.ryz.entity.SysMenus;
import com.ryz.entity.SysMenusExample;
import com.ryz.mapper.SysMenusMapper;
import com.ryz.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenusServiceimpl implements SysMenusService {

    @Autowired(required = false)
    private SysMenusMapper SysMenusMapper;

    @Override
    public List<SysMenus> findtree() {
        SysMenusExample Example=new SysMenusExample();
        Example.createCriteria().andParentIdEqualTo((long) 1);
        List<SysMenus> findtree = SysMenusMapper.selectByExample(Example);
        return findtree;
    }

    @Override
    public List<SysMenus> findall(List<SysMenus> list) {
        for (SysMenus trre:list){
            SysMenusExample Example=new SysMenusExample();
            Example.createCriteria().andParentIdEqualTo(trre.getId());
            List<SysMenus> sysmenus = SysMenusMapper.selectByExample(Example);
            //设置孩子属性
            trre.setChildren(sysmenus);
            //设置attributes属性
            Attributes att=new Attributes();
            att.setLikeurl(trre.getLinkUrl());
            trre.setAttributes(att);
            findall(sysmenus);
        }
        return list;
    }
}
