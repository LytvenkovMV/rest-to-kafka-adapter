package ru.lytvenkovmv.resttokafkaadapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lytvenkovmv.resttokafkaadapter.dto.ErrorDto;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto exceptionHandler(Exception ex) {
        log.warn("Ошибка во время HTTP запроса", ex);

        return ErrorDto.builder()
                .id(UUID.randomUUID())
                .errorType(ex.getClass().getTypeName())
                .errorMessage(ex.getMessage())
                .build();
    }
}
