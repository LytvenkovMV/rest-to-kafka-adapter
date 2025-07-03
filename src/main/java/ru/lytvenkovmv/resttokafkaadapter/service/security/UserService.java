package ru.lytvenkovmv.resttokafkaadapter.service.security;

import ru.lytvenkovmv.resttokafkaadapter.dto.security.RegisterRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdatePasswordRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdateRolesRequestDto;

public interface UserService {
    void create(RegisterRequestDto registerRequestDto);

    void delete();

    void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);

    void updateRoles(UpdateRolesRequestDto updateRolesRequestDto);
}
