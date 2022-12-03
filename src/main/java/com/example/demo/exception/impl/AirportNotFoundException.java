package com.example.demo.exception.impl;

import com.example.demo.exception.DataNotFoundException;

public class AirportNotFoundException extends RuntimeException implements DataNotFoundException {
    public AirportNotFoundException(String message) {
        super(message);
    }
}
