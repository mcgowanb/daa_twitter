package com.airport.twitter;

import org.jsoup.nodes.Element;

public class FlightObject {

	private String terminal, arrivFrom, airline, flightNo, date, status;
	private boolean isArrivals;

	public FlightObject() {
		
		terminal = null;
		airline = null;
		arrivFrom = null;
		flightNo = null;
		date = null;
		status = null;
		boolean isArrivals = false;
		
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getArrivFrom() {
		return arrivFrom;
	}

	public void setArrivFrom(String arrivFrom) {
		this.arrivFrom = arrivFrom;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FlightObject [terminal = " + terminal + ",\t arrivFrom = "
				+ arrivFrom + ",\t airline = " + airline + ",\t flightNo = " + flightNo
				+ ",\t date = " + date + ",\t status = " + status + "]";
	}
	
	
	

}
