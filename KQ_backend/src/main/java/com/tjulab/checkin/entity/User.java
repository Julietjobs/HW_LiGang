package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId
    private Long userId;

    private String account;

    private String name;

    private Integer gender;

    private String phone;

    private String password;

    private String email;

    private Date createTime;

    private Integer role;
}
