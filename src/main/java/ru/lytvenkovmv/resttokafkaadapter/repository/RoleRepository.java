package ru.lytvenkovmv.resttokafkaadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lytvenkovmv.resttokafkaadapter.entity.security.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(String roleName);
}
