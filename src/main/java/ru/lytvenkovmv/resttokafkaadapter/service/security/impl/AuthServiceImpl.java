package ru.lytvenkovmv.resttokafkaadapter.service.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.AuthRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.JwtResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.mapper.AuthMapper;
import ru.lytvenkovmv.resttokafkaadapter.service.security.AuthService;
import ru.lytvenkovmv.resttokafkaadapter.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;

    @Override
    public JwtResponseDto authenticateUser(AuthRequestDto authRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequestDto.username(),
                authRequestDto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authenticationToken.getPrincipal().toString());

        return authMapper.jwtResponseDtoFrom(token, "Bearer");
    }
}
