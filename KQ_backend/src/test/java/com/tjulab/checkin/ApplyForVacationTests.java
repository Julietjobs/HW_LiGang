package com.tjulab.checkin;

import com.tjulab.checkin.mapper.ApplyMapper;
import com.tjulab.checkin.mapper.ApplyRecordMapper;
import com.tjulab.checkin.mapper.LeftVacationMapper;
import com.tjulab.checkin.service.ApplyForVacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ApplyForVacationTests {

    @Autowired
    private LeftVacationMapper leftVacationMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @Autowired
    private ApplyRecordMapper applyRecordMapper;

    @Test
    public void test01(){
        leftVacationMapper.selectList(null).stream().forEach(System.out::println);
    }

    @Test
    public void test02(){
        applyMapper.selectList(null).stream().forEach(System.out::println);
    }

    @Test
    public void test03(){
        applyForVacationService.applyForVacation(1,new Date() ,2 , "我要去结婚！",2);
    }

    @Test
    public void test04(){
        System.out.println(new Date());
    }

    @Test
    public void test05(){
        applyRecordMapper.selectList(null).stream().forEach(System.out::println);
    }

    @Test
    public void test06(){
        applyForVacationService.updateApplyState(6 , 2);
    }
}
