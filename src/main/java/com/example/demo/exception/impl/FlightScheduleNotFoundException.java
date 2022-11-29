package com.example.demo.exception.impl;

import com.example.demo.exception.DataNotFoundException;

public class FlightScheduleNotFoundException extends RuntimeException implements DataNotFoundException {
    public FlightScheduleNotFoundException(String message) {
        super(message);
    }
}
