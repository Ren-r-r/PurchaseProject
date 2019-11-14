package com.ryz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Employee;
import com.ryz.entity.Supplier;
import com.ryz.entity.SysUsers;
import com.ryz.service.EmployeeService;
import com.ryz.service.SupplierService;
import com.ryz.service.SysUsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 供应商基本信息控制层
 */
@Controller
public class SupplierController {

    @Autowired
    private SupplierService SupplierService;//供应商

    @Autowired
    private EmployeeService employeeService;//员工

    @Autowired
    private SysUsersService SysUsersService;//用户

    @GetMapping("/showSupplier")
    public String showSupplier(HttpServletRequest request){
        //获取session中用户
        SysUsers users = (SysUsers) request.getSession().getAttribute("users");
        Supplier supplier = SupplierService.findsupler(users.getId());
        //将供应商对象存入到session中
        request.getSession().setAttribute("supplier",supplier);
        //重定向去显示页面
        return "redirect:/jiffprov_look";
    }

    /**
     * 查询供应商信息和用户信息,分页
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/findSupplierAndUser")
    @ResponseBody
    public EasyUIDataGrid findSupplierAndUser(@RequestParam(defaultValue = "4") int page,@RequestParam(defaultValue = "4") int rows
    ,Supplier supplier,String UserName){
        //分页对象放第一行
        Page<Object> page1 = PageHelper.startPage(page, rows);
        EasyUIDataGrid easyUIDataGrid=new EasyUIDataGrid();
        List<Map> supplierAndUser = SupplierService.findSupplierAndUser(supplier.getSupplierNum(),supplier.getCompany(),UserName);
        easyUIDataGrid.setRows(supplierAndUser);
        easyUIDataGrid.setTotal((int) page1.getTotal());
        return easyUIDataGrid;
    }

    /**
     * id:供应商id
     * 根据选中的供应商id查询基本信息
     * @param id
     * @return
     */
    @GetMapping("/ShowBySupplierId")
    public String ShowBySupplierId(long id, HttpSession session){
        Supplier supplierlook = SupplierService.findByid(id);
        session.setAttribute("supplierlook",supplierlook);
        return "redirect:/Supplier_look";
    }

    /**
     * 用户id
     * @param id
     * @return
     */
    @GetMapping("/ShowByUser")
    public String ShowByUser(long id,HttpSession session){
        SysUsers sysUserlook = SysUsersService.findById(id);
        Employee employeelook = employeeService.findByid(id);
        session.setAttribute("employeelook",employeelook);
        session.setAttribute("sysUserlook",sysUserlook);
        return "redirect:/ProviderContract_look";
    }

    /**
     * 注册供应商
     * @param supplier
     * @return
     */
    @PostMapping("/addSupplier")
    @ResponseBody
    public String addSupplier(Supplier supplier){
        int i = SupplierService.addSuppler(supplier);
        return "success";
    }
}
