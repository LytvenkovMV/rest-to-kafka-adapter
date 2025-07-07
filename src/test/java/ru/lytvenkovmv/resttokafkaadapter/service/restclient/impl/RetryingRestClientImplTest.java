package ru.lytvenkovmv.resttokafkaadapter.service.restclient.impl;

import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.feign.FeignClientOne;
import ru.lytvenkovmv.resttokafkaadapter.service.restclient.RetryingRestClient;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = {
        "rest-to-kafka-adapter.retry.max-attempts=2",
        "rest-to-kafka-adapter.retry.delay=200",
        "rest-to-kafka-adapter.retry.multiplier=1",
        "rest-to-kafka-adapter.retry.max-delay=1000"
})
class RetryingRestClientImplTest {

    @Autowired
    private RetryingRestClient client;

    @MockBean
    private FeignClientOne feignClientOne;

    @Test
    void getData_WhenFeignException_RetriesAndCallsFallback() {
        FeignException ex = mock(FeignException.class);
        when(ex.status()).thenReturn(500);
        when(ex.getMessage()).thenReturn("Удаленный сервис не ответил");
        when(feignClientOne.getData()).thenThrow(ex);

        RestClientResponseDto dto = client.getData();

        Assertions.assertEquals(500, dto.responseStatus());
        Assertions.assertNull(dto.responseJson());
        Assertions.assertEquals("Удаленный сервис не ответил", dto.errorMessage());
        verify(feignClientOne, times(2)).getData();
    }
}