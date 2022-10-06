package com.cloud.common.persist;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author xiaofang
 * @date 2022/9/3
 */
public abstract class MyBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {
    public MyBaseService(){}
}
