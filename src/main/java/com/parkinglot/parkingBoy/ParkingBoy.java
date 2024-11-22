package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.Ticket;

public interface ParkingBoy {
    Ticket park(Car car);

    Car fetch(Ticket ticket);
}
