package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;
import java.util.Optional;

public class StandardParkingStrategy implements ParkingStrategy{

    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        Optional<ParkingLot> hasPositionPackingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.checkIsPackingLotFull())
                .findFirst();
        if (hasPositionPackingLot.isPresent()) {
            return hasPositionPackingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
