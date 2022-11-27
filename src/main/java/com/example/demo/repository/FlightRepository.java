package com.example.demo.repository;

import com.example.demo.entity.Flight;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends PagingAndSortingRepository<Flight, String> {



}
