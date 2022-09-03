package com.cloud.common.movie.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("movie_tags")
@ApiModel(value = "MovieTags对象", description = "")
public class MovieTags extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "movie_tag_id", type = IdType.ASSIGN_ID)
    private Long movieTagId;

    @TableField("name")
    private String name;

    @TableField("value")
    private String value;


}
