package ru.lytvenkovmv.resttokafkaadapter.dto.kafka;

import lombok.Builder;

import java.util.UUID;

@Builder
public record KafkaApiDataDto(
    UUID id,
    String data
) {
}
