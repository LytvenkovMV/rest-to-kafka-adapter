package ru.lytvenkovmv.resttokafkaadapter.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ErrorDto(
        UUID id,
        String errorType,
        String errorMessage
) {
}
