package com.ryz.controller;

import com.ryz.entity.Employee;
import com.ryz.entity.Supplier;
import com.ryz.entity.SysUsers;
import com.ryz.service.EmployeeService;
import com.ryz.service.SupplierService;
import com.ryz.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户控制层
 */
@Controller
public class SysUsersController {

    @Autowired
    private SysUsersService SysUsersService;

    @Autowired
    private SupplierService SupplierService;

    @Autowired
    private EmployeeService EmployeeService;

    //用户登录
    @PostMapping("/login111")
    @ResponseBody
    public String login(SysUsers sysUsers, HttpServletRequest request){
        SysUsers users = SysUsersService.login(sysUsers);
        if(users!=null){
            Supplier supplier = SupplierService.findsupler(users.getId());
            Employee employee = EmployeeService.findEmployee(users.getId());
            request.getSession().setAttribute("employee",employee);
            request.getSession().setAttribute("users",users);
            request.getSession().setAttribute("supplier",supplier);
            return "index";
        } else{
            return "login.html";
        }

    }


    /**
     * 修改密码
     * @param user
     * @return
     */
    @PostMapping("/UpdatePwd")
    @ResponseBody
    public String updatepwd(SysUsers user, HttpServletRequest request){
        SysUsersService.updatepwd(user);
        //摧毁session
        request.getSession().invalidate();
        return "succeed";
    }

}
