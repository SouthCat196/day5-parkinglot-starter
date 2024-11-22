package com.parkinglot;

import java.util.Objects;
import java.util.UUID;

public class Car {

    private String carNumber;

    public Car() {
        this.carNumber = getNewCarNumber();
    }

    private String getNewCarNumber() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carNumber, car.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(carNumber);
    }
}
