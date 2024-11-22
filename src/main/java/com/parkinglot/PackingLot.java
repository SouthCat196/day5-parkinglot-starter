package com.parkinglot;

import java.lang.invoke.WrongMethodTypeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.parkinglot.Constant.*;

public class PackingLot {

    private final Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int capacity;

    public PackingLot() {
        this.capacity = ZERO;
    }

    public Ticket pack(Car car) {
        if(checkIsPackingLotFull()){
            throw new NoAvailablePositionException();
        }
        capacity++;
        return getTicket(car);
    }

    private boolean checkIsPackingLotFull() {
        return parkingRecord.size() >= MAXIMUM_CAPACITY;
    }

    private Ticket getTicket(Car car) {
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

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
