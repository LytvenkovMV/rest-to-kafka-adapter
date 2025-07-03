package ru.lytvenkovmv.resttokafkaadapter.service.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lytvenkovmv.resttokafkaadapter.constants.AppConstants;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.RegisterRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdatePasswordRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.security.UpdateRolesRequestDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.Role;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.User;
import ru.lytvenkovmv.resttokafkaadapter.mapper.UserMapper;
import ru.lytvenkovmv.resttokafkaadapter.repository.RoleRepository;
import ru.lytvenkovmv.resttokafkaadapter.repository.UserRepository;
import ru.lytvenkovmv.resttokafkaadapter.service.security.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public void create(RegisterRequestDto registerRequestDto) {
        Role defaulRole = roleRepository.findByRoleName(AppConstants.DEFAULT_ROLE)
                .orElseThrow(() -> new RuntimeException("В системе нет роли по имени " + AppConstants.DEFAULT_ROLE));

        String encodedPassword = passwordEncoder.encode(registerRequestDto.password());
        User user = userMapper.userFrom(registerRequestDto, encodedPassword, Set.of(defaulRole));
        userRepository.save(user);
    }

    @Override
    public void delete() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        userRepository.deleteByUsername(username);
    }

    @Override
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                updatePasswordRequestDto.oldPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Неверный пароль");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Не удалось найти пользователя по имени " + username));

        String encodedPassword = passwordEncoder.encode(updatePasswordRequestDto.newPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateRoles(UpdateRolesRequestDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new RuntimeException("Не удалось найти пользователя по имени " + dto.username()));

        Set<Role> roleSet = new HashSet<>();
        for (String roleName : dto.roles()) {
            Role role = roleRepository.findByRoleName(roleName.toUpperCase())
                    .orElseThrow(() -> new RuntimeException("В системе не существует роль по имени " + roleName));
            roleSet.add(role);
        }
        user.getRoles().clear();
        user.getRoles().addAll(roleSet);
        userRepository.save(user);
    }
}
