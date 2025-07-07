package ru.lytvenkovmv.resttokafkaadapter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "api_data")
@NoArgsConstructor
@AllArgsConstructor
public class ApiData {
    /**
     * Unique record identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Record creation date and time
     */
    private LocalDateTime createdAt;
    /**
     * Response status
     */
    private Boolean success;
    /**
     * Response data or error message
     */
    private String payload;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiData apiData = (ApiData) o;
        return Objects.equals(id, apiData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ApiData{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", success=" + success +
                ", payload='" + payload + '\'' +
                '}';
    }
}
