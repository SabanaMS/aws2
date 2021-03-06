package com.goldys.gymservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Boilerplate Code: Do Not Change
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Specified Program not found")
public class ProgramNotFoundException extends Exception {
}
