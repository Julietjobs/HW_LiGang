package com.tjulab.checkin.controller;

import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.service.EmployerService;
import com.tjulab.checkin.vo.QueryEmpInfoResp;
import com.tjulab.checkin.vo.QueryEmpStateResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @ApiOperation("查询全部员工信息")
    @GetMapping("/queryAllEmployeeInfo")
    public List<QueryEmpInfoResp> queryAllEmployeeInfo(){
        return employerService.queryAllEmployeeInfo();
    }

    @ApiOperation("根据id查询员工信息")
    @GetMapping("/queryEmpInfoById/{empId}")
    public Employer queryEmpInfoById(@PathVariable("empId") long empId) {
        return employerService.queryEmpInfoById(empId);
    }

    @ApiOperation("查询全部经理信息")
    @GetMapping("/queryAllEmployerInfo")
    public List<QueryEmpInfoResp> queryAllEmployerInfo() {
        return employerService.queryAllEmployerInfo();
    }

    @ApiOperation("员工登录")
    @GetMapping("login/{account}/{password}")
    public Employer login(@PathVariable("account") String account,@PathVariable("password") String password) {
        return employerService.login(account , password);
    }

    @ApiOperation("新增员工信息")
    @PostMapping("/insertEmpInfo")
    public boolean insertEmpInfo(Employer employer) {
        return employerService.insertEmpInfo(employer);
    }

    @ApiOperation("查询公司全部人员的状态")
    @GetMapping("/queryEmpState")
    public List<QueryEmpStateResp> queryEmpState(){
        return employerService.queryEmpState();
    }

    @ApiOperation("查询当前用户的状态")
    @GetMapping("/queryEmpStateById/{empId}")
    public Integer queryEmpStateById(@PathVariable("empId") long empId) {
        return employerService.queryEmpStateById(empId);
    }

}
