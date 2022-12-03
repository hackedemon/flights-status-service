package com.example.demo.service.impl;

import com.example.demo.entity.Airport;
import com.example.demo.exception.impl.AirportAlreadyExistsException;
import com.example.demo.exception.impl.AirportNotFoundException;
import com.example.demo.repository.AirportRepository;
import com.example.demo.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultAirportService implements AirportService {
    private AirportRepository repository;

    @Override
    public Airport getAirportByCode(String arrivalAirportCode) throws AirportNotFoundException {
        return repository.findById(arrivalAirportCode)
                .orElseThrow(() -> new AirportNotFoundException("No airport found for code " + arrivalAirportCode));
    }

    @Override
    public void addAirport(Airport airport) throws AirportAlreadyExistsException{
        if (repository.findById(airport.getCode()).isPresent())
            throw new AirportAlreadyExistsException(
                    "Can't add airport with code "
                            + airport.getCode() + " as it already exists. Use update method instead.");

        repository.save(airport);
    }
}
