package com.goldys.ticketservice.service;

import com.goldys.ticketservice.exception.TicketNotFoundException;
import com.goldys.ticketservice.exception.UserUnauthorizedException;
import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
 * This class is implementing the TicketService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class TicketServiceImpl implements TicketService {

    /*
     * Constructor Autowiring should be implemented for the TicketRepository
     * and RestTemplate.
     */
    private TicketRepository ticketRepository;

    private RestTemplate restTemplate;

    private ResponseEntity responseEntity;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, RestTemplate restTemplate) {
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;
    }

    /*
     * Retrieve all existing ticket details.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "tickets-cache")
    @Override
    public List<Ticket> listAllTickets() {
        return ticketRepository.findAll();
    }

    /*
     * Retrieve all existing ticket details which are in open status.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "open-tickets-cache")
    @Override
    public List<Ticket> listAllOpenTickets() {
        return ticketRepository.findByIsOpen(true);
    }

    /*
     * Retrieve an existing ticket by it's ticketId. Throw TicketNotFoundException if the
     * ticket with specified ticketId does not exist.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "tickets-cache", key = "#p0")
    @Override
    public Ticket getTicketByTicketId(String ticketId) throws TicketNotFoundException {
        if (ticketRepository.findById(ticketId).isEmpty()) {
            throw new TicketNotFoundException();
        }
        return ticketRepository.findById(ticketId).get();
    }


    /*
     * Update an existing Ticket by it's ticketId. Throw TicketNotFoundException if the
     * ticket with specified ticketId does not exist. Only a user with role "Executive" can update
     * the ticket details. To check the user authorization, API call needs to be made to UserService microservice
     * having the following details:
     * 1. Method: GET
     * 2. URI:
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     *
     * @HystrixCommand annotation should be used here with a fallback method which
     * will be called if the API call fails.
     */
    @CacheEvict(cacheNames = {"tickets-cache", "open-tickets-cache"}, allEntries = true)
    @Override
    @HystrixCommand(fallbackMethod = "updateTicketFallback")
    public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        if (ticketRepository.findById(ticket.getTicketId()).isEmpty()) {
            throw new TicketNotFoundException();
        }

        responseEntity = restTemplate.getForEntity("http://localhost:9000/userservice/api/v1/userservice/" + ticket.getExecutiveEmail(), String.class);
        if (responseEntity.getBody().equals("false")) {
            throw new UserUnauthorizedException();
        }
        return ticketRepository.save(ticket);
    }

    /*
     * This method is the fallback method for updateTicket() method. In case
     * updateTicket() method could not connect to API, this method will be called
     * which will set the executiveEmail attribute value to be "default"
     */
    public Ticket updateTicketFallback(Ticket ticket) throws TicketNotFoundException, UserUnauthorizedException {
        ticket.setExecutiveEmail("default");
        return ticketRepository.save(ticket);
    }
}
