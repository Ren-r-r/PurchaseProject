package com.ryz.controller;

import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Enquire;
import com.ryz.service.EnquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 询价表
 */
@Controller
public class EnquireController {

    @Autowired
    private EnquireService enquireService;


    @GetMapping("/showEnquire")
    @ResponseBody
    public EasyUIDataGrid showEnquire(@RequestParam(defaultValue = "4")int page, @RequestParam(defaultValue = "4") int rows, Enquire enquire){
       return enquireService.findAllEnquire(enquire,page,rows);
    }
}
