package ru.lytvenkovmv.resttokafkaadapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.ApiDataResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.JwtResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {
    JwtResponseDto jwtResponseDtoFrom(String token, String type);
}
