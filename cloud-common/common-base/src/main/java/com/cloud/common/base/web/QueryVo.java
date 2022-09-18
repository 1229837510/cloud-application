package com.cloud.common.base.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author fangcy
 * @date 2022-09-18
 */
@Getter
@Setter
public class QueryVo extends AbsVo {
    /**
     * 分页查询对象
     */
    @NotNull(message = "分页信息不能为null，请检查query参数", groups = {page.class})
    private Query query;

    @NotNull(message = "参数信息不能为null，请检查paramMap参数", groups = {page.class, list.class})
    private Map<String, Object> paramMap;
}
