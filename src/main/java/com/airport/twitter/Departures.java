package com.airport.twitter;

public class Departures extends FlightObject {

	private String departTo;

	public Departures() {
		super();
		departTo = null;
	}

	public String getDepartTo() {
		return departTo;
	}

	public void setDepartTo(String departTo) {
		this.departTo = departTo;
	}

	@Override
	public String toString() {
		return terminal + ": " + airline + " To: " + departTo + " Flight No: "
				+ flightNo + " Departure Time: " + date + " Status: " + status;
	}

}
