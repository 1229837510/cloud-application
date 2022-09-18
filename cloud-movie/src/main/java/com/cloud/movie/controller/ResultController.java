package com.cloud.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.common.movie.dto.ResultDto;
import com.cloud.common.persist.util.QueryUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cloud.common.movie.po.Result;
import com.cloud.movie.service.ResultService;
import com.cloud.common.base.config.StringConstant;
import com.cloud.common.base.excetion.CustomException;
import com.cloud.common.base.result.R;
import com.cloud.common.base.web.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fangcy
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/result")
@Slf4j
public class ResultController {

    @Resource
    private ResultService resultService;

    /**
     * 保存
     *
     * @param resultDto resultDto对象
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody ResultDto resultDto) {
        Result result = new Result();
        BeanUtils.copyProperties(resultDto, Result.class);
        return R.status(resultService.save(result));
    }

    /**
     * 详情
     *
     * @param resultDto resultDto对象
     * @return
     */
    @PostMapping("/detail")
    public R detail(@RequestBody ResultDto resultDto) {
        return R.ok().data("ResultDto", resultService.getById(resultDto.getId()));
    }

    /**
     * 删除
     *
     * @param resultDto result对象
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody ResultDto resultDto) {
        boolean success = resultService.removeById(resultDto.getId());
        if (!success) {
            throw new CustomException(StringConstant.DATA_VERSION_ERROR);
        }
        return R.ok();
    }

    /**
     * 批量删除用户
     *
     * @param paramMap map集合
     * @return
     */
    @PostMapping("/deleteBatch")
    public R deleteBatch(@RequestBody Map<String, Object> paramMap) {
        boolean success = resultService.removeByIds((List) paramMap.get("idList"));
        if (!success) {
            throw new CustomException(StringConstant.DATA_VERSION_ERROR);
        }
        return R.ok();
    }

    /**
     * 列表查询
     *
     * @return
     */
    @PostMapping("/list")
    public R dtoList() {
        return R.ok().data("list", resultService.list());
    }

    /**
     * 分页查询
     *
     * @param queryVo 查询条件
     * @return
     */
    @PostMapping("/page")
    public R detail(@RequestBody QueryVo queryVo) {
        IPage<ResultDto> pages = resultService.getBaseMapper().dtoPage(
                QueryUtils.getPage(queryVo.getQuery()),
                QueryUtils.getQueryWrapper(queryVo.getParamMap(), ResultDto.class));
        return R.ok().data("page", pages);
    }

}
