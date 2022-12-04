package com.example.demo.service.impl;

import com.example.demo.entity.Airport;
import com.example.demo.exception.impl.AirportNotFoundException;
import com.example.demo.repository.AirportRepository;
import com.example.demo.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class DefaultAirportServiceTest {
    private final AirportService airportService;

    @MockBean
    private AirportRepository repository;

    private Airport airport;

    @Autowired
    public DefaultAirportServiceTest(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * Method to test getAirportByCode success scenario.
     */
    @Test
    @DisplayName("getAirportByCode success case")
    void testGetAirportByCode() {
        when(repository.findById("CGN")).thenReturn(Optional.ofNullable(airport));

        assertEquals(airport, airportService.getAirportByCode("CGN"));
    }

    /**
     * Method to test getAirportByCode throwable.
     */
    @Test
    @DisplayName("getAirportByCode error case")
    void testGetAirportByCodeException() {
        when(repository.findById("AMS")).thenThrow(new AirportNotFoundException("No airport found for code AMS"));

        assertThrows(AirportNotFoundException.class, () -> airportService.getAirportByCode("AMS"));
    }

    @BeforeEach
    private void setup() {
        airport = new Airport();
        airport.setCode("CGN");
        airport.setName("Cologne");
    }
}
