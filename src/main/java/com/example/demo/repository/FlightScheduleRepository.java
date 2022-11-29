package com.example.demo.repository;

import com.example.demo.entity.Flight;
import com.example.demo.entity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
    FlightSchedule getFlightScheduleByFlightAndDepartureDate(Flight flight, LocalDate departureDate);
}
