package com.cloud.movie.controller;

import com.cloud.common.base.config.StringConstant;
import com.cloud.common.base.excetion.CustomException;
import com.cloud.common.base.result.R;
import com.cloud.common.movie.po.User;
import com.cloud.movie.config.MovieProperties;
import com.cloud.movie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiaofang
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/movie/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MovieProperties movieProperties;

    /**
     * 删除用户
     *
     * @param user user
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody User user) {
        boolean success = userService.removeById(user.getId());
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
        boolean success = userService.removeByIds((List) paramMap.get("idList"));
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
        return R.ok().data("list", userService.list());
    }


    @GetMapping("/name")
    public R name() {
        return R.ok().data("name", movieProperties.getName()).data("age", movieProperties.getAge());
    }

    /**
     * 详情
     *
     * @return
     */
    @PostMapping("/detail")
    public R detail(@RequestBody User user) {
        return R.ok().data("userDto", userService.getById(user.getId()));
    }
}
