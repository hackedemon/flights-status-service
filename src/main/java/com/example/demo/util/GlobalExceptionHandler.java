package com.example.demo.util;

import com.example.demo.dto.response.AppError;
import com.example.demo.exception.impl.FlightNotFoundException;
import com.example.demo.exception.impl.FlightScheduleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody AppError handleFlightNotFoundException(
            FlightNotFoundException flightNotFoundException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(flightNotFoundException.getMessage())
                .path(request.getContextPath())
                .build();
    }

    @ExceptionHandler(value = FlightScheduleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody AppError handleFlightScheduleNotFoundException(
            FlightScheduleNotFoundException flightScheduleNotFoundException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(flightScheduleNotFoundException.getMessage())
                .path(request.getContextPath())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleMethodArgumentNotValidException(
            MethodArgumentTypeMismatchException methodArgumentNotValidException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(methodArgumentNotValidException.getMessage())
                .path(request.getContextPath())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody AppError handleAllUncaughtException(Exception exception, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .path(request.getContextPath())
                .build();
    }
}