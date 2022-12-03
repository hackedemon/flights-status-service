package com.example.demo.exception.impl;

import com.example.demo.exception.DataAlreadyExists;

public class FlightScheduleAlreadyExistsException extends RuntimeException implements DataAlreadyExists {
    public FlightScheduleAlreadyExistsException(String message) {
        super(message);
    }
}
