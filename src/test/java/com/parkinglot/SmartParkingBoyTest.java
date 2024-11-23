package com.parkinglot;

import com.parkinglot.parkingBoy.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SmartParkingBoyTest {

    @Test
    void should_car_parked_more_empty_positions_parking_lot_when_park_given_tow_not_same_empty_positions_parking_lot() {
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot();
        smartParkingBoy.addPackingLot(firstParkingLot);
        smartParkingBoy.addPackingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        boolean inSecondPackingLot = secondParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inSecondPackingLot);
    }

    @Test
    void should_car_parked_first_parking_lot_when_park_given_tow_same_empty_positions_parking_lot() {
        // Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot();
        secondParkingLot.park(new Car());
        secondParkingLot.park(new Car());
        smartParkingBoy.addPackingLot(firstParkingLot);
        smartParkingBoy.addPackingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        boolean inFirstPackingLot = firstParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inFirstPackingLot);
    }

}
