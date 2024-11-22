package com.parkinglot;

import org.junit.jupiter.api.Test;

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
}
