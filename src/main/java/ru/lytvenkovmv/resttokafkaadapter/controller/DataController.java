package ru.lytvenkovmv.resttokafkaadapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.service.ApiDataService;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/data")
@Tag(name = "Данные", description = "Данные от удаленного API")
public class DataController {
    private final ApiDataService service;

    @Operation(summary = "Поиск",
            description = "Поиск данных, полученных от удаленного API")
    @ApiResponse(responseCode = "200",
            description = "Данные, полученные от удаленного API, успешно отправлены")
    @GetMapping
    public List<ApiDataResponseDto> findAll(@RequestParam(required = false)
                                            @Schema(description = "Количество записей в ответе")
                                            @Min(value = 1, message = "Page size must be 1 or greater")
                                            Integer size) {
        return service.findAll(Optional.ofNullable(size));
    }
}
