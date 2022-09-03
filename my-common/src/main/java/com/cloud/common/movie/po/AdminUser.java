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
@TableName("admin_user")
@ApiModel(value = "AdminUser对象", description = "")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("au_id")
    private String auId;

    @TableField("au_username")
    private String auUsername;

    @TableField("au_password")
    private String auPassword;

    @TableField("au_role")
    private String auRole;


}
