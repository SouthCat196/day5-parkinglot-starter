package com.parkinglot;

import com.parkinglot.parkingBoy.SuperParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SuperParkingBoyTest {

    @Test
    void should_car_parked_max_available_position_rate_parking_lot_when_park_given_tow_not_same_available_position_rate_parking_lot() {
        // Given
        SuperParkingBoy smartParkingBoy = new SuperParkingBoy();
        // 80 / 100
        ParkingLot firstParkingLot = new ParkingLot(100);
        IntStream.rangeClosed(1, 80)
                .forEach(i -> firstParkingLot.park(new Car()));
        ParkingLot secondParkingLot = new ParkingLot(10);
        IntStream.rangeClosed(1, 7)
                .forEach(i -> secondParkingLot.park(new Car()));
        smartParkingBoy.addParkingLot(firstParkingLot);
        smartParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        boolean inSecondPackingLot = secondParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inSecondPackingLot);
    }

    @Test
    void should_car_parked_first_parking_lot_when_park_given_tow_same_available_position_rate_parking_lot() {
        // Given
        SuperParkingBoy smartParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(3);
        IntStream.rangeClosed(1, 2)
                .forEach(i -> firstParkingLot.park(new Car()));
        ParkingLot secondParkingLot = new ParkingLot(30);
        IntStream.rangeClosed(1, 20)
                .forEach(i -> secondParkingLot.park(new Car()));
        smartParkingBoy.addParkingLot(firstParkingLot);
        smartParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        boolean inFirstPackingLot = firstParkingLot.checkTicketInPackingLot(ticket);
        assertTrue(inFirstPackingLot);
    }
}
