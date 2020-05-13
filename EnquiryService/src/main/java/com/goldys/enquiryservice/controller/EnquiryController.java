package com.goldys.enquiryservice.controller;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.proxy.EnquiryServiceProxy;
import com.goldys.enquiryservice.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Boilerplate Code: Do Not Change
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/enquiryservice")
public class EnquiryController {

    private EnquiryService enquiryService;

    private EnquiryServiceProxy enquiryServiceProxy;

    @Autowired
    public EnquiryController(EnquiryService enquiryService, EnquiryServiceProxy enquiryServiceProxy) {
        this.enquiryService = enquiryService;
        this.enquiryServiceProxy = enquiryServiceProxy;
    }

    @GetMapping("/admin/")
    public ResponseEntity<?> listAllEnquiries() {
        return new ResponseEntity<>(enquiryService.listAllEnquiries(), HttpStatus.OK);
    }

    @GetMapping("/admin/{enquiryCode}")
    public ResponseEntity<?> getEnquiryByCode(@PathVariable String enquiryCode) throws EnquiryNotFoundException {
        return new ResponseEntity<>(enquiryService.getEnquiryByCode(enquiryCode), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addNewEnquiry(@RequestBody Enquiry enquiry) {
        return new ResponseEntity<>(enquiryService.addNewEnquiry(enquiry), HttpStatus.CREATED);
    }

}
