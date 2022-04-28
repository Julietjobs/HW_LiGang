package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ApplyRecord {

    @TableId(type = IdType.AUTO)
    private long applyRecordId;

    private long empId;

    private Integer type;

    private Integer totalTime;

    private Integer state;
}
