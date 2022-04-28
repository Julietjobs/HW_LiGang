package com.tjulab.checkin;

import com.tjulab.checkin.entity.Employer;
import com.tjulab.checkin.service.EmployerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class Test0410 {
    @Autowired
    private EmployerService employerService;

    @Test
    public void test01(){
        employerService.queryAllEmployeeInfo().stream().forEach(System.out::println);
    }

    @Test
    public void test02(){
        System.out.println(employerService.queryEmpInfoById(1));
    }

    @Test
    public void test03(){
        employerService.queryAllEmployerInfo().stream().forEach(System.out::println);
    }

    @Test
    public void test04(){
        System.out.println(employerService.login("20212290331", "12356"));
    }

    @Test
    public void test05(){
        Employer employer = new Employer();
        employer.setAccount("2021229072");
        employer.setName("mxc");
        employer.setGender(1);
        employer.setPhone("15829892981");
        employer.setPassword("123456");
        employer.setEmail("mxc312@qq.com");
        employer.setCreateTime(new Date());
        employer.setRole(4);
        employerService.insertEmpInfo(employer);
    }
}
