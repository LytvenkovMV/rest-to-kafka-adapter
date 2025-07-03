package ru.lytvenkovmv.resttokafkaadapter.service.security;

import ru.lytvenkovmv.resttokafkaadapter.dto.security.AuthRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.JwtResponseDto;

public interface AuthService {
    JwtResponseDto authenticateUser(AuthRequestDto authRequestDto);
}
