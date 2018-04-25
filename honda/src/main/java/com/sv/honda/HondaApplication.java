package com.sv.honda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.sv.honda.repository"})
@EntityScan(basePackages = {"com.sv.honda.entity"})
@EnableTransactionManagement
public class HondaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HondaApplication.class, args);
    }
}
