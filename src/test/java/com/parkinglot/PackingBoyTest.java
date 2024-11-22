package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import static com.parkinglot.constant.Constant.NO_AVAILABLE_POSITION;
import static com.parkinglot.constant.Constant.WRONG_TICKET_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackingBoyTest {

    @Test
    void should_return_ticket_when_pack_given_a_car() {
        // Given
        PackingLot packingLot = new PackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_tickets() {
        // Given
        PackingLot packingLot = new PackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        Car firstCar = new Car();
        Car secendCar = new Car();
        Ticket firstCarTicket = parkingBoy.park(firstCar);
        Ticket secendCarTicket = parkingBoy.park(secendCar);
        // When
        Car fetchFirstCar = parkingBoy.fetch(firstCarTicket);
        Car fetchSecendCar = parkingBoy.fetch(secendCarTicket);
        // Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secendCar, fetchSecendCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_wrong_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        Car car = new Car();
        parkingBoy.park(car);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_used_ticket() {
        // Given
        PackingLot packingLot = new PackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        // When
        UnrecognizedParkingTicketException exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(new Ticket()));
        // Then
        assertEquals(exception.getMessage(), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_park_given_a_parking_lot_without_any_position() {
        // Given
        PackingLot packingLot = getFullPackingLot();
        ParkingBoy parkingBoy = new ParkingBoy(packingLot);
        // When
        NoAvailablePositionException exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(new Car()));
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
