package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.constant.Constant.NO_AVAILABLE_POSITION;
import static com.parkinglot.constant.Constant.WRONG_TICKET_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class PackingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_return_ticket_when_park_given_a_car() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        // When
        Ticket ticket = packingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        Ticket ticket = packingLot.park(car);
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
        Ticket firstCarTicket = packingLot.park(firstCar);
        Ticket secendCarTicket = packingLot.park(secendCar);
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
        packingLot.park(car);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> packingLot.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_used_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        Car car = new Car();
        Ticket ticket = packingLot.park(car);
        packingLot.fetch(ticket);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> packingLot.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_park_given_a_parking_lot_without_any_position() {
        // Given
        PackingLot packingLot = getFullPackingLot();
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> packingLot.park(new Car()));
        // Then
        assertEquals(exception.getMessage(), NO_AVAILABLE_POSITION);
    }

    private static PackingLot getFullPackingLot() {
        PackingLot packingLot = new PackingLot();
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        packingLot.park(new Car());
        return packingLot;
    }
}
