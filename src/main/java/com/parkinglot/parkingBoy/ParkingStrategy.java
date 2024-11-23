package com.parkinglot.parkingBoy;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.Ticket;

import java.util.List;

public interface ParkingStrategy {
    Ticket park(List<ParkingLot> parkingLots, Car car);
}
