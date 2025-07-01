package ru.lytvenkovmv.resttokafkaadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface ApiDataRepository extends JpaRepository<ApiData, UUID> {
    @Query(value = """
            SELECT created_at FROM api_data
            ORDER BY created_at DESC
            LIMIT 1
            """, nativeQuery = true
    )
    Optional<LocalDateTime> findLastUpdateTime();
}
