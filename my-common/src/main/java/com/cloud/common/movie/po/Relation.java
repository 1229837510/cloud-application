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
@TableName("relation")
@ApiModel(value = "Relation对象", description = "")
public class Relation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("au_id")
    private String auId;

    @TableField("re_download")
    private Integer reDownload;

    @TableField("mo_id")
    private Integer moId;

    @TableField("re_score")
    private Integer reScore;

    @TableField("re_watch")
    private Integer reWatch;


}
