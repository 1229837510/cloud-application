package com.cloud.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(
        scanBasePackages = {"com.cloud", "com.cloud.movie"}
)
//@MapperScan(basePackages = {"com.cloud.movie.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
public class MovieApplication {
    protected static final Logger logger = LoggerFactory.getLogger(MovieApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
        logger.info("项目已成功运行！！！！！！！！！");
    }
}
