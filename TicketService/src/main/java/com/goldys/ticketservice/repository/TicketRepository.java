package com.goldys.ticketservice.repository;

import com.goldys.ticketservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * This class is implementing the MongoRepository interface for Enquiry.
 * Annotate this class with @Repository annotation
 */
@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByIsOpen(boolean open);
}
