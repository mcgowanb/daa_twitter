package com.airport.twitter;


public class Departures {

	private String terminal, departTo, airline, flightNo, date, status;

	public Departures() {
		
		terminal = null;
		airline = null;
		departTo = null;
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

	public String getDepartTo() {
		return departTo;
	}


	public void setDepartTo(String departTo) {
		this.departTo = departTo;
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
		return "Departures [terminal=" + terminal + ", departTo=" + departTo
				+ ", airline=" + airline + ", flightNo=" + flightNo + ", date="
				+ date + ", status=" + status + "]";
	}


	
	
	

}
