package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;

public class StandardParkingStrategy implements ParkingStrategy {

    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.checkIsPackingLotFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
