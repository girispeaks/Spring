package com.infotech.ticketbookingapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infotech.ticketbookingapp.entities.Ticket;

@Repository
public interface TicketBookingDao extends CrudRepository<Ticket, Integer>{

}
