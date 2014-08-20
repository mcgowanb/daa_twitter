package com.airport.twitter;

import java.util.Properties;

public class Arrivals {
	
	private String airline, url;

	public Arrivals(Properties config) {
		airline = config.getProperty("carrier");
		url = config.getProperty("arrivals_url");
	}
	
	public void gatherData(){
		App.print("Fetching %s%s%s", airline, " arrival data from: ", url);
	}
	
	

}
