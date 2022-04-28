package com.tjulab.checkin.service;

import com.tjulab.checkin.entity.LeftVacation;
import com.tjulab.checkin.vo.QueryCheckRecordResp;
import io.swagger.models.auth.In;

import java.util.List;

public interface CheckInOutService {

    /**
     * 上下班打卡（签到），根据用户ID修改用户的状态，并添加打卡记录
     * @param empId
     * @param signType
     * @return
     */
    boolean signInOrOut(Long empId , int signType);

    /**
     * 查询全部打卡记录
     * @return
     */
    List<QueryCheckRecordResp> queryAllSignRecord();

    /**
     * 模糊查询：根据account或者type查询打卡记录
     * @param account
     * @param type
     * @return
     */
    List<QueryCheckRecordResp> querySignRecordByAccountOrType(String account , Integer type);

    /**
     * 根据员工ID查询本人剩余假期时间
     * @param empId
     * @return
     */
    LeftVacation queryLeftTimeByEmpId(long empId);

}
