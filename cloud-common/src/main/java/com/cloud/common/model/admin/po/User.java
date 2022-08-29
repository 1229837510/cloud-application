package com.cloud.common.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-08-29
 */
@Data
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.NONE)
    private Long id;

    @TableField("userName")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;




}



