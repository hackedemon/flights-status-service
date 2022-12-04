package com.example.demo.util;

import com.example.demo.dto.response.AppError;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.exception.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Global exception handler class to return proper error response if exceptions reach controller layer.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody AppError handleAccessDeniedException(
            AccessDeniedException accessDeniedException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(accessDeniedException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody AppError handleFlightNotFoundException(
            FlightNotFoundException flightNotFoundException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(flightNotFoundException.getMessage())
                .path(request.getDescription(false))
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
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = AirportAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleAirportAlreadyExistsException(
            AirportAlreadyExistsException airportAlreadyExistsException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(airportAlreadyExistsException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = FlightAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleFlightAlreadyExistsException(
            FlightAlreadyExistsException flightAlreadyExistsException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(flightAlreadyExistsException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = FlightScheduleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleFlightScheduleAlreadyExistsException(
            FlightScheduleAlreadyExistsException flightScheduleAlreadyExistsException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(flightScheduleAlreadyExistsException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = AirportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody AppError handleAirportNotFoundException(
            AirportNotFoundException airportNotFoundException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(airportNotFoundException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleInvalidRequestException(
            InvalidRequestException invalidRequestException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(invalidRequestException.getMessage())
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleMethodArgumentNotValidException(
            MethodArgumentTypeMismatchException methodArgumentNotValidException, WebRequest request) {
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(methodArgumentNotValidException.getName() + " value " + methodArgumentNotValidException.getValue() + " is invalid.")
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleBindException(
            ConstraintViolationException constraintViolationException, WebRequest request) {
        Set<String> errorFields = new HashSet<>();
        Set<String> errorMessages = new HashSet<>();
        for (var e : constraintViolationException.getConstraintViolations()) {
            errorFields.add(e.getPropertyPath().toString());
            errorMessages.add(e.getMessage());
        }
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid value in field(s) " + errorFields + " with errors " + errorMessages)
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody AppError handleBindException(
            BindException bindException, WebRequest request) {
        Set<String> errorFields = new HashSet<>();
        Set<String> errorMessages = new HashSet<>();
        for (var e : bindException.getFieldErrors()) {
            errorFields.add(e.getField());
            errorMessages.add(e.getDefaultMessage());
        }
        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid value in field(s) " + errorFields + " with errors " + errorMessages)
                .path(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody AppError handleAllUncaughtException(Throwable throwable, WebRequest request) {
        log.error(Arrays.stream(throwable.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n\tat ")));

        return AppError.builder()
                .timeStamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Some error occurred while handling the request.")
                .path(request.getDescription(false))
                .build();
    }
}
