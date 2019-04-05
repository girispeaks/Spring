package com.infotech.ticketbookingapp.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Ticket")
public class Ticket {
	
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ticketId;
	@Column(name="passenger_name")
	private String passengerName;
	@Column(name="booking_date")
	private Date bookingDate;
	@Column(name="source_station")
	private String sourceStation;
	@Column(name="destination_Station")
	private String destStation;
	@Column(name="email")
	private String email;
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}
	public String getDestStation() {
		return destStation;
	}
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	

}
