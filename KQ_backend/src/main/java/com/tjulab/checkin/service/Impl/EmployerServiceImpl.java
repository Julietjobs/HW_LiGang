package com.tjulab.checkin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjulab.checkin.entity.EmpState;
import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.entity.LeftVacation;
import com.tjulab.checkin.mapper.EmpStateMapper;
import com.tjulab.checkin.mapper.EmployerMapper;
import com.tjulab.checkin.mapper.LeftVacationMapper;
import com.tjulab.checkin.service.EmployerService;
import com.tjulab.checkin.vo.QueryEmpInfoResp;
import com.tjulab.checkin.vo.QueryEmpStateResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private LeftVacationMapper leftVacationMapper;

    @Autowired
    private EmpStateMapper empStateMapper;


    /**
     * 查询全部员工信息 , 即role = 0
     * @return
     */
    @Override
    public List<QueryEmpInfoResp> queryAllEmployeeInfo() {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role" , 0);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        List<QueryEmpInfoResp> respList = new ArrayList<>();
        for(Employer employer : employerList){
            QueryEmpInfoResp resp = new QueryEmpInfoResp();
            resp.setEmpId(employer.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            resp.setGender(employer.getGender());
            resp.setPhone(employer.getPhone());
            resp.setRole(employer.getRole());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 根据id查询员工信息
     * @param empId
     * @return
     */
    @Override
    public Employer queryEmpInfoById(long empId) {
        return employerMapper.selectById(empId);
    }

    /**
     * 查询全部经理信息
     * @return
     */
    @Override
    public List<QueryEmpInfoResp> queryAllEmployerInfo() {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role" , 1, 2 , 3);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        List<QueryEmpInfoResp> respList = new ArrayList<>();
        for(Employer employer : employerList){
            QueryEmpInfoResp resp = new QueryEmpInfoResp();
            resp.setEmpId(employer.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            resp.setGender(employer.getGender());
            resp.setPhone(employer.getPhone());
            resp.setRole(employer.getRole());

            respList.add(resp);
        }
        return respList;
    }

    /**
     * 登录，登录后返回该员工信息
     * @param account
     * @param password
     * @return
     */
    @Override
    public Employer login(String account, String password) {
        QueryWrapper<Employer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account" , account);
        List<Employer> employerList = employerMapper.selectList(queryWrapper);
        if(employerList.size() == 0) {
            System.out.println("没有该用户信息！");
            return null;
        }
        if(employerList.get(0).getPassword().equals(password)){
            return employerList.get(0);
        }else {
            System.out.println("密码不正确！");
            return null;
        }
    }

    /**
     * 新增员工信息
     * @param employer
     * @return
     */
    @Override
    public boolean insertEmpInfo(Employer employer) {
        int insert = employerMapper.insert(employer);
        if(insert == 1){
            //需要在剩余假期表中初始化该员工的剩余假期表
            LeftVacation leftVacation = new LeftVacation();
            leftVacation.setLeftYear(7);
            leftVacation.setLeftHunJia(3);
            leftVacation.setLeftChanJian(3);
            leftVacation.setLeftChanJia(20);
            leftVacation.setLeftBuRu(30);
            leftVacation.setLeftPeiChan(5);
            leftVacation.setEmpId(employer.getEmpId());
            leftVacationMapper.insert(leftVacation);

            //需要在员工状态表汇中初始化该员工的状态
            EmpState empState = new EmpState();
            empState.setEmpId(employer.getEmpId());
            empState.setState(0);
            empStateMapper.insert(empState);

            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询公司全部人员的状态
     * @return
     */
    @Override
    public List<QueryEmpStateResp> queryEmpState() {
        List<EmpState> empStateList = empStateMapper.selectList(null);
        List<QueryEmpStateResp> respList = new ArrayList<>();
        for(EmpState empState : empStateList){
            QueryEmpStateResp resp = new QueryEmpStateResp();
            resp.setStateId(empState.getStateId());
            resp.setState(empState.getState());
            Employer employer = employerMapper.selectById(empState.getEmpId());
            resp.setAccount(employer.getAccount());
            resp.setName(employer.getName());
            respList.add(resp);

        }
        return respList;

    }

    /**
     * 查询当前用户的状态
     * @param empId
     * @return
     */
    @Override
    public Integer queryEmpStateById(long empId) {
        return empStateMapper.selectById(empId).getState();
    }
}
