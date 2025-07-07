package ru.lytvenkovmv.resttokafkaadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableRetry
@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class RestToKafkaAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestToKafkaAdapterApplication.class, args);
    }

}

