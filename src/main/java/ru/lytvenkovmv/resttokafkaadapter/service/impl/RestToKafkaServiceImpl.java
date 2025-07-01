package ru.lytvenkovmv.resttokafkaadapter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiDataDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.kafka.KafkaApiErrorDto;
import ru.lytvenkovmv.resttokafkaadapter.dto.restclient.RestClientResponseDto;
import ru.lytvenkovmv.resttokafkaadapter.entity.ApiData;
import ru.lytvenkovmv.resttokafkaadapter.mapper.ApiDataMapper;
import ru.lytvenkovmv.resttokafkaadapter.repository.ApiDataRepository;
import ru.lytvenkovmv.resttokafkaadapter.service.RestToKafkaService;
import ru.lytvenkovmv.resttokafkaadapter.service.kafka.KafkaSender;
import ru.lytvenkovmv.resttokafkaadapter.service.restclient.RetryingRestClient;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestToKafkaServiceImpl implements RestToKafkaService {
    private final RetryingRestClient restClient;
    private final ApiDataRepository repository;
    private final ApiDataMapper mapper;
    private final KafkaSender<String, String> kafkaSender;

    @Override
    public void communicate() {
        try {
            log.info("Начался периодический опрос внешнего сервиса");

            RestClientResponseDto responseDto = restClient.getData();

            log.info("Получен ответ от внешнего сервиса: {}", responseDto.toString());

            ApiData apiData = mapper.apiDataFrom(responseDto);
            repository.save(apiData);

            log.info("Ответ сохранен в БД: {}", apiData);

            if (apiData.getSuccess()) {
                KafkaApiDataDto kafkaApiDataDto = KafkaApiDataDto.builder()
                        .id(UUID.randomUUID())
                        .data(responseDto.responseJson())
                        .build();
                kafkaSender.sendApiData(kafkaApiDataDto).join();
            } else {
                KafkaApiErrorDto kafkaApiErrorDto = KafkaApiErrorDto.builder()
                        .id(UUID.randomUUID())
                        .errorMessage(responseDto.errorMessage())
                        .build();
                kafkaSender.sendApiError(kafkaApiErrorDto).join();
            }

            log.info("Закончен периодический опрос внешнего сервиса");
        } catch (Exception ex) {
            log.error("Непредвиденная ошибка во время перодического опроса внешнего сервиса", ex);
        }
    }
}
