package com.parkinglot;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private String ticketNumber;

    public Ticket() {
        this.ticketNumber = getNewTicketNumber();
    }

    private String getNewTicketNumber() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketNumber, ticket.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ticketNumber);
    }
}
