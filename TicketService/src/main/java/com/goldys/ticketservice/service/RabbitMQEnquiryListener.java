package com.goldys.ticketservice.service;

import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * This class is implementing the EnquiryListener interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class RabbitMQEnquiryListener implements EnquiryListener {

    /*
     * Constructor Autowiring should be implemented for the TicketRepository
     * and Ticket.
     */
    private TicketRepository ticketRepository;

    private Ticket ticket;

    @Autowired
    public RabbitMQEnquiryListener(TicketRepository ticketRepository, Ticket ticket) {
        this.ticketRepository = ticketRepository;
        this.ticket = ticket;
    }

    /*
     * This method should listen to RabbitMQ queue "enquiry.new.queue", on receiving a new message,
     * it should add a new ticket with the enquiryCode which should be coming from
     * the queue
     */
    @RabbitListener(queues = "enquiry.new.queue")
    public void addNewTicket(String enquiryCode) {
        ticket = new Ticket();
        ticket.setEnquiryCode(enquiryCode);
        ticket.setOpen(true);

        ticketRepository.save(ticket);

    }
}