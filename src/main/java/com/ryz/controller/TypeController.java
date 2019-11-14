package com.ryz.controller;

import com.ryz.entity.*;
import com.ryz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 供应商材料类型
 */
@Controller
public class TypeController {

    @Autowired
    private TypeService TypeService;

    @PostMapping("/showType")
    @ResponseBody
    public EasyUIDataGrid showMaterial(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows, Type type){
        EasyUIDataGrid easyUIDataGrid = TypeService.findType(page, rows, type);
        return easyUIDataGrid;
    }

    @PostMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        TypeService.addType(type);
        return "success";
    }

    @PostMapping("/updaType")
    @ResponseBody
    public String updaType(Type type){
        TypeService.updaType(type);
        return "success";
    }

    @PostMapping("/deleType")
    @ResponseBody
    public String deleType(Integer cId){
        TypeService.deleType(cId.longValue());
        return "success";
    }

}
