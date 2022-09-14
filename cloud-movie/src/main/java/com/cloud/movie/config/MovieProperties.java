package com.cloud.movie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaofang
 * @date 2022/9/15
 */


@Data
@Component
@ConfigurationProperties(prefix = MovieProperties.PREFIX)
public class MovieProperties {
    public static final String PREFIX = "movie.service";
    private String name;
    private Integer age;
}
