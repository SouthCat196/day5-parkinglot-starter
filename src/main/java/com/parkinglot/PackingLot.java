package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class PackingLot {

    public static final int MAXIMUM_CAPACITY = 10;
    public static final int ZERO = 0;
    private Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int capacity;

    public PackingLot() {
        this.capacity = 0;
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
