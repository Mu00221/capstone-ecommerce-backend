package com.shop.shopping;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.shop.repository")
@EntityScan(basePackages = "com.shop.model")

public class ApplicationConfig {
    

}
