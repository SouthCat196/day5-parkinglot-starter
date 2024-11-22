package com.parkinglot;

import static com.parkinglot.Constant.WRONG_TICKET_MESSAGE;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public UnrecognizedParkingTicketException() {
        super(WRONG_TICKET_MESSAGE);
    }
}
