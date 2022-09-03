package com.cloud.common.movie.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.base.web.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@TableName("sys_user")
@ApiModel(value = "User对象", description = "User对象")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("userId")
    private Long userId;

    @TableField("mobile")
    private String mobile;

    @TableField("pwd")
    private String pwd;

    @TableField("name")
    private String name;

    @TableField("type")
    private Integer type;


}
