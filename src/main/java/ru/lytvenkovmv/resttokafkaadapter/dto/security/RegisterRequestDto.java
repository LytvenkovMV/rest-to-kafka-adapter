package ru.lytvenkovmv.resttokafkaadapter.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record RegisterRequestDto(
        @Schema(description = "Имя пользователя",
                example = "user1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Имя пользователя не может быть пустым")
        @Size(min = 8, max = 16, message = "Длина имени пользователя должна быть от 4 до 20 символов")
        String username,
        @Schema(description = "Пароль",
                example = "sD78r2s!",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 8, max = 16, message = "Длина пароля должна быть от 8 до 16 символов")
        String password,
        @Schema(description = "Адрес электронной почты",
                example = "user1@mail.ru",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Адрес электронной почты не может быть пустым")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]@[a-zA-Z0-9.-]\\.[a-zA-Z]{2,}$",
                message = "Неверный формат адреса электронной почты")
        String email
) {
}
