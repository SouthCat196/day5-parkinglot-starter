package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;
import java.util.Optional;

public class SmartParkingStrategy implements ParkingStrategy{
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        Optional<ParkingLot> maxEmptyPositionPackingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.checkIsPackingLotFull())
                .max((firstParkingBoy, secondParkingBoy) -> secondParkingBoy.getCapacity() - firstParkingBoy.getCapacity());
        if (maxEmptyPositionPackingLot.isPresent()) {
            return maxEmptyPositionPackingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
