package com.cloud.movie.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.cloud.common.movie.dto.ResultDto;
import com.cloud.common.movie.po.Result;
import com.cloud.common.persist.MyBaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangcy
 * @since 2022-09-18
 */
@Mapper
public interface ResultMapper extends MyBaseMapper<Result> {

  /**
  * 分页查询
  * @param page 分页
  * @param queryWrapper 查询条件
  * @return
  */
  IPage<ResultDto> dtoPage(IPage<ResultDto> page, @Param(Constants.WRAPPER) Wrapper<ResultDto> queryWrapper);
}
