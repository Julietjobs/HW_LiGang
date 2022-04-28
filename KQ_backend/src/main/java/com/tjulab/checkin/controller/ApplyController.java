package com.tjulab.checkin.controller;

import com.tjulab.checkin.service.ApplyForVacationService;
import com.tjulab.checkin.vo.QueryApplyInfoResp;
import com.tjulab.checkin.vo.QueryApplyRecordResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ApplyController {

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @ApiOperation("员工申请假期")
    @PostMapping("/applyVacation/{empId}/{startTime}/{duringTime}/{reason}/{type}")
    public boolean applyVacation(@PathVariable("empId") long empId, @PathVariable("startTime")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime, @PathVariable("duringTime") Integer duringTime, @PathVariable("reason") String reason, @PathVariable("type") Integer type){
        return applyForVacationService.applyForVacation(empId ,startTime, duringTime , reason , type );
    }

    @ApiOperation("领导审批假，更新审批表状态")
    @PostMapping("/checkApplyInfo/{applyId}/{state}")
    public boolean checkApplyInfo(@PathVariable("applyId") long applyId ,@PathVariable("state") Integer state){
        return applyForVacationService.updateApplyState(applyId , state);
    }

    @ApiOperation("查询本人请假记录")
    @GetMapping("/getApplyRecordById/{empId}")
    public List<QueryApplyRecordResp> getApplyRecordById(@PathVariable("empId") long empId){
        return applyForVacationService.queryApplyByEmpId(empId);
    }

    @ApiOperation("展示给部门经理的审批表信息，只展示小于3天的请假信息")
    @GetMapping("/getApplyInfoForEvent")
    public List<QueryApplyInfoResp> queryApplyByStateForEventManager(){
        return applyForVacationService.queryApplyByStateForEventManager();
    }

    @ApiOperation("展示给总经理以及副总经理的审批表信息，只展示大于等于3天的请假信息")
    @GetMapping("/getApplyInfoForBig")
    public List<QueryApplyInfoResp> queryApplyByStateForBigManager(){
        return applyForVacationService.queryApplyByStateForBigManager();
    }

    @ApiOperation("根据用户id查询正在申请的记录")
    @GetMapping("/queryApplyingById/{empId}")
    List<QueryApplyInfoResp> queryApplyingById(@PathVariable("empId") long empId){
        return applyForVacationService.queryApplyingById(empId);
    }
}
