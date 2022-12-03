package com.example.demo.dto.request;

import com.example.demo.entity.enumeration.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class FlightScheduleRequest {
    private String flightNumber;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Status status;
}
