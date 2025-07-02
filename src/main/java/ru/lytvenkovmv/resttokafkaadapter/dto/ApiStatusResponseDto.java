package ru.lytvenkovmv.resttokafkaadapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ApiStatusResponseDto(
        @Schema(description = "Дата и время последней записи в БД")
        LocalDateTime lastUpdate
) {
}