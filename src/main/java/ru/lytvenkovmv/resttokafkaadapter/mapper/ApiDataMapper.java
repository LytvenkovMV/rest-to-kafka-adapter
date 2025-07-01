package ru.lytvenkovmv.resttokafkaadapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiDataMapper {
    ApiDataResponseDto apiDataResponseDtoFrom(ApiData apiData);


    default ApiData apiDataFrom(RestClientResponseDto dto) {
        if (dto == null) {
            return null;
        }

        ApiData apiData = new ApiData();
        apiData.setCreatedAt(LocalDateTime.now());

        if (dto.responseStatus().equals(200)) {
            apiData.setSuccess(true);
            apiData.setPayload(dto.responseJson());
        } else {
            apiData.setSuccess(false);
            apiData.setPayload(dto.errorMessage());
        }

        return apiData;
    }
}
