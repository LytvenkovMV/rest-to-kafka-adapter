package ru.lytvenkovmv.resttokafkaadapter.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

public record UpdateRolesRequestDto(
        @Schema(description = "Имя пользователя",
                example = "user1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Имя пользователя не может быть пустым")
        String username,
        @Schema(description = "Список ролей пользователя",
                example = "[USER, ADMIN]",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "Список ролей не может быть пустым")
        Set<String> roles
) {
}
