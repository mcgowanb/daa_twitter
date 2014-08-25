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
	private String filePath;
	private Properties config;

	public App() {
		config = new Properties();
	}

	public static void main(String[] args) throws IOException {
		App app = new App();

		app.filePath = "/" + args[0];

		app.config.load(App.class.getResourceAsStream(app.filePath));

		app.arrivalsList = app.doArrivals();
		app.printList(app.arrivalsList);

		System.out.println();
		System.out.println("====================BREAK====================");
		System.out.println();

		app.departuresList = app.doDepartures();
		app.printList(app.departuresList);

	}

	public ArrayList<FlightObject> doArrivals() throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url, config);
		arrivalsList = parser.arrivalsFetch(url);
		arrivalsList = parser.processResults(arrivalsList);
		Collections.sort(arrivalsList, FlightObject.SORT_BY_DATE);
		return arrivalsList;
	}

	public ArrayList<FlightObject> doDepartures() throws IOException {
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config);
		departuresList = parser.departuresFetch(url);
		departuresList = parser.processResults(departuresList);
		Collections.sort(departuresList, FlightObject.SORT_BY_DATE);
		return departuresList;

	}

	

	public void printList(ArrayList<FlightObject> list) {
		for (FlightObject fo : list) {
			System.out.println(fo);
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
