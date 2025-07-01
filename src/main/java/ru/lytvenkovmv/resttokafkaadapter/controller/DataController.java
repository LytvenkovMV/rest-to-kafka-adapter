package ru.lytvenkovmv.resttokafkaadapter.controller;

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
public class DataController {
    private final ApiDataService service;

    @GetMapping
    public List<ApiDataResponseDto> findAll(@RequestParam(required = false)
                                            @Min(value = 1, message = "Page size must be 1 or greater")
                                            Integer size) {
        return service.findAll(Optional.ofNullable(size));
    }
}
