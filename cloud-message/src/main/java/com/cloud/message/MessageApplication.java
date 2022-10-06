package com.cloud.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
public class MessageApplication {

    protected static final Logger logger = LoggerFactory.getLogger(MessageApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
        logger.info("项目已成功运行！！！！！！！！！");
    }

}
