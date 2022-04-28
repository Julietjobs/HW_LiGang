package com.tjulab.checkin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tjulab.checkin.entity.CheckRecord;
import com.tjulab.checkin.entity.EmpState;
import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.entity.LeftVacation;
import com.tjulab.checkin.mapper.CheckRecordMapper;
import com.tjulab.checkin.mapper.EmpStateMapper;
import com.tjulab.checkin.mapper.EmployerMapper;
import com.tjulab.checkin.mapper.LeftVacationMapper;
import com.tjulab.checkin.service.CheckInOutService;
import com.tjulab.checkin.vo.QueryCheckRecordResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckInOutServiceImpl implements CheckInOutService {

    @Autowired
    private EmpStateMapper empStateMapper;

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Autowired
    private LeftVacationMapper leftVacationMapper;

    /**
     * 上下班打卡（签到），根据用户ID修改用户的状态，并添加打卡记录
     *      signType为0或3，证明已经回公司，状态为1
     *      signType为1或2，证明已经出公司，状态为0
     * @param empId
     * @param signType
     * @return
     */
    @Override
    public boolean signInOrOut(Long empId, int signType) {
        Map<String , Object> map = new HashMap<>();
        map.put("emp_id" , empId);
        List<EmpState> empStates = empStateMapper.selectByMap(map);
        EmpState empState = empStates.get(0);

        if(signType == 0 || signType == 3){ //上班打卡和返回打卡
            empState.setState(1); //上班
            empStateMapper.updateById(empState);
            CheckRecord checkRecord = new CheckRecord();
            checkRecord.setEmpId(empId);
            checkRecord.setTime(new Date());
            checkRecord.setType(signType);
            checkRecordMapper.insert(checkRecord);
            return true;
        }else if(signType == 1){ //下班打卡
            empState.setState(0); //下班
            empStateMapper.updateById(empState);
            CheckRecord checkRecord = new CheckRecord();
            checkRecord.setEmpId(empId);
            checkRecord.setTime(new Date());
            checkRecord.setType(signType);
            checkRecordMapper.insert(checkRecord);
            return true;

        }else if(signType == 2){ //外出打卡
            empState.setState(2); //外出
            empStateMapper.updateById(empState);
            CheckRecord checkRecord = new CheckRecord();
            checkRecord.setEmpId(empId);
            checkRecord.setTime(new Date());
            checkRecord.setType(signType);
            checkRecordMapper.insert(checkRecord);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询全部打卡记录
     * @return
     */
    @Override
    public List<QueryCheckRecordResp> queryAllSignRecord() {
        List<CheckRecord> recordList = checkRecordMapper.selectList(null);
        List<QueryCheckRecordResp> respList = new ArrayList<>();
        for(CheckRecord checkRecord : recordList){
            QueryCheckRecordResp queryCheckRecordResp = new QueryCheckRecordResp();
            Employer employer = employerMapper.selectById(checkRecord.getEmpId());

            queryCheckRecordResp.setName(employer.getName());
            queryCheckRecordResp.setAccount(employer.getAccount());
            queryCheckRecordResp.setRole(employer.getRole());
            queryCheckRecordResp.setTime(checkRecord.getTime());
            queryCheckRecordResp.setType(checkRecord.getType());

            respList.add(queryCheckRecordResp);
        }
        return respList;
    }

    /**
     * 模糊查询：根据account或者type查询打卡记录
     * 可选参数：null/account/type
     * @param account
     * @param type
     * @return
     */
    @Override
    public List<QueryCheckRecordResp> querySignRecordByAccountOrType(String account, Integer type) {
        //condition组装条件
        QueryWrapper<CheckRecord> queryWrapperForRecord = new QueryWrapper<>();
        queryWrapperForRecord.eq(type != null , "type" , type);
        List<CheckRecord> checkRecordList = checkRecordMapper.selectList(queryWrapperForRecord);

        List<QueryCheckRecordResp> respList = new ArrayList<>();

        for(CheckRecord checkRecord : checkRecordList){
            QueryWrapper<Employer> queryWrapperForEmp = new QueryWrapper<>();
            queryWrapperForEmp.eq(StringUtils.isNotBlank(account), "account" , account)
                            .eq("emp_id" , checkRecord.getEmpId());
           List<Employer> employerList = employerMapper.selectList(queryWrapperForEmp);

           if(employerList.size() != 0){
               Employer employer = employerList.get(0);
               QueryCheckRecordResp queryCheckRecordResp = new QueryCheckRecordResp();
               queryCheckRecordResp.setType(checkRecord.getType());
               queryCheckRecordResp.setTime(checkRecord.getTime());
               queryCheckRecordResp.setAccount(employer.getAccount());
               queryCheckRecordResp.setName(employer.getName());
               queryCheckRecordResp.setRole(employer.getRole());
               respList.add(queryCheckRecordResp);
           }
        }
        return respList;
    }

    /**
     * 根据员工ID查询本人剩余假期时间
     * @param empId
     * @return
     */
    @Override
    public LeftVacation queryLeftTimeByEmpId(long empId) {
        QueryWrapper<LeftVacation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id" , empId);
        List<LeftVacation> leftVacationList = leftVacationMapper.selectList(queryWrapper);
        if(leftVacationList.size() == 0){
            return null;
        }
        return leftVacationList.get(0);
    }
}
