package com.example.demo.exception.impl;

import com.example.demo.exception.DataNotFoundException;

public class FlightNotFoundException extends RuntimeException implements DataNotFoundException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
