package com.tjulab.checkin.vo;

import lombok.Data;

@Data
public class QueryEmpStateResp {
    private long stateId;

    private String name;

    private String account;

    private Integer state;
}
