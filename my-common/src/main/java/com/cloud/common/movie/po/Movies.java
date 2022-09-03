package com.cloud.common.movie.po;

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
@TableName("movies")
@ApiModel(value = "Movies对象", description = "")
public class Movies extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("title")
    private String title;

    @TableField("star")
    private String star;

    @TableField("movie_time")
    private String movieTime;

    @TableField("movie_country")
    private String movieCountry;

    @TableField("movie_type")
    private String movieType;

    @TableField("rating_num")
    private String ratingNum;

    @TableField("img_url")
    private String imgUrl;

    @TableField("people")
    private String people;

    @TableField("summary")
    private String summary;

    @TableField("is_deleted")
    private Integer isDeleted;


}
