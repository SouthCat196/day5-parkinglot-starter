package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

public interface ParkingLotManagement {
    Ticket park(Car car);

    Car fetch(Ticket ticket);

    void addParkingLot(ParkingLot parkingLot);
}
