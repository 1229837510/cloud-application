package com.cloud.common.model.admin.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "sys_user")
public class UserDto {
    private final static Long SerializableId = 1L;
    private String userName;
    private String password;
    private String phone;
}
