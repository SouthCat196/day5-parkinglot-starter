package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

import static com.parkinglot.constant.Constant.MAXIMUM_CAPACITY;
import static com.parkinglot.constant.Constant.ZERO;

public class ParkingLot {

    private final Map<Ticket, Car> parkingRecord = new HashMap<>();
    private int capacity;

    public ParkingLot() {
        this.capacity = ZERO;
    }

    public boolean checkIsPackingLotFull() {
        return parkingRecord.size() >= MAXIMUM_CAPACITY;
    }

    public Ticket park(Car car) {
        if(checkIsPackingLotFull()){
            throw new NoAvailablePositionException();
        }
        capacity++;
        return getTicket(car);
    }

    public Car fetch(Ticket ticket) {
        if(checkTicketInPackingLot(ticket)){
            capacity--;
            return parkingRecord.remove(ticket);
        }else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    public boolean checkTicketInPackingLot(Ticket ticket) {
        return parkingRecord.containsKey(ticket);
    }

    private Ticket getTicket(Car car) {
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

    public int getCapacity() {
        return capacity;
    }
}
