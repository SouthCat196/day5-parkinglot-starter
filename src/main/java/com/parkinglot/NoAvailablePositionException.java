package com.parkinglot;

import static com.parkinglot.Constant.NO_AVAILABLE_POSITION;

public class NoAvailablePositionException extends RuntimeException {
    public NoAvailablePositionException() {
        super(NO_AVAILABLE_POSITION);
    }
}
