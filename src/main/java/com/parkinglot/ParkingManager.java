package com.parkinglot;

public interface ParkingManager {
    Ticket park(Car car);

    Car fetch(Ticket ticket);
}
