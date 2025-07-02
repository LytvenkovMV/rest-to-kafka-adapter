package ru.lytvenkovmv.resttokafkaadapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiDataResponseDto(
        @Schema(description = "Уникальный идентификатор записи")
        UUID id,

        @Schema(description = "Дата и время создания записи")
        LocalDateTime createdAt,

        @Schema(description = "Статус ответа от удаленного API")
        Boolean success,

        @Schema(description = "Ответ от удаленного API или сообщение об ошибке")
        String payload
) {
}
