package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import com.parkinglot.parkingBoy.ParkingBoy;
import com.parkinglot.parkingBoy.StandardParkingStrategy;
import org.junit.jupiter.api.Test;

import static com.parkinglot.constant.Constant.NO_AVAILABLE_POSITION;
import static com.parkinglot.constant.Constant.WRONG_TICKET_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardParkingBoyTest {

    private ParkingLot getFullPackingLot() {
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

    @Test
    void should_return_ticket_when_pack_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        // When
        Car fetchedCar = standardParkingBoy.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_tickets() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        Car firstCar = new Car();
        Car secendCar = new Car();
        Ticket firstCarTicket = standardParkingBoy.park(firstCar);
        Ticket secendCarTicket = standardParkingBoy.park(secendCar);
        // When
        Car fetchFirstCar = standardParkingBoy.fetch(firstCarTicket);
        Car fetchSecendCar = standardParkingBoy.fetch(secendCarTicket);
        // Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secendCar, fetchSecendCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        standardParkingBoy.park(car);
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(new Ticket()), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        System.out.println(parkingLot);
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(new Ticket()), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_park_given_a_parking_lot_without_any_position() {
        // Given
        ParkingLot parkingLot = getFullPackingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        standardParkingBoy.addParkingLot(parkingLot);
        // When
        // Then
        assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.park(new Car()), NO_AVAILABLE_POSITION);
    }

    @Test
    void should_car_parked_first_parking_lot_when_park_given_tow_not_full_parking_lot() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        boolean inFirstPackingLot = firstParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inFirstPackingLot);
    }

    @Test
    void should_car_parked_second_parking_lot_when_park_given_one_full_parking_log_and_one_not_full_parking_lot() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = getFullPackingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        boolean inSecondPackingLot = secondParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inSecondPackingLot);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_car_in_two_packing_lot() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = firstParkingLot.park(firstCar);
        Ticket secondTicket = secondParkingLot.park(secondCar);
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        // When
        Car fetchFirstCar = standardParkingBoy.fetch(firstTicket);
        Car fetchSecondCar = standardParkingBoy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_two_packing_lot_and_wrong_ticket() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        standardParkingBoy.park(car);
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(new Ticket()), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_two_packing_lot_and_used_ticket() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);
        // When
        // Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(ticket), WRONG_TICKET_MESSAGE);
    }

    @Test
    void should_return_nothing_when_fetch_given_two_full_packing_lot() {
        // Given
        ParkingBoy standardParkingBoy = new ParkingBoy(new StandardParkingStrategy());
        ParkingLot firstParkingLot = getFullPackingLot();
        ParkingLot secondParkingLot = getFullPackingLot();
        standardParkingBoy.addParkingLot(firstParkingLot);
        standardParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        // When
        // Then
        assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.park(car), NO_AVAILABLE_POSITION);
    }

}
