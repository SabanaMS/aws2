package com.goldys.gymservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Boilerplate Code: Do Not Change
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Specified Program already exists")
public class ProgramAlreadyExistsException extends Exception {
}
