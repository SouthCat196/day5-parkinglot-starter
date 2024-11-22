package com.parkinglot;

public class ParkingBoy implements ParkingManager{

    private PackingLot packingLot;

    public ParkingBoy(PackingLot packingLot) {
        this.packingLot = packingLot;
    }

    @Override
    public Ticket park(Car car) {
        return packingLot.park(car);
    }

    @Override
    public Car fetch(Ticket ticket) {
        return packingLot.fetch(ticket);
    }
}
