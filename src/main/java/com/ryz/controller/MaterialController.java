package com.ryz.controller;

import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Material;
import com.ryz.entity.Supplier;
import com.ryz.entity.SysUsers;
import com.ryz.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService MaterialService;



    //定义一个全局变量用来接收前端的json数据
    String selectMaterial1;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @param Material
     * @param request
     * @return
     */
    @PostMapping("/showMaterial")
    @ResponseBody
    public EasyUIDataGrid showMaterial(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows, Material Material, HttpServletRequest request){
        //获取当前登录用户id
        SysUsers users =(SysUsers)request.getSession().getAttribute("users");
        EasyUIDataGrid dataGrid = MaterialService.findMathUserId(page, rows, Material,users.getId().intValue());
        return dataGrid;
    }

    /**
     * 添加供应商产品
     * @param Material
     * @return
     */
    @PostMapping("/insertMater")
    @ResponseBody
    public String insertMater(Material Material,HttpServletRequest request){
        SysUsers users = (SysUsers)request.getSession().getAttribute("users");
        Supplier supplier = (Supplier)request.getSession().getAttribute("supplier");
        Material.setUserId(users.getId().intValue());
        Material.setSupplierId(supplier.getSupplierNum());
        int i = MaterialService.addMath(Material);
        return "success";
    }

    /**
     * 修改供应商产品
     * @param Material
     * @return
     */
    @PostMapping("/updateMater")
    @ResponseBody
    public String updateMater(Material Material){
        int i = MaterialService.updateMath(Material);
        return "success";
    }

    /**
     * 删除供应商产品
     * @param Mid
     * @return
     */
    @PostMapping("/delMater")
    @ResponseBody
    public String delMater(Integer Mid){
        int i = MaterialService.deleMath(Mid);
        return "success";
    }

    /**
     * 查询所有供应商商品
     * @return
     */
    @PostMapping("/findAllMaterial")
    @ResponseBody
    public EasyUIDataGrid findAllMaterial(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows){
        return MaterialService.findAllMaterial(page,rows);
    }

    /**
     * 接受前端的JSON数据
     * @param selectMaterial
     * @return
     */
    @GetMapping("/setSelectMaterial")
    @ResponseBody
    public String setSelectMaterial(String selectMaterial){
        //将接收到的json赋值给全局变量
        selectMaterial1=selectMaterial;
        return selectMaterial1;
    }

    /**
     * 将json返回到录入需求计划页面
     * @return
     */
    @GetMapping("/getSelectMaterial")
    @ResponseBody
    public String getSelectMaterial(){
        return selectMaterial1;
    }


}
