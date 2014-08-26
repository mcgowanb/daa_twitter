package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Hello world!
 * 
 */
public class App {

	private ArrayList<FlightObject> departuresList = new ArrayList<FlightObject>();
	private ArrayList<FlightObject> arrivalsList = new ArrayList<FlightObject>();
	private String filePath, lastArrival, lastDeparture, arrivalsUrl,
			departuresUrl;
	private Properties config;

	public App() {
		config = new Properties();
	}

	public static void main(String[] args) throws IOException {
		App app = new App();

		app.filePath = "/" + args[0];
		app.config.load(App.class.getResourceAsStream(app.filePath));
		app.arrivalsUrl = app.config.getProperty("arrivals_url");
		app.departuresUrl = app.config.getProperty("departures_url");
		System.out.println("Fetching data from the DAA Website...........................");
		System.out.println();
		System.out.println("Processing arrivals data");
		System.out.println();

		app.doArrivals();
		System.out.println(app.lastArrival);
		//app.postArrival(app.lastArrival);

		System.out.println();
		System.out.println("Processing departures data");
		System.out.println();

		app.doDepartures();
		System.out.println(app.lastDeparture);
		//app.postDeparture(app.lastDeparture);

		// app.debug(app.arrivalsUrl, app.arrivalsList);
		// app.debug(app.arrivalsUrl, app.arrivalsList);

	}

	public void postDeparture(String status) {
		TwitterProcess tp = new TwitterProcess(status);
		tp.initialiseDepartures(config);
		tp.postToTwitter();

	}

	public void postArrival(String status) {
		TwitterProcess tp = new TwitterProcess(status);
		tp.initialiseArrivals(config);
		tp.postToTwitter();

	}

	public void debug(String url, ArrayList<FlightObject> list) {

		System.out.println();
		System.out.println("====================DEBUG====================");
		System.out.println("Parsing data from....................." + url);
		System.out.println();
		printList(list);
		System.out.println("====================END DEBUG====================");
		System.out.println();

	}

	public void doArrivals() throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url, config);
		arrivalsList = parser.arrivalsFetch(url);
		arrivalsList = parser.processResults(arrivalsList);
		Collections.sort(arrivalsList, FlightObject.SORT_BY_DATE);
		lastArrival = arrivalsList.get(0).toString();

	}

	public void doDepartures() throws IOException {
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config);
		departuresList = parser.departuresFetch(url);
		departuresList = parser.processResults(departuresList);
		Collections.sort(departuresList, FlightObject.SORT_BY_DATE);
		lastDeparture = departuresList.get(0).toString();

	}

	public void printList(ArrayList<FlightObject> list) {
		for (FlightObject fo : list) {
			System.out.println(fo);
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	public void printArrivalsList() {
		System.out.println();
		System.out
				.println("====================LISTING ARRIVALS====================");
		printList(arrivalsList);
		System.out
				.println("====================END ARRIVALS====================");
		System.out.println();
	}

	public void printDeparturesList() {
		System.out.println();
		System.out
				.println("====================LISTING DEPARTURES====================");
		printList(departuresList);
		System.out
				.println("====================END DEPARTURES====================");
		System.out.println();
	}

}
