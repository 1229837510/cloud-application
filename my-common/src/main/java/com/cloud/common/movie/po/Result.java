package com.cloud.common.movie.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.base.web.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaofang
 * @since 2022-09-03
 */
@Getter
@Setter
@TableName("result")
@ApiModel(value = "Result对象", description = "")
public class Result extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("title")
    private String title;

    @TableField("movie_country")
    private String movieCountry;

    @TableField("year")
    private Long year;


}
