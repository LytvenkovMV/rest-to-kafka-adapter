package ru.lytvenkovmv.resttokafkaadapter.dto.kafka;

import lombok.Builder;

import java.util.UUID;

@Builder
public record KafkaApiErrorDto(
    UUID id,
    String errorMessage
) {
}
