package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.parkinglot.constant.Constant.MAXIMUM_CAPACITY;
import static com.parkinglot.constant.Constant.ZERO;

public class PackingLot implements ParkingManager{

    private final Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int capacity;

    public PackingLot() {
        this.capacity = ZERO;
    }

    private boolean checkIsPackingLotFull() {
        return parkingRecord.size() >= MAXIMUM_CAPACITY;
    }

    private Ticket getTicket(Car car) {
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

    @Override
    public Ticket park(Car car) {
        if(checkIsPackingLotFull()){
            throw new NoAvailablePositionException();
        }
        capacity++;
        return getTicket(car);
    }

    @Override
    public Car fetch(Ticket ticket) {
        Car car = parkingRecord.remove(ticket);
        if(Objects.isNull(car)){
            throw new UnrecognizedParkingTicketException();
        }
        if(capacity >= ZERO){
            capacity--;
        }
        return car;
    }

}
