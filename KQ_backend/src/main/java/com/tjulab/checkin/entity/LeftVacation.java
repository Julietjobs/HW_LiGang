package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("left_vacation")
@Data
public class LeftVacation {

    @TableId(type = IdType.AUTO)
    private long leftId;

    private long empId;

    private Integer leftYear;

    private Integer leftHunJia;

    private Integer leftChanJian;

    private Integer leftChanJia;

    private Integer leftBuRu;

    private Integer leftPeiChan;

}
