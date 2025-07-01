package ru.lytvenkovmv.resttokafkaadapter.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "rest-to-kafka-adapter.scheduled-rest-client")
public class ScheduledRestClientProperties {
    @NotBlank
    private String cron = "0 0/10 * * * *";
    @NotBlank
    private String url;
}
