package com.ryz.controller;

import com.ryz.entity.Contract;
import com.ryz.entity.ContractDetail;
import com.ryz.entity.EasyUIDataGrid;
import com.ryz.entity.Supplier;
import com.ryz.service.ContractDetailService;
import com.ryz.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 合同
 */
@Controller
public class ContractController {

    //合同
    @Autowired
    private ContractService ContractService;

    //合同详情
    @Autowired
    private ContractDetailService ContractDetailService;

    @PostMapping("/findContractByEId")
    @ResponseBody
    public EasyUIDataGrid findContractByEId(@RequestParam(defaultValue = "2") int page, @RequestParam(defaultValue = "2")
            int rows, Contract contract, HttpSession session){
        //获取供应商id
        Supplier supplier = (Supplier) session.getAttribute("supplier");
        return ContractService.findContractByEId(page,rows,contract,supplier.getSupplierNum());
    }

    @GetMapping("/findContractAndDe")
    public String findContractAndDe(int id,HttpSession session){
        Contract contract = ContractService.findById(id);
        ContractDetail contractdetail = ContractDetailService.findByContID(id);
        session.setAttribute("contract",contract);
        session.setAttribute("contractdetail",contractdetail);
        return "redirect:/contract_view";
    }
}
