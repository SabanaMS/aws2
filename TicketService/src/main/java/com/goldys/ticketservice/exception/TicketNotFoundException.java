package com.goldys.ticketservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Boilerplate Code: Do Not Change
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ticket with specified details not found")
public class TicketNotFoundException extends Exception {

}
