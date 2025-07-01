package ru.lytvenkovmv.resttokafkaadapter.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiDataResponseDto(
        UUID id,
        LocalDateTime createdAt,
        Boolean success,
        String payload
) {
}
