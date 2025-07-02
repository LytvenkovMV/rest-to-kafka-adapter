package ru.lytvenkovmv.resttokafkaadapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiStatusResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.service.ApiStatusService;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/status")
@Tag(name = "Статус", description = "Статус приложения")
public class StatusController {
    private final ApiStatusService service;
    @Operation(summary = "Статус",
            description = "Получение статуса приложения")
    @ApiResponse(responseCode = "200",
            description = "Статус приложения успешно отправлен")
    @GetMapping
    public ApiStatusResponseDto find() {
        return service.find();
    }
}
