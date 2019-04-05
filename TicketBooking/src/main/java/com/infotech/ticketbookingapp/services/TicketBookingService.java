package com.infotech.ticketbookingapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotech.ticketbookingapp.dao.TicketBookingDao;
import com.infotech.ticketbookingapp.entities.Ticket;

@Service
public class TicketBookingService {
	
	@Autowired
	private TicketBookingDao ticketBookingDao;

	public Ticket createBooking(Ticket ticket) {
		return ticketBookingDao.save(ticket);
	}

	public Optional<Ticket> getTicketBooking(Integer id) {
		return ticketBookingDao.findById(id);
	}

	public Iterable<Ticket> getAllTickets() {
		return ticketBookingDao.findAll();
	}

	public Ticket updateTicketBooking(Integer ticketId, String newEmail) {
		Ticket tkt=ticketBookingDao.findById(ticketId).get();
		tkt.setEmail(newEmail);
		return ticketBookingDao.save(tkt);
	}

	public void deleteTicketBooking(Integer tickedId) {
		ticketBookingDao.deleteById(tickedId);		
	}
}
