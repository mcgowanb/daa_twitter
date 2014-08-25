package com.airport.twitter;

public class Arrivals extends FlightObject {

	private String arrivFrom;

	public Arrivals() {
		super();
		arrivFrom = null;
	}

	public String getArrivFrom() {
		return arrivFrom;
	}

	public void setArrivFrom(String arrivFrom) {
		this.arrivFrom = arrivFrom;
	}

	@Override
	public String toString() {
		return terminal + ": " + airline + " From: " + arrivFrom + " Flight No: "
				+ flightNo + " Arrival Time: " + date + " Status: " + status;
	}

}
