package ru.lytvenkovmv.resttokafkaadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
