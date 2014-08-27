package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 * 
 */
public class App {

	private ArrayList<FlightObject> departuresList = new ArrayList<FlightObject>();
	private ArrayList<FlightObject> arrivalsList = new ArrayList<FlightObject>();
	private ArrayList<String> airlineList = new ArrayList<String>();
	private String filePath, lastArrival, lastDeparture, arrivalsUrl,
			departuresUrl;
	private Properties config;
	private Document arrivalsDoc, departuresDoc;

	public App() {
		config = new Properties();
		arrivalsDoc = null;
		departuresDoc = null;
	}

	public static void main(String[] args) throws IOException {
		App app = new App();

		app.filePath = "/" + args[0];
		app.config.load(App.class.getResourceAsStream(app.filePath));
		app.arrivalsUrl = app.config.getProperty("arrivals_url");
		app.departuresUrl = app.config.getProperty("departures_url");

		app.airlineList = new AirlineList(app.config).generateList();

		System.out
				.println("Fetching data from the DAA Website...........................");
		System.out.println();
		System.out.println("Processing arrivals data");
		System.out.println();

		app.createDocs();

		for (String str : app.airlineList) {
			app.doArrivals(str);
			System.out.println(app.lastArrival);
			// app.postArrival(app.lastArrival);

			/*
			 * Create a new connection method to load the document. Remove this
			 * from the parser and once done pass the document to the methods to
			 * parse each Time this will save multiple url connections for each
			 * airline by arrivals and departres
			 * 
			 * **If the list for airline is blank, skip over it to avoid an NPE.
			 * Be cleverand use a try catch if you can.
			 * 
			 * 
			 * 
			 * 
			 * System.out.println();
			 * System.out.println("Processing departures data");
			 * System.out.println();
			 * 
			 * app.doDepartures(str); System.out.println(app.lastDeparture);
			 */
			// app.postDeparture(app.lastDeparture);

			// app.debug(app.arrivalsUrl, app.arrivalsList);
			// app.debug(app.arrivalsUrl, app.arrivalsList);
		}

	}

	public void createDocs() throws IOException {
		arrivalsDoc = Jsoup.connect(arrivalsUrl).get();
		departuresDoc = Jsoup.connect(departuresUrl).get();
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

	public void doArrivals(String airline) throws IOException {
		lastArrival = null;
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url, config, airline);
		arrivalsList = parser.arrivalsFetch(arrivalsDoc);
		arrivalsList = parser.processResults(arrivalsList);
		Collections.sort(arrivalsList, FlightObject.SORT_BY_DATE);
		if (!arrivalsList.isEmpty()) {
			lastArrival = arrivalsList.get(0).toString();
		} 

	}

	public void doDepartures(String airline) throws IOException {
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config, airline);
		departuresList = parser.departuresFetch(departuresDoc);
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
