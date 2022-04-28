package com.tjulab.checkin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QueryApplyRecordResp {

    private long applyRecordId;

    private Integer duringTime;

    private Integer type;

    private Integer state;

    private String account;

    private String name;

}
