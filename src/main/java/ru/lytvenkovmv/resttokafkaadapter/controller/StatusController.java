package ru.lytvenkovmv.resttokafkaadapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiStatusResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.service.ApiStatusService;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-to-kafka-adapter/api/v1/status")
public class StatusController {
    private final ApiStatusService service;

    @GetMapping
    public ApiStatusResponseDto find() {
        return service.find();
    }
}
