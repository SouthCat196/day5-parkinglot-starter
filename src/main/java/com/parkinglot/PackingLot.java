package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class PackingLot {

    private Map<Ticket, Car> parkingRecord = new HashMap<>();

    public Ticket pack(Car car) {
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecord.get(ticket);
    }
}
