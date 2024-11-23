package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy;

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(parkingLots, car);
    }

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
}
