package com.tjulab.checkin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tjulab.checkin.entity.LeftVacation;
import com.tjulab.checkin.service.CheckInOutService;
import com.tjulab.checkin.vo.QueryCheckRecordResp;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckController {

    @Autowired
    private CheckInOutService checkInOutService;

    @ApiOperation("员工打卡")
    @PostMapping("/checkInOrOut/{empId}/{signType}")
    public boolean checkInOrOut(@PathVariable("empId") long empId ,@PathVariable("signType")  int signType){
        return checkInOutService.signInOrOut(empId , signType);
    }

    @ApiOperation("查询全部打卡记录")
    @GetMapping("/queryAllRecords")
    public List<QueryCheckRecordResp> queryAllSignRecord(){
        return checkInOutService.queryAllSignRecord();
    }

    //传参方式：localhost:8080/queryRecordsByActOrType?account=2021229033&type=1
    @ApiOperation("根据工号以及打卡类型查询打卡记录")
    @GetMapping("/queryRecordsByActOrType")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "员工工号", required = false),
            @ApiImplicitParam(name = "type", value = "打卡类型", required = false)
    })*/
    public List<QueryCheckRecordResp> queryRecordsByActOrType(String account, Integer type){
        return checkInOutService.querySignRecordByAccountOrType(account , type);
    }

    @ApiOperation("根据员工ID查询本人剩余假期时间")
    @GetMapping("/getLeftTimeById/{id}")
    public LeftVacation getLeftTimeById(@PathVariable("id") long empId){
        return checkInOutService.queryLeftTimeByEmpId(empId);
    }
}
