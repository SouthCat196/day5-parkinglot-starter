package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackingLotTest {

    @Test
    void should_return_ticket_when_pack_given_a_car() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        // When
        Ticket ticket = packingLot.pack(car);
        // Then
        assertNotNull(ticket);
    }
    
    @Test
    void should_return_the_car_when_fetch__given_a_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        Ticket ticket = packingLot.pack(car);
        // When
        Car fetchedCar = packingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }
}
