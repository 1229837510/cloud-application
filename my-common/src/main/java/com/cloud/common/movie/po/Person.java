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
@TableName("person")
@ApiModel(value = "Person对象", description = "")
public class Person extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "person_id", type = IdType.AUTO)
    private Long personId;

    @TableField("avatar")
    private String avatar;

    @TableField("biography")
    private String biography;

    @TableField("birth")
    private String birth;

    @TableField("birth_place")
    private String birthPlace;

    @TableField("constellation")
    private String constellation;

    @TableField("name")
    private String name;

    @TableField("name_en")
    private String nameEn;

    @TableField("name_zn")
    private String nameZn;

    @TableField("profession")
    private String profession;

    @TableField("sex")
    private String sex;


}
