package com.ryz.controller;

import com.ryz.entity.Employee;
import com.ryz.entity.SysUsers;
import com.ryz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工信息控制层
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService EmployeeService;

    /**
     * 显示员工信息
     * @param request
     * @return
     */
    @GetMapping("/showEmpinfo")
    public String showEmpinfo(HttpServletRequest request){
        //获取当前登录用户
        SysUsers users = (SysUsers)request.getSession().getAttribute("users");
        Employee employee = EmployeeService.findEmployee(users.getId());
        request.getSession().setAttribute("employee",employee);
        return "redirect:/ProviderContract";
    }

}
