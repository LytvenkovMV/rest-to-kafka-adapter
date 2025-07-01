package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.constants.AppConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;
import ru.lytvenkovmv.resttokafkaadapter.mapper.ApiDataMapper;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;
import ru.lytvenkovmv.resttokafkaadapter.service.ApiDataService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiDataServiceImpl implements ApiDataService {
    private final ApiDataRepository repository;
    private final ApiDataMapper mapper;

    @Override
    public List<ApiDataResponseDto> findAll(Optional<Integer> sizeParam) {
        Integer size = sizeParam.orElse(AppConstants.DEFAULT_DATA_PAGE_SIZE);
        Page<ApiData> pageData = repository.findAll(
                PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, AppConstants.DEFAULT_DATA_SORT_BY)));

        if (pageData.isEmpty()) {
            return List.of();
        }

        return pageData.getContent().stream()
                .map(mapper::apiDataResponseDtoFrom)
                .toList();
    }
}
