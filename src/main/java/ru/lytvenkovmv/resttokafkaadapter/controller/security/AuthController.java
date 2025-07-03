package ru.lytvenkovmv.resttokafkaadapter.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.AuthRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.JwtResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.RegisterRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.service.security.AuthService;
import ru.lytvenkovmv.resttokafkaadapter.service.security.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/auth")
@Tag(name = "Регистрация и Аутентификация", description = "Регистрация и аутентификация пользователей в системе")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Operation(summary = "Регистрация",
            description = "Регистрация нового пользователя в системе")
    @ApiResponse(responseCode = "200",
            description = "Пользователь успешно зарегистрирован в системе")
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequest) {
        userService.create(registerRequest);
    }

    @Operation(summary = "Аутентификация",
            description = "Аутентификация зарегистрированного пользователя в системе")
    @ApiResponse(responseCode = "200",
            description = "Пользователь успешно аутентифицирован в системе")
    @PostMapping("/login")
    public JwtResponseDto createToken(@RequestBody AuthRequestDto authRequest) {
        return authService.authenticateUser(authRequest);
    }
}
