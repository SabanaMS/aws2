package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;

import java.util.List;

/*
 * Boilerplate Code: Do Not Change
 */
public interface EnquiryService {

    Enquiry addNewEnquiry(Enquiry enquiry);

    List<Enquiry> listAllEnquiries();

    Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException;

}
