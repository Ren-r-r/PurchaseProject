package com.ryz.service;

import com.ryz.entity.SysMenus;

import java.util.List;

/**
 * 菜单接口
 */
public interface SysMenusService {


    //查询所有父节点
    public List<SysMenus> findtree();

    public List<SysMenus>findall(List<SysMenus> list);
}
