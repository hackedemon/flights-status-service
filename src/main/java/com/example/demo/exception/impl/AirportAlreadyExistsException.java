package com.example.demo.exception.impl;

public class AirportAlreadyExistsException extends RuntimeException {
    public AirportAlreadyExistsException(String message) {
        super(message);
    }
}
