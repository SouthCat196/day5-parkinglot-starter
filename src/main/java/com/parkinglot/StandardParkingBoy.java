package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StandardParkingBoy implements ParkingManager {

    private List<ParkingLot> parkingLots = new ArrayList<>();

    @Override
    public Ticket park(Car car) {
        Optional<ParkingLot> hasPositionPackingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.checkIsPackingLotFull())
                .findFirst();
        if (hasPositionPackingLot.isPresent()) {
            return hasPositionPackingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }

    @Override
    public Car fetch(Ticket ticket) {
        Optional<ParkingLot> matchPackingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.checkTicketInPackingLot(ticket))
                .findFirst();
        if (matchPackingLot.isPresent()) {
            return matchPackingLot.get().fetch(ticket);
        } else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    public void addPackingLot(ParkingLot parkingLot) {
        if (!parkingLots.contains(parkingLot)) {
            parkingLots.add(parkingLot);
        }
    }
}
