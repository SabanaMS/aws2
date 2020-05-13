package com.goldys.ticketservice.service;

import com.goldys.ticketservice.model.Ticket;
import com.goldys.ticketservice.repository.TicketRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Boilerplate Code: Do Not Change
 */
@Service
public class RabbitMQEnquiryListener implements EnquiryListener {


    private TicketRepository ticketRepository;

    private Ticket ticket;

    @Autowired
    public RabbitMQEnquiryListener(TicketRepository ticketRepository, Ticket ticket) {
        this.ticketRepository = ticketRepository;
        this.ticket = ticket;
    }

    @RabbitListener(queues = "enquiry.new.queue")
    public void addNewTicket(String enquiryCode) {
        ticket = new Ticket();
        ticket.setEnquiryCode(enquiryCode);
        ticket.setOpen(true);

        ticketRepository.save(ticket);

    }
}
