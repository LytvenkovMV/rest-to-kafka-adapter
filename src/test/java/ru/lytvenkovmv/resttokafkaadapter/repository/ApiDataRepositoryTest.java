package ru.lytvenkovmv.resttokafkaadapter.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApiDataRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private ApiDataRepository apiDataRepository;

    @Test
    @Sql(statements = {
            "INSERT INTO api_data (id, created_at) VALUES ('550e8400-e29b-41d4-a716-446655440001', '2023-01-01 12:00:00')",
            "INSERT INTO api_data (id, created_at) VALUES ('550e8400-e29b-41d4-a716-446655440002', '2023-01-02 12:00:00')",
            "INSERT INTO api_data (id, created_at) VALUES ('550e8400-e29b-41d4-a716-446655440003', '2023-01-03 12:00:00')"
    })
    void findLastUpdateTime_shouldReturnLatestDate_whenDataExists() {
        Optional<LocalDateTime> result = apiDataRepository.findLastUpdateTime();

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get(), LocalDateTime.of(2023, 1, 3, 12, 0, 0));
    }
}