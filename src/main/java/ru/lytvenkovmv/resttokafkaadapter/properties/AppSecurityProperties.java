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
@ConfigurationProperties(prefix = "rest-to-kafka-adapter.security")
public class AppSecurityProperties {
    @Min(value = 1000L, message = "JWT lifetime must be 1000ms or greater")
    private Long jwtLifetime = 3600_000L;
}
