package com.airport.twitter;

import org.jsoup.nodes.Element;

public class FlightObject {

	protected String terminal, airline, flightNo, date,
			status;

	public FlightObject() {

		terminal = null;
		airline = null;
		flightNo = null;
		date = null;
		status = null;

	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
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
		return "FlightObject [terminal=" + terminal + ", airline=" + airline
				+ ", flightNo=" + flightNo + ", date=" + date + ", status="
				+ status + "]";
	}

	
	

}
