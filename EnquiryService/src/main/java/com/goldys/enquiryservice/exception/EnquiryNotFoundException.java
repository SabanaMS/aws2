package com.goldys.enquiryservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Boilerplate Code: Do Not Change
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Enquiry with specified details not found")
public class EnquiryNotFoundException extends Exception {

}
