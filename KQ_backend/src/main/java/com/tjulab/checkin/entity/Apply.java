package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Apply {

    @TableId(type = IdType.AUTO)
    private long applyId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private Integer duringTime;

    private String reason;

    /*
    * 0：事假
    * 1：年假
    * 2：婚假
    * 3：产检
    * 4：产假
    * 5：哺乳
    * 6：陪产
    * 7：外出
    * */
    private Integer type;

    /*
    * 0：拒绝
    * 1：审核中
    * 2：通过
    * */
    private Integer state;

    private long empId;
}
