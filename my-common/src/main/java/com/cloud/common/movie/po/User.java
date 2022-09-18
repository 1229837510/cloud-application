package com.cloud.common.movie.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.base.web.BaseEntity;
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
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * id主键
     */
    @ApiModelProperty("id主键")
    @TableId("id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("userId")
    private Long userId;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    @TableField("mobile")
    private String mobile;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @TableField("pwd")
    private String pwd;
    /**
     * 名字
     */
    @ApiModelProperty("名字")
    @TableField("name")
    private String name;
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    @TableField("type")
    private Integer type;

}
