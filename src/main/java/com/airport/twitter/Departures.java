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
		return "Arrivals [terminal= " + terminal + ", \tdepartTo= " + departTo
				+ ", \t airline= " + airline + ", \t flightNo= " + flightNo + ", \t date= "
				+ date + ",\t status= " + status + "]";
	}

}
