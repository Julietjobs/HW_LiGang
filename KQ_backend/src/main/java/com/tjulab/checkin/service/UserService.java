package com.tjulab.checkin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tjulab.checkin.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getAll();
}
