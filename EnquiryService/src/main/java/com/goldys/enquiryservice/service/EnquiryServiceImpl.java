package com.goldys.enquiryservice.service;

import com.goldys.enquiryservice.exception.EnquiryNotFoundException;
import com.goldys.enquiryservice.model.Enquiry;
import com.goldys.enquiryservice.repository.EnquiryRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Boilerplate Code: Do Not Change
 */
@Service
public class EnquiryServiceImpl implements EnquiryService {

    private EnquiryRepository enquiryRepository;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public EnquiryServiceImpl(EnquiryRepository enquiryRepository, RabbitTemplate rabbitTemplate) {
        this.enquiryRepository = enquiryRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @CacheEvict(value = "enquiry-cache", allEntries = true)
    @Override
    public Enquiry addNewEnquiry(Enquiry enquiry) {

        Enquiry newEnquiry = enquiryRepository.save(enquiry);

        rabbitTemplate.convertAndSend("enquiry.new.queue", newEnquiry.getEnquiryCode());
        return newEnquiry;
    }

    @Cacheable(value = "enquiry-cache")
    @Override
    public List<Enquiry> listAllEnquiries() {
        return enquiryRepository.findAll();
    }

    @Cacheable(value = "enquiry-cache", key = "#p0")
    @Override
    public Enquiry getEnquiryByCode(String enquiryCode) throws EnquiryNotFoundException {
        if (enquiryRepository.findById(enquiryCode).isEmpty()) {
            throw new EnquiryNotFoundException();
        }
        return enquiryRepository.findById(enquiryCode).get();
    }

}
