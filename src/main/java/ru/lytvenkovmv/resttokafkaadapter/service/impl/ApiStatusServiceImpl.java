package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiStatusResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;
import ru.lytvenkovmv.resttokafkaadapter.service.ApiStatusService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApiStatusServiceImpl implements ApiStatusService {
    private final ApiDataRepository repository;

    @Override
    public ApiStatusResponseDto find() {
        LocalDateTime lastUpdate = repository.findLastUpdateTime()
                .orElse(LocalDateTime.MIN);

        return new ApiStatusResponseDto(lastUpdate);
    }
}
