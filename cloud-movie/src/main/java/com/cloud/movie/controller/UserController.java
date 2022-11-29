package com.cloud.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.common.base.config.StringConstant;
import com.cloud.common.base.excetion.CustomException;
import com.cloud.common.base.result.R;
import com.cloud.common.base.util.BeanCopy;
import com.cloud.common.base.web.QueryVo;
import com.cloud.common.movie.dto.UserDto;
import com.cloud.common.movie.po.User;
import com.cloud.common.persist.util.QueryUtils;
import com.cloud.movie.config.MovieProperties;
import com.cloud.movie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * 保存
     *
     * @param userDto userDto对象
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody UserDto userDto) {
        log.info("save User");
        User user = BeanCopy.copyBean(userDto, User.class);
        return R.status(userService.save(user));
    }

    /**
     * 删除用户
     *
     * @param userDto userDto对象
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody UserDto userDto) {
        boolean success = userService.removeById(userDto.getId());
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
        boolean success = userService.removeByIds(new ArrayList<>(paramMap.values()));
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
        List<UserDto> dtoList = BeanCopy.copyListProperties(userService.list(), UserDto::new);
        return R.ok().data("list", dtoList);
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
    public R detail(@RequestBody UserDto userDto) {
        return R.ok().data("userDto", userService.getById(userDto.getId()));
    }

    /**
     * 分页查询
     *
     * @param queryVo 查询条件
     * @return
     */
    @PostMapping("/page")
    public R detail(@RequestBody QueryVo queryVo) {
        IPage<UserDto> pages = userService.getBaseMapper().dtoPage(
                QueryUtils.getPage(queryVo.getQuery()),
                QueryUtils.getQueryWrapper(queryVo.getParamMap(), UserDto.class));
        return R.ok().data("page", pages);
    }
}
