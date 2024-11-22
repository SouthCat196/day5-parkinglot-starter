package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PackingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    void should_return_nothing_when_fetch_given_a_wrong_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        packingLot.pack(car);
        // When
        Car fetchCar = packingLot.fetch(new Ticket());
        // Then
        assertNull(fetchCar);
        assertTrue(systemOut().contains("Unrecognized parking ticket."));
    }

    @Test
    void should_return_nothing_when_fetch_given_a_used_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        Ticket ticket = packingLot.pack(car);
        packingLot.fetch(ticket);
        // When
        Car fetchCar = packingLot.fetch(ticket);
        // Then
        assertNull(fetchCar);
        assertTrue(systemOut().contains("Unrecognized parking ticket."));
    }

    @Test
    void should_return_nothing_when_park_given_a_parking_lot_without_any_position() {
        // Given
        PackingLot packingLot = getFullPackingLot();
        // When
        Ticket ticket = packingLot.pack(new Car());
        // Then
        assertNull(ticket);
        assertTrue(systemOut().contains("No available position."));
    }

    private static PackingLot getFullPackingLot() {
        PackingLot packingLot = new PackingLot();
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        packingLot.pack(new Car());
        return packingLot;
    }

    private String systemOut() {
        return outContent.toString();
    }
}
