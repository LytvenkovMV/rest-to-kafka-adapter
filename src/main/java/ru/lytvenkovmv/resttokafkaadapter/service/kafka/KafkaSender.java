package ru.lytvenkovmv.resttokafkaadapter.service.kafka;

import org.springframework.kafka.support.SendResult;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiDataDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiErrorDto;

import java.util.concurrent.CompletableFuture;

public interface KafkaSender<K, V> {
    CompletableFuture<SendResult<K, V>> sendApiData(KafkaApiDataDto data);

    CompletableFuture<SendResult<K, V>> sendApiError(KafkaApiErrorDto data);
}
