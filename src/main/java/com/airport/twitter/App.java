package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
		app.arrivalsList = app.processResults(app.arrivalsList);
		app.printList(app.arrivalsList);

		System.out.println();
		System.out.println("====================BREAK====================");
		System.out.println();

		app.departuresList = app.doDepartures();
		app.departuresList = app.processResults(app.departuresList);
		app.printList(app.departuresList);

	}

	public ArrayList<FlightObject> doArrivals() throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url, config);
		arrivalsList = parser.arrivalsFetch(url);
		return arrivalsList;
	}

	public ArrayList<FlightObject> doDepartures() throws IOException {
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config);
		departuresList = parser.departuresFetch(url);
		return departuresList;

	}

	public ArrayList<FlightObject> processResults(ArrayList<FlightObject> list) {
		for (Iterator<FlightObject> iter = list.iterator(); iter.hasNext();) {
			FlightObject fo = iter.next();
			if (fo.status == null) {
				iter.remove();
				continue;
			}
			if (fo.status.contains("Delayed") | fo.status.contains("Due")){
				iter.remove();
			}
		}
		return list;
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
