package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiStatusResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ApiStatusServiceImplTest {

    @Mock
    private ApiDataRepository repository;

    @InjectMocks
    private ApiStatusServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find_ReturnsLastUpdateTime_WhenRepositoryReturnsValue() {
        LocalDateTime now = LocalDateTime.now();
        when(repository.findLastUpdateTime()).thenReturn(Optional.of(now));

        ApiStatusResponseDto result = service.find();

        Assertions.assertEquals(now, result.lastUpdate());
        verify(repository).findLastUpdateTime();
    }

    @Test
    void find_ReturnsMinDateTime_WhenRepositoryReturnsEmpty() {
        when(repository.findLastUpdateTime()).thenReturn(Optional.empty());

        ApiStatusResponseDto result = service.find();

        Assertions.assertEquals(LocalDateTime.MIN, result.lastUpdate());
        verify(repository).findLastUpdateTime();
    }
}