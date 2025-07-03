package ru.lytvenkovmv.resttokafkaadapter.service.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.User;
import ru.lytvenkovmv.resttokafkaadapter.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Не удалось найти пользователя по имени " + username));

        return new UserDetailsImpl(user);
    }
}
