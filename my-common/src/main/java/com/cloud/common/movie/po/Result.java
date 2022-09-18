package com.cloud.common.movie.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.base.web.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author fangcy
 * @since 2022-09-18
 */
@Getter
@Setter
@TableName("result")
@ApiModel(value = "Result对象", description = "")
public class Result extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.NONE)
    private Long id;
    /**
    * 
    */
    @TableField("title")
    private String title;

    /**
    * 
    */
    @TableField("movie_country")
    private String movieCountry;

    /**
    * 
    */
    @TableField("year")
    private Long year;


}
