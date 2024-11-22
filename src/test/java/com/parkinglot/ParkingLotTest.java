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

class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_tickets() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstCarTicket = parkingLot.park(firstCar);
        Ticket secendCarTicket = parkingLot.park(secondCar);
        // When
        Car fetchFirstCar = parkingLot.fetch(firstCarTicket);
        Car fetchSecendCar = parkingLot.fetch(secendCarTicket);
        // Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecendCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_park_given_a_parking_lot_without_any_position() {
        // Given
        ParkingLot parkingLot = getFullPackingLot();
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(new Car()));
        // Then
        assertEquals(exception.getMessage(), NO_AVAILABLE_POSITION);
    }

    private static ParkingLot getFullPackingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        return parkingLot;
    }
}
