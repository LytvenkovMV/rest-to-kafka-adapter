package ru.lytvenkovmv.resttokafkaadapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    public ExecutorService scheduledRestClientExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
