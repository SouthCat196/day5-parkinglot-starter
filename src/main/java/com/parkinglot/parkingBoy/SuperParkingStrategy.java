package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        Optional<ParkingLot> maxAvailablePositionRatePackingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.checkIsPackingLotFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate));
        if (maxAvailablePositionRatePackingLot.isPresent()) {
            return maxAvailablePositionRatePackingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
