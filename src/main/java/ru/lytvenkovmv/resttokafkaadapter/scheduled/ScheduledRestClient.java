package ru.lytvenkovmv.resttokafkaadapter.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lytvenkovmv.resttokafkaadapter.service.RestToKafkaService;


@Component
@RequiredArgsConstructor
public class ScheduledRestClient {
    private final RestToKafkaService service;

    @Async("scheduledRestClientExecutor")
    @Scheduled(cron = "#{@scheduledRestClientProperties.cron}")
    public void sendRequest() {
        service.communicate();
    }
}
