package ru.lytvenkovmv.resttokafkaadapter.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

public record UpdatePasswordRequestDto(
        @Schema(description = "Старый пароль",
                example = "sD78r2s!",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Старый пароль не может быть пустым")
        String oldPassword,
        @Schema(description = "Новый пароль",
                example = "72hg4Wji",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Новый пароль не может быть пустым")
        String newPassword
) {
}
