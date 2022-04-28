package com.tjulab.checkin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QueryCheckRecordResp {
    private String name;
    private int role;
    private String account;
    private int type;
    private Date time;
}
