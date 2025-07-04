package ru.lytvenkovmv.resttokafkaadapter.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record JwtResponseDto(
        @Schema(description = "JWT токен")
        String token,

        @Schema(description = "Тип токена")
        String type
) {
}
