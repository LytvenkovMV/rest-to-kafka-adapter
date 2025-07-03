package ru.lytvenkovmv.resttokafkaadapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.RegisterRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.Role;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.User;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "encodedPassword")
    User userFrom(RegisterRequestDto dto, String encodedPassword, Set<Role> roles);
}
