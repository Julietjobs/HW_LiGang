package com.tjulab.checkin.service;

import com.tjulab.checkin.entity.Apply;
import com.tjulab.checkin.vo.QueryApplyInfoResp;
import com.tjulab.checkin.vo.QueryApplyRecordResp;

import java.util.Date;
import java.util.List;

public interface ApplyForVacationService {

    /**
     * 员工申请假期，需要填写请假开始时间，请假总时长，理由，请假类型
     * @param empId
     * @param duringTime
     * @param reason
     * @param type
     * @return
     */
    boolean applyForVacation(long empId , Date startTime , Integer duringTime , String reason , Integer type);

    /**
     * 负责人原审批员工请假，根据申请ID号修改申请表中的状态
     * @param applyId
     * @param state
     * @return
     */
    boolean updateApplyState(long applyId , Integer state);

    /**
     * 查询本人请假记录
     * @param empId
     * @return
     */
    List<QueryApplyRecordResp> queryApplyByEmpId(long empId);

    /**
     * 用于展示给项目经理，只需要审批state为1的审批记录并且申请天数小于3天
     * @return
     */
    List<QueryApplyInfoResp> queryApplyByStateForEventManager();

    /**
     * 用于展示给总经理经理，只需要审批state为1的审批记录并且申请天数大于3天
     * @return
     */
    List<QueryApplyInfoResp> queryApplyByStateForBigManager();

    /**
     * 根据用户id查询正在申请的记录
     * @param empId
     * @return
     */
    List<QueryApplyInfoResp> queryApplyingById(long empId);

}
