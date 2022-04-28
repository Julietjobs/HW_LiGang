package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("check_record")
public class CheckRecord {

    @TableId(type = IdType.AUTO)
    private long signId;

    private Date time;

    /*
    * 0：上班打卡
    * 1：下班打卡
    * 2：外出打卡
    * 3：返回打卡
    * */
    private Integer type;

    private long empId;


}
