package ru.lytvenkovmv.resttokafkaadapter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "#{@scheduledRestClientProperties.url}", name = "feign-client-one")
public interface FeignClientOne {
    @GetMapping
    ResponseEntity<String> getData();
}
