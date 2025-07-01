package ru.lytvenkovmv.resttokafkaadapter.service.restclient;

import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;

public interface RetryingRestClient {
    RestClientResponseDto getData();
}
