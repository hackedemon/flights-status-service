package com.example.demo.service;

import com.example.demo.entity.Airport;
import com.example.demo.exception.impl.AirportAlreadyExistsException;
import com.example.demo.exception.impl.AirportNotFoundException;

public interface AirportService {
    Airport getAirportByCode(String arrivalAirportCode) throws AirportNotFoundException;

    void addAirport(Airport airport) throws AirportAlreadyExistsException;
}
