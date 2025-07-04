package ru.lytvenkovmv.resttokafkaadapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ErrorDto(
        @Schema(description = "Тип ошибки")
        String errorType,

        @Schema(description = "Описание ошибки")
        String errorMessage
) {
}
