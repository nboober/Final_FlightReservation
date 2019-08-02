package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {
//Yun Add
    Iterable<Flight> findAllByUser(User user);

    Flight findByDiscount(boolean discount);
}
