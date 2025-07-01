package ru.lytvenkovmv.resttokafkaadapter.properties;

import jakarta.validation.constraints.Min;
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
@ConfigurationProperties(prefix = "rest-to-kafka-adapter.kafka")
public class KafkaProperties {
    @NotBlank(message = "Topic name can not be blank")
    private String dataTopic;

    @NotBlank(message = "Topic name can not be blank")
    private String errorTopic;

    @Min(value = 500, message = "Timeout must be 500ms or greater")
    private Long timeout = 15000L;
}
