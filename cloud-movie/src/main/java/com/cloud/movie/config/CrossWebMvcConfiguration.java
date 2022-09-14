package com.cloud.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaofang
 * @date 2022/9/14
 */
@Configuration
public class CrossWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 配置对所有请求路径生效
                .addMapping("/**")
                // 放行所有请求域（默认配置，不配置就是放行所有）
                .allowedOrigins("*")
                // 浏览器是否应该发送凭据
                .allowCredentials(true)
                // 放行所有请求方法（默认只放行GET/HEAD/POST）
                .allowedMethods("*");
    }
}
