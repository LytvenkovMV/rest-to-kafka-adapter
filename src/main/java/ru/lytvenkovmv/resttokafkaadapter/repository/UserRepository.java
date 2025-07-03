package ru.lytvenkovmv.resttokafkaadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
