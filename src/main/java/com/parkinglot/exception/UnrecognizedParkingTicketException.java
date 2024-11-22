package com.parkinglot.exception;

import static com.parkinglot.constant.Constant.WRONG_TICKET_MESSAGE;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public UnrecognizedParkingTicketException() {
        super(WRONG_TICKET_MESSAGE);
    }
}
