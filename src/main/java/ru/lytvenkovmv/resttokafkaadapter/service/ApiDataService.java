package ru.lytvenkovmv.resttokafkaadapter.service;

import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;

import java.util.List;
import java.util.Optional;

public interface ApiDataService {
    List<ApiDataResponseDto> findAll(Optional<Integer> sizeParam);
}
