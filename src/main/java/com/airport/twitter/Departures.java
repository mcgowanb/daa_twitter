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
		return "Departures [terminal=" + terminal + ", departTo=" + departTo
				+ ", airline=" + airline + ", flightNo=" + flightNo + ", date="
				+ date + ", status=" + status + "]";
	}

}
