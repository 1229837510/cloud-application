package com.cloud.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication (
        scanBasePackages = {"com.cloud", "com.cloud.admin"}
        )
@EnableTransactionManagement(proxyTargetClass = true)
public class CloudAdminApplication {
    protected static final Logger logger = LoggerFactory.getLogger(CloudAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
        logger.info("项目已成功运行！！！！！！！！！");
    }

}
