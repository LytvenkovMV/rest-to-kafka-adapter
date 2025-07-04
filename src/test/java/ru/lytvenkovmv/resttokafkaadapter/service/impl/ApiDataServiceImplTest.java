package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.lytvenkovmv.resttokafkaadapter.constants.AppConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;
import ru.lytvenkovmv.resttokafkaadapter.mapper.ApiDataMapper;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ApiDataServiceImplTest {

    @Mock
    private ApiDataRepository repository;

    @Mock
    private ApiDataMapper mapper;

    @InjectMocks
    private ApiDataServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ReturnsMappedDtoList_WhenPageIsNotEmpty() {
        int size = 5;
        ApiData apiData = new ApiData();
        ApiDataResponseDto dto = new ApiDataResponseDto(null, null, null, null);

        List<ApiData> dataList = List.of(apiData, apiData);
        Page<ApiData> page = new PageImpl<>(dataList);

        when(repository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, AppConstants.DEFAULT_DATA_SORT_BY))))
                .thenReturn(page);
        when(mapper.apiDataResponseDtoFrom(apiData)).thenReturn(dto);

        List<ApiDataResponseDto> result = service.findAll(Optional.of(size));

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void findAll_ReturnsEmptyList_WhenPageIsEmpty() {
        int size = 10;
        Page<ApiData> emptyPage = new PageImpl<>(List.of());

        when(repository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, AppConstants.DEFAULT_DATA_SORT_BY))))
                .thenReturn(emptyPage);

        List<ApiDataResponseDto> result = service.findAll(Optional.of(size));

        Assertions.assertTrue(result.isEmpty());
        verify(mapper, never()).apiDataResponseDtoFrom(any());
    }

    @Test
    void findAll_UsesDefaultSize_WhenSizeParamIsEmpty() {
        int defaultSize = AppConstants.DEFAULT_DATA_PAGE_SIZE;
        Page<ApiData> emptyPage = new PageImpl<>(List.of());

        when(repository.findAll(PageRequest.of(0, defaultSize, Sort.by(Sort.Direction.DESC, AppConstants.DEFAULT_DATA_SORT_BY))))
                .thenReturn(emptyPage);

        List<ApiDataResponseDto> result = service.findAll(Optional.empty());

        Assertions.assertTrue(result.isEmpty());
        verify(repository).findAll(PageRequest.of(0, defaultSize, Sort.by(Sort.Direction.DESC, AppConstants.DEFAULT_DATA_SORT_BY)));
    }
}