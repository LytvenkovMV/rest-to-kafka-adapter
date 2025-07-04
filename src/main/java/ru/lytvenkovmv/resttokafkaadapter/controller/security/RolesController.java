package ru.lytvenkovmv.resttokafkaadapter.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdateRolesRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.service.security.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/roles")
@Tag(name = "Роли", description = "Обновление списка ролей пользователей в системе")
public class RolesController {
    private final UserService service;

    @Operation(summary = "Роли",
            description = "Обновление списка ролей пользователя в системе")
    @ApiResponse(responseCode = "200",
            description = "Список ролей пользователя успешно обновлен")
    @PutMapping()
    public void updateRoles(@RequestBody @Valid UpdateRolesRequestDto dto) {
        service.updateRoles(dto);
    }
}
