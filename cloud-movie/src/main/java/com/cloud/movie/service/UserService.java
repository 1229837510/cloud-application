package com.cloud.movie.service;

import com.cloud.common.movie.po.User;
import com.cloud.common.persist.MyBaseService;
import com.cloud.movie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaofang
 * @since 2022-09-03
 */
@Service
public class UserService extends MyBaseService<UserMapper, User> {

}
