package com.tjulab.checkin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjulab.checkin.entity.User;
import com.tjulab.checkin.mapper.UserMapper;
import com.tjulab.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAll() {
        Map<String , Object> map = new HashMap<>();
        map.put("name" , "赵奕涵");
        return userMapper.selectByMap(map);
    }


}
