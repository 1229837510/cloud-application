package com.cloud.admin.service;

import com.cloud.admin.mapper.UserMapper;
import com.cloud.common.persist.MyBaseService;
import com.cloud.common.admin.po.User;
import org.springframework.stereotype.Service;

/**
 * @author xiaofang
 * @date 2022/9/3
 */
@Service
public class UserService extends MyBaseService<UserMapper, User> {
}

