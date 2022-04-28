package com.tjulab.checkin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tjulab.checkin.entity.Apply;
import com.tjulab.checkin.entity.ApplyRecord;
import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.entity.LeftVacation;
import com.tjulab.checkin.mapper.ApplyMapper;
import com.tjulab.checkin.mapper.ApplyRecordMapper;
import com.tjulab.checkin.mapper.EmployerMapper;
import com.tjulab.checkin.mapper.LeftVacationMapper;
import com.tjulab.checkin.service.ApplyForVacationService;
import com.tjulab.checkin.vo.QueryApplyInfoResp;
import com.tjulab.checkin.vo.QueryApplyRecordResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplyForVacationServiceImpl implements ApplyForVacationService {

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private LeftVacationMapper leftVacationMapper;

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private ApplyRecordMapper applyRecordMapper;


    /**
     * 员工申请假期或事假，需要填写请假开始时间，请假总时长，理由，请假类型
     * @param empId
     * @param duringTime
     * @param reason
     * @param type
     * @return
     */
    @Override
    public boolean applyForVacation(long empId, Date startTime, Integer duringTime, String reason, Integer type) {
        //上锁，如果该员工有其他的未审批的审批表，则不予审批
        QueryWrapper<Apply> applyQueryWrapper = new QueryWrapper<>();
        applyQueryWrapper.eq("emp_id" , empId).eq("state" , 1);
        List<Apply> applyList = applyMapper.selectList(applyQueryWrapper);
        if(applyList.size() > 1){
            System.out.println("同一时间只能申请一个事项！");
            return false;
        }

        QueryWrapper<LeftVacation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id" , empId);
        List<LeftVacation> leftVacationList = leftVacationMapper.selectList(queryWrapper);
        //申请表中没有该员工的剩余请假记录
        if(leftVacationList.size() == 0){
            System.out.println("该用户剩余假期记录不存在，将初始化");
            LeftVacation leftVacation = new LeftVacation();
            leftVacation.setLeftYear(7);
            leftVacation.setLeftHunJia(3);
            leftVacation.setLeftChanJian(3);
            leftVacation.setLeftChanJia(20);
            leftVacation.setLeftBuRu(30);
            leftVacation.setLeftPeiChan(5);
            leftVacation.setEmpId(empId);
            leftVacationMapper.insert(leftVacation);
        }
        //检查申请的类型，并检查该员工剩余时间是否符合请假时间
        List<LeftVacation> newLeftVacationList = leftVacationMapper.selectList(queryWrapper);
        LeftVacation leftVacation = newLeftVacationList.get(0);
        if(type == 1){//年假
            if(leftVacation.getLeftYear() < duringTime){
                System.out.println("剩余年假时间不足");
                return false;
            }
        }else if(type == 2){//婚假
            if(leftVacation.getLeftHunJia() < duringTime){
                System.out.println("剩余婚假时间不足");
                return false;
            }
        }else if(type == 3){//产检
            if(leftVacation.getLeftChanJian() < duringTime){
                System.out.println("剩余产检时间不足");
                return false;
            }
        }else if(type == 4){//产假
            if(leftVacation.getLeftChanJia() < duringTime){
                System.out.println("剩余产假时间不足");
                return false;
            }
        }else if(type == 5){//哺乳
            if(leftVacation.getLeftBuRu() < duringTime){
                System.out.println("剩余哺乳时间不足");
                return false;
            }
        }else if(type == 6){//陪产
            if(leftVacation.getLeftPeiChan() < duringTime){
                System.out.println("剩余陪产时间不足");
                return false;
            }
        }else if(type == 0 || type == 7){//事假、外出
            System.out.println("符合");
        }else{
            System.out.println("请假类型不存在");
            return false;
        }
        //将员工请假信息添加到请假表中
        Apply apply = new Apply();
        apply.setStartTime(startTime);
        apply.setDuringTime(duringTime);
        apply.setReason(reason);
        apply.setType(type);
        //默认是正在审核中
        apply.setState(1);
        apply.setEmpId(empId);
        applyMapper.insert(apply);
        /*ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setEmpId(empId);
        applyRecord.setState(1);
        applyRecord.setType(type);
        applyRecord.setTotalTime(duringTime);
        applyRecordMapper.insert(applyRecord);*/
        return true;
    }

    /**
     * 负责人原审批员工请假，根据申请ID号修改申请表中的状态
     * @param applyId
     * @param state
     * @return
     */
    @Override
    public boolean updateApplyState(long applyId, Integer state) {

        //上锁，如果该员工有其他的未审批的审批表，则不予审批
        Apply curApplier = applyMapper.selectById(applyId);
        long curApplierId = curApplier.getEmpId();

        Integer curApplierDuringTime = curApplier.getDuringTime();

        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id" , curApplierId);
        List<Apply> applyList = applyMapper.selectList(queryWrapper);
        int cnt = 0;
        for(Apply apply : applyList){
            if(apply.getState() == 1){
                cnt++;
            }
        }
        if(cnt > 1){
            return false;
        }

        //判断操作的类型，如果是2，即通过，则
        //  修改审批表状态 - 修改员工剩余时间表 - 添加请假记录
        //否则更改状态为0，即拒绝 - 修改审批表状态即可

        //首先修改审批表状态，无论操作时什么都要更新
        UpdateWrapper<Apply> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("apply_id" , applyId);
        updateWrapper.set("state" , state);
        applyMapper.update(null , updateWrapper);

        //如果操作状态是2
        if(state == 2){

            //查询当前用户的剩余假期时间
            QueryWrapper<LeftVacation> leftVacationQueryWrapper = new QueryWrapper<>();
            leftVacationQueryWrapper.eq("emp_id" , curApplierId);
            List<LeftVacation> leftVacationList = leftVacationMapper.selectList(leftVacationQueryWrapper);
            LeftVacation curLeftVacation = leftVacationList.get(0);

            //根据当前员工申请的类型 以及 申请总时间，更新假期表中的相应假期时间字段
            UpdateWrapper<LeftVacation> leftVacationUpdateWrapper = new UpdateWrapper<>();
            leftVacationUpdateWrapper.eq("emp_id" , curApplierId);

            if(curApplier.getType() == 1){
                leftVacationUpdateWrapper.set("left_year" , curLeftVacation.getLeftYear() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 2){
                leftVacationUpdateWrapper.set("left_hun_jia" , curLeftVacation.getLeftHunJia() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 3){
                leftVacationUpdateWrapper.set("left_chan_jian" , curLeftVacation.getLeftChanJian() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 4){
                leftVacationUpdateWrapper.set("left_chan_jia" , curLeftVacation.getLeftChanJia() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 5){
                leftVacationUpdateWrapper.set("left_bu_ru" , curLeftVacation.getLeftBuRu() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 6){
                leftVacationUpdateWrapper.set("left_pei_chan" , curLeftVacation.getLeftPeiChan() - curApplierDuringTime);
                leftVacationMapper.update(null , leftVacationUpdateWrapper);
            }else if(curApplier.getType() == 0 || curApplier.getType() == 7){//事假和外出不需要更新剩余时间
                System.out.println("合法");
            }else {
                System.out.println("type不合法");
                return false;
            }

            //添加员工请假记录
            ApplyRecord applyRecord = new ApplyRecord();
            applyRecord.setEmpId(curApplierId);
            applyRecord.setType(curApplier.getType());
            applyRecord.setTotalTime(curApplierDuringTime);
            applyRecord.setState(state);
            applyRecordMapper.insert(applyRecord);
        }
        return true;
    }

    /**
     * 查询本人请假记录
     * @param empId
     * @return
     */
    @Override
    public List<QueryApplyRecordResp> queryApplyByEmpId(long empId) {
        QueryWrapper<ApplyRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id" , empId);
        List<ApplyRecord> recordList = applyRecordMapper.selectList(queryWrapper);
        List<QueryApplyRecordResp> respList = new ArrayList<>();
        for(ApplyRecord applyRecord : recordList){
            QueryApplyRecordResp resp = new QueryApplyRecordResp();
            resp.setApplyRecordId(applyRecord.getApplyRecordId());
            resp.setDuringTime(applyRecord.getTotalTime());
            resp.setType(applyRecord.getType());
            resp.setState(applyRecord.getState());
            long curEmpId = applyRecord.getEmpId();
            Employer employer = employerMapper.selectById(curEmpId);
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 用于展示给审批人员，审批人员只需要审批state为1的审批记录
     * @return
     */
    @Override
    public List<QueryApplyInfoResp> queryApplyByStateForEventManager() {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , 1).lt("during_time" , 3);
        List<Apply> applyList = applyMapper.selectList(queryWrapper);
        List<QueryApplyInfoResp> respList = new ArrayList<>();
        for(Apply apply : applyList){
            QueryApplyInfoResp resp = new QueryApplyInfoResp();
            resp.setApplyId(apply.getApplyId());
            resp.setStartTime(apply.getStartTime());
            resp.setDuringTime(apply.getDuringTime());
            resp.setReason(apply.getReason());
            resp.setType(apply.getType());
            resp.setState(apply.getState());
            Employer curEmp = employerMapper.selectById(apply.getEmpId());
            resp.setName(curEmp.getName());
            resp.setAccount(curEmp.getAccount());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 用于展示给总经理经理，只需要审批state为1的审批记录并且申请天数大于3天
     * @return
     */
    @Override
    public List<QueryApplyInfoResp> queryApplyByStateForBigManager() {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , 1).ge("during_time" , 3);
        List<Apply> applyList = applyMapper.selectList(queryWrapper);
        List<QueryApplyInfoResp> respList = new ArrayList<>();
        for(Apply apply : applyList){
            QueryApplyInfoResp resp = new QueryApplyInfoResp();
            resp.setApplyId(apply.getApplyId());
            resp.setStartTime(apply.getStartTime());
            resp.setDuringTime(apply.getDuringTime());
            resp.setReason(apply.getReason());
            resp.setType(apply.getType());
            resp.setState(apply.getState());
            Employer curEmp = employerMapper.selectById(apply.getEmpId());
            resp.setName(curEmp.getName());
            resp.setAccount(curEmp.getAccount());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 根据用户id查询正在申请的记录
     * @param empId
     * @return
     */
    @Override
    public List<QueryApplyInfoResp> queryApplyingById(long empId) {
        List<QueryApplyInfoResp> respList = new ArrayList<>();
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id" , empId).eq("state" , 1);
        List<Apply> applyList = applyMapper.selectList(queryWrapper);
        if(applyList.size() == 0){
            return null;
        }
        Apply apply = applyList.get(0);
        QueryApplyInfoResp resp = new QueryApplyInfoResp();
        resp.setApplyId(apply.getApplyId());
        resp.setStartTime(apply.getStartTime());
        resp.setDuringTime(apply.getDuringTime());
        resp.setReason(apply.getReason());
        resp.setType(apply.getType());
        resp.setState(apply.getState());
        Employer curEmp = employerMapper.selectById(empId);
        resp.setName(curEmp.getName());
        resp.setAccount(curEmp.getAccount());
        respList.add(resp);
        return respList;

    }
}
