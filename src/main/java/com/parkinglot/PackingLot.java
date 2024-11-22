package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

import static com.parkinglot.Constant.MAXIMUM_CAPACITY;
import static com.parkinglot.Constant.ZERO;

public class PackingLot {

    private Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int capacity;

    public PackingLot() {
        this.capacity = ZERO;
    }

    public Ticket pack(Car car) {
        if(checkIsPackingLotFull()){
            return null;
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
        if(capacity >= ZERO){
            capacity--;
        }
        return parkingRecord.remove(ticket);
    }
}
