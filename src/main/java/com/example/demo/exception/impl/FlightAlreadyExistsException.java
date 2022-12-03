package com.example.demo.exception.impl;

import com.example.demo.exception.DataAlreadyExists;

public class FlightAlreadyExistsException extends RuntimeException implements DataAlreadyExists {
    public FlightAlreadyExistsException(String message) {
        super(message);
    }
}
