package ru.lytvenkovmv.resttokafkaadapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.JwtResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {
    JwtResponseDto jwtResponseDtoFrom(String token, String type);
}
