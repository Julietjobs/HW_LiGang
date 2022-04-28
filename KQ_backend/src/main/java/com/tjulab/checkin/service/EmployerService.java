package com.tjulab.checkin.service;

import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.vo.QueryEmpInfoResp;
import com.tjulab.checkin.vo.QueryEmpStateResp;

import java.util.List;

public interface EmployerService {

    /**
     * 查询全部员工信息
     * @return
     */
    List<QueryEmpInfoResp> queryAllEmployeeInfo();

    /**
     * 根据id查询员工信息
     * @param empId
     * @return
     */
    Employer queryEmpInfoById(long empId);

    /**
     * 查询全部经理信息
     * @return
     */
    List<QueryEmpInfoResp> queryAllEmployerInfo();

    /**
     * 登录，登录后返回该员工信息
     * @param account
     * @param password
     * @return
     */
    Employer login(String account , String password);

    /**
     * 新增员工信息，
     * @param employer
     * @return
     */
    boolean insertEmpInfo(Employer employer);

    /**
     * 查询公司全部人员的状态
     * @return
     */
    List<QueryEmpStateResp> queryEmpState();

    /**
     * 查询当前用户的状态
     * @param empId
     * @return
     */
    Integer queryEmpStateById(long empId);



}
