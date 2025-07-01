package ru.lytvenkovmv.resttokafkaadapter.properties;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "rest-to-kafka-adapter.retry")
public class RetryProperties {
    @Min(value = 1, message = "Max attempts must be 1 or greater")
    private Integer maxAttempts = 3;

    @Min(value = 200, message = "Delay must be 200ms or greater")
    private Long delay = 1000L;

    @Min(value = 1, message = "Multiplier must be 1 or greater")
    private Double multiplier = 2.0;

    @Min(value = 1000, message = "Max delay must be 1000ms or greater")
    private Long maxDelay = 10000L;
}
