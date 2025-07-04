package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiDataDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiErrorDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;
import ru.lytvenkovmv.resttokafkaadapter.mapper.ApiDataMapper;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;
import ru.lytvenkovmv.resttokafkaadapter.service.kafka.KafkaSender;
import ru.lytvenkovmv.resttokafkaadapter.service.restclient.RetryingRestClient;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class RestToKafkaServiceImplTest {

    @Autowired
    private RestToKafkaServiceImpl service;

    @MockBean
    private RetryingRestClient restClient;

    @MockBean
    private ApiDataRepository repository;

    @MockBean
    private ApiDataMapper mapper;

    @MockBean
    private KafkaSender<String, String> kafkaSender;

    @Test
    void communicate_successfulFlow_sendsApiDataToKafka() {
        RestClientResponseDto responseDto = Mockito.mock(RestClientResponseDto.class);
        ApiData apiData = Mockito.mock(ApiData.class);

        when(restClient.getData()).thenReturn(responseDto);
        when(mapper.apiDataFrom(responseDto)).thenReturn(apiData);
        when(repository.save(apiData)).thenReturn(apiData);
        when(apiData.getSuccess()).thenReturn(true);
        when(responseDto.responseJson()).thenReturn("{\"test\":true}");
        when(kafkaSender.sendApiData(any(KafkaApiDataDto.class))).thenReturn(CompletableFuture.completedFuture(null));

        service.communicate();

        verify(restClient, times(1)).getData();
        verify(repository, times(1)).save(apiData);
        verify(kafkaSender, times(1)).sendApiData(any(KafkaApiDataDto.class));
        verify(kafkaSender, never()).sendApiError(any());
    }

    @Test
    void communicate_failureFlow_sendsApiErrorToKafka() {
        RestClientResponseDto responseDto = Mockito.mock(RestClientResponseDto.class);
        ApiData apiData = Mockito.mock(ApiData.class);

        when(restClient.getData()).thenReturn(responseDto);
        when(mapper.apiDataFrom(responseDto)).thenReturn(apiData);
        when(repository.save(apiData)).thenReturn(apiData);
        when(apiData.getSuccess()).thenReturn(false);
        when(responseDto.errorMessage()).thenReturn("Ошибка удаленного сервиса");
        when(kafkaSender.sendApiError(any(KafkaApiErrorDto.class))).thenReturn(CompletableFuture.completedFuture(null));

        service.communicate();

        verify(restClient, times(1)).getData();
        verify(repository, times(1)).save(apiData);
        verify(kafkaSender, times(1)).sendApiError(any(KafkaApiErrorDto.class));
        verify(kafkaSender, never()).sendApiData(any());
    }

    @Test
    void communicate_whenExceptionThrown_logsError() {
        when(restClient.getData()).thenThrow(new RuntimeException("Удаленный сервис не ответил"));

        service.communicate();

        verify(repository, never()).save(any());
        verify(kafkaSender, never()).sendApiData(any());
        verify(kafkaSender, never()).sendApiError(any());
    }
}