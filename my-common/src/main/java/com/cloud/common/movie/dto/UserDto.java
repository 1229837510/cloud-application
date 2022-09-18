package com.cloud.common.movie.dto;

import com.cloud.common.base.web.AbsVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
* <p>
*   UserDto
* </p>
*
* @author fangcy
* @since 2022-09-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AbsVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * id主键
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 电话号码
    */
    private String mobile;

    /**
    * 密码
    */
    private String pwd;

    /**
    * 名字
    */
    private String name;

    /**
    * 类型
    */
    private Integer type;


}