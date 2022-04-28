package com.tjulab.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Employer {
    @TableId(type = IdType.AUTO)
    private Long empId;

    //员工工号
    private String account;

    private String name;

    //0：女 1：男
    private Integer gender;

    private String phone;

    private String password;

    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    /*
    * 0:员工
    * 1：部门经理
    * 2：副总经理
    * 3：总经理
    * 4：人事人员
    * 5：财务人员
    * */
    private Integer role;
}
