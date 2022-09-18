package com.cloud.common.movie.dto;

import com.cloud.common.base.web.AbsVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* <p>
*   ResultDto
* </p>
*
* @author fangcy
* @since 2022-09-18
*/
@Data
public class ResultDto extends AbsVo {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 
    */
    private String title;

    /**
    * 
    */
    private String movieCountry;

    /**
    * 
    */
    private Long year;


}