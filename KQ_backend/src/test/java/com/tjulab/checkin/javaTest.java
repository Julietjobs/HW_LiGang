package com.tjulab.checkin;

import com.tjulab.checkin.service.ApplyForVacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class javaTest {

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @Test
    public void test01(){
        System.out.println(applyForVacationService.queryApplyingById(1));
    }
}
