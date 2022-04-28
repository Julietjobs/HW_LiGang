package com.tjulab.checkin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QueryApplyInfoResp {

    private long applyId;

    private Date startTime;

    private Integer duringTime;

    private String reason;

    private Integer type;

    private Integer state;

    private String name;

    private String account;

}
