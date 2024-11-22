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
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        Ticket ticket = packingLot.pack(car);
        // When
        Car fetchedCar = packingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_tickets() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car firstCar = new Car();
        Car secendCar = new Car();
        Ticket firstCarTicket = packingLot.pack(firstCar);
        Ticket secendCarTicket = packingLot.pack(secendCar);
        // When
        Car fetchFirstCar = packingLot.fetch(firstCarTicket);
        Car fetchSecendCar = packingLot.fetch(secendCarTicket);

        // Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secendCar, fetchSecendCar);
    }
}
