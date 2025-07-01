package ru.lytvenkovmv.resttokafkaadapter.dto.restclient;

import lombok.Builder;

@Builder
public record RestClientResponseDto(
        Integer responseStatus,
        String responseJson,
        String errorMessage
) {
}
