package com.ryz.controller;

import com.ryz.entity.SysMenus;
import com.ryz.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 菜单控制层
 */
@Controller
public class SysMenusController {

    @Autowired
    private SysMenusService SysMenusService;

    @GetMapping("/gettree")
    @ResponseBody
    public List<SysMenus> gettree(){
        //查询出所有父节点
        List<SysMenus> list = SysMenusService.findtree();
        //递归
        List<SysMenus> trees = SysMenusService.findall(list);
        return trees;
    }
}
