package ru.lytvenkovmv.resttokafkaadapter.service.restclient.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.feign.FeignClientOne;
import ru.lytvenkovmv.resttokafkaadapter.service.restclient.RetryingRestClient;

@Service
@RequiredArgsConstructor
public class RetryingRestClientImpl implements RetryingRestClient {
    private final FeignClientOne feignClientOne;

    @Override
    @Retryable(
            retryFor = {FeignException.class},
            maxAttemptsExpression = "${retryProperties.maxAttempts}",
            backoff = @Backoff(
                    delayExpression = "${retryProperties.delay}",
                    multiplierExpression = "${retryProperties.multiplier}",
                    maxDelayExpression = "${retryProperties.maxDelay}"
            ),
            recover = "getDataRecover")
    public RestClientResponseDto getData() {
        ResponseEntity<String> response = feignClientOne.getData();

        return RestClientResponseDto.builder()
                .responseStatus(response.getStatusCode().value())
                .responseJson(response.getBody())
                .build();
    }

    @Recover
    public RestClientResponseDto getDataFallback(FeignException ex) {
        return RestClientResponseDto.builder()
                .responseStatus(ex.status())
                .errorMessage(ex.getMessage())
                .build();
    }
}
