package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Hello world!
 * 
 */
public class App {
	
	private ArrayList<FlightObject> departuresList = new ArrayList<FlightObject>();
	private ArrayList<FlightObject> arrivalsList = new ArrayList<FlightObject>();
	private boolean isArrivals;
	private String filePath;
	
	public App(){
		isArrivals = false;
	}
	
	public static void main(String[] args) throws IOException {
		App app = new App();

		app.filePath = "/" + args[0];
		//if (args[1] == "arrivals"){
			//app.isArrivals = true;
		//}
		Properties config = new Properties();
		config.load(App.class.getResourceAsStream(app.filePath));
		
		//Arrivals arr = new Arrivals(config);
		//arr.gatherData();

		app.processArrivals(config);
		app.printList(app.arrivalsList);
		
		System.out.println();
		System.out.println("====================BREAK====================");
		System.out.println();
		
		app.processDeparture(config);
		app.printList(app.departuresList);
		
	}	
	
	public void arrivalOrDepartures(){
		
	}
	
	
	public void processDeparture(Properties config) throws IOException{
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config);
		departuresList = parser.dataFetch();
		
	}

	public void processArrivals(Properties config) throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url,config);
		arrivalsList = parser.dataFetch();
		//parser.sampleProcess();

	}
	
	public void printList(ArrayList<FlightObject> list){
		for (FlightObject fo : list){
			System.out.println(fo);
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
