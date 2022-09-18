package com.cloud.movie.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.common.movie.dto.UserDto;
import com.cloud.common.movie.po.User;
import com.cloud.common.persist.MyBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaofang
 * @since 2022-09-03
 */
public interface UserMapper extends MyBaseMapper<User> {
    /**
     * 分页查询
     * @param page 分页
     * @param queryWrapper 查询条件
     * @return
     */
    IPage<UserDto> dtoPage(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<UserDto> queryWrapper);
}
