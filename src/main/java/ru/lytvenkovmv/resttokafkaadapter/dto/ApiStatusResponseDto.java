package ru.lytvenkovmv.resttokafkaadapter.dto;

import java.time.LocalDateTime;

public record ApiStatusResponseDto(
        LocalDateTime lastUpdate
) {
}