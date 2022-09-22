package com.cloud.movie.dto;

import com.cloud.common.base.web.AbsVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginDto extends AbsVo {

    private String accessToken;
    private String openId;
}
