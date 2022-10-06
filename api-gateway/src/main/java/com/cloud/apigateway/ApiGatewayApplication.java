package com.cloud.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    protected static final Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        logger.info("项目已成功运行！！！！！！！！！");
    }

}
