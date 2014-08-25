package com.airport.twitter;

import java.util.Comparator;


public class FlightObject {

	protected String terminal, airline, flightNo, date,
			status, hashTag;

	public FlightObject() {

		terminal = null;
		airline = null;
		flightNo = null;
		date = null;
		status = null;
		hashTag = null;

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



	public String getHashTag() {
		return hashTag;
	}



	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}



	@Override
	public String toString() {
		return "FlightObject [terminal=" + terminal + ",\t airline=" + airline
				+ ",\t flightNo=" + flightNo + ",\t date=" + date + ",\t status="
				+ status + "]";
	}

	public static Comparator<FlightObject> SORT_BY_DATE = new Comparator<FlightObject>() {
        public int compare(FlightObject one, FlightObject other) {
            return -one.date.compareTo(other.date);
        }
    };
	

}
