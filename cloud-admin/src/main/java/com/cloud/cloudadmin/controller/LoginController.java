package com.cloud.cloudadmin.controller;

import com.cloud.common.model.admin.po.User;
import com.cloud.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @PostMapping("login")
    public R login(@RequestBody User user){

        return R.ok();
    }
}
