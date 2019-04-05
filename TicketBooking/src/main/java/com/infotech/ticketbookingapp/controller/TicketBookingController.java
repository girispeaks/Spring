package com.infotech.ticketbookingapp.controller;

import java.util.Iterator;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.ticketbookingapp.entities.Ticket;
import com.infotech.ticketbookingapp.services.TicketBookingService;

@RestController
@RequestMapping(value="api/tickets")
public class TicketBookingController {
	
	@Autowired
	private TicketBookingService ticketBookingService;
	
	
	//difference between @PathParam and @PathVariable
	//difference between @PathVariable and @RequestParam
	@GetMapping(value="/ticket/{ticketId}")
	public Ticket getTicketBooking(@PathVariable("ticketId") Integer ticketId) {
		Optional<Ticket> ticket=ticketBookingService.getTicketBooking(ticketId);
		return ticket.get();
	}
	
	@PostMapping(value="/create")
	public Ticket createBooking(@RequestBody Ticket ticket) {
		return ticketBookingService.createBooking(ticket);	
	}
	
	@GetMapping(value="/allTickets")
	public Iterable<Ticket> getAllTickets(){
		return ticketBookingService.getAllTickets();
	}
	
	@PutMapping(value="/ticket/{ticketId}/{newEmail:.+}")
	public Ticket updateTicketBooking(@PathVariable("ticketId") Integer ticketId, @PathVariable("newEmail") String newEmail) {
		return ticketBookingService.updateTicketBooking(ticketId, newEmail);
		
	}
	
	@DeleteMapping(value="/ticket/{ticketId}")
	public void deleteBooking(@PathVariable("ticketId") Integer tickedId) {
		ticketBookingService.deleteTicketBooking(tickedId);
		
	}
}
