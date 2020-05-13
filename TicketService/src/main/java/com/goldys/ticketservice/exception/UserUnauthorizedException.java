package com.goldys.ticketservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Boilerplate Code: Do Not Change
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Only an executive could update tickets!!")
public class UserUnauthorizedException extends Exception {
}
