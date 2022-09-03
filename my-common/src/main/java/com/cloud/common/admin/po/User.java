package com.cloud.common.admin.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

import com.cloud.common.base.web.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-08-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity implements Serializable {

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



