package com.ryz.controller;

import com.ryz.entity.IdMapping;
import com.ryz.service.IdMappingService;
import com.ryz.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 编号对应
 */
@Controller
public class IdMappingController {

    @Autowired
    private IdMappingService IdMappingService;


    /**
     * 根据需求计划id查询对应编号对应表并修改采购进度状态
     * @return
     */
    @GetMapping("/updateState")
    @ResponseBody
    public String updateState(long oid){
        //获取对应编号id
        IdMapping mapping;
        mapping= IdMappingService.findMapping(oid);
        mapping.setStatus("C001-20");
        IdMappingService.updateState(mapping);
        return "success";
    }

}
