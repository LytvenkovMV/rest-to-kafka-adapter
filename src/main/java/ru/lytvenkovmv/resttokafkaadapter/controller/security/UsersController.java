package ru.lytvenkovmv.resttokafkaadapter.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdatePasswordRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.service.security.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/users")
@Tag(name = "Пользователи", description = "Операции для пользователей в системе")
public class UsersController {
    private final UserService userService;

    @Operation(summary = "Обновление пароля",
            description = "Обновление пароля пользователя")
    @ApiResponse(responseCode = "200",
            description = "Пароль успешно обновлен")
    @PutMapping()
    public void updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        userService.updatePassword(updatePasswordRequestDto);
    }

    @Operation(summary = "Удаление пользователя",
            description = "Удаление пользователя из системы")
    @ApiResponse(responseCode = "200",
            description = "Пользователь успешно удален из системы")
    @DeleteMapping()
    public void delete() {
        userService.delete();
    }
}
