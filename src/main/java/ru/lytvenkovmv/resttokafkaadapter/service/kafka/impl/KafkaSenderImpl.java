package ru.lytvenkovmv.resttokafkaadapter.service.kafka.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiDataDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiErrorDto;
import ru.lytvenkovmv.resttokafkaadapter.properties.KafkaProperties;
import ru.lytvenkovmv.resttokafkaadapter.service.kafka.KafkaSender;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSenderImpl implements KafkaSender<String, String> {
    private final KafkaProperties properties;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public CompletableFuture<SendResult<String, String>> sendApiData(KafkaApiDataDto dto) {
        String json = jsonFromObject(dto);

        return send(properties.getDataTopic(), json);
    }

    @Override
    public CompletableFuture<SendResult<String, String>> sendApiError(KafkaApiErrorDto dto) {
        String json = jsonFromObject(dto);

        return send(properties.getErrorTopic(), json);
    }

    private CompletableFuture<SendResult<String, String>> send(String topic, String message) {

        return kafkaTemplate.send(topic, message)
                .orTimeout(properties.getTimeout(), TimeUnit.MILLISECONDS)
                .whenComplete((sendResult, ex) -> {
                    if (ex != null) {
                        log.error("Произошла ошибка во время отправки сообщения в kafka: {}", ex.getMessage(), ex);
                    } else {
                        log.info("Сообщение отправлено в kafka: {}", sendResult);
                    }
                });
    }

    private String jsonFromObject(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
