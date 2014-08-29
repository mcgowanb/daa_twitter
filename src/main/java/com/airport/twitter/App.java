package com.airport.twitter;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class App {

	private ArrayList<FlightObject> departuresList = new ArrayList<FlightObject>();
	private ArrayList<FlightObject> arrivalsList = new ArrayList<FlightObject>();
	private ArrayList<String> airlineList = new ArrayList<String>();
	private ArrayList<String> arrivedFlights = new ArrayList<String>();
	private ArrayList<String> departedFlights = new ArrayList<String>();
	private String filePath, lastArrival, lastDeparture, arrivalsUrl,
			departuresUrl;
	private Properties config;
	private Document arrivalsDoc, departuresDoc;
	private Map<String, String> flightMap = new TreeMap<String, String>();

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
		app.createDocs();
		
		System.out
				.println("Fetching data from the DAA Website...........................");
		System.out.println();
		System.out.println("==========PROCESSING ARRIVALS DATA==========");
		System.out.println();

		app.flightMap.clear();
		for (String str : app.airlineList) {
			app.doArrivals(str);
		}
		
		app.flightMap.clear();		//Clear the hashmap before re=use
		for (String str : app.airlineList) {	//temp
			app.doDepartures(str);				//temp
		}
			
			
		FileHandler fh = new FileHandler(app.arrivedFlights, app.departedFlights);
		fh.prepareDataSets();
		
		/*
		ConsolePrinter.printStringList(app.arrivedFlights);

		System.out.println();
		System.out.println("==========END OF ARRIVALS DATA==========");
		System.out.println();

		System.out.println();
		System.out.println("==========PROCESSING DEPARTURES DATA==========");
		System.out.println();

		app.flightMap.clear();		//Clear the hashmap before re=use
		for (String str : app.airlineList) {
			app.doDepartures(str);
		}
		ConsolePrinter.printStringList(app.departedFlights);

		System.out.println();
		System.out.println("==========END OF DEPARTURES DATA==========");
		System.out.println();
*/
	}

	public void prepareForTwitter() {

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
		ConsolePrinter.printObjecList(list);
		System.out.println("====================END DEBUG====================");
		System.out.println();

	}

	public void doArrivals(String airline) throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url, config, airline);
		arrivalsList = parser.arrivalsFetch(arrivalsDoc);
		arrivalsList = parser.processResults(arrivalsList);
		if (!arrivalsList.isEmpty()) {
			arrivedFlights.add(arrivalsList.get(0).toString());
			flightMap.put(airline, arrivalsList.get(0).toString());
		}

	}

	public void doDepartures(String airline) throws IOException {
		String url = config.getProperty("departures_url");
		HtmlParser parser = new HtmlParser(url, config, airline);
		departuresList = parser.departuresFetch(departuresDoc);
		departuresList = parser.processResults(departuresList);
		if (!departuresList.isEmpty()) {
			departedFlights.add(departuresList.get(0).toString());
			flightMap.put(airline, departuresList.get(0).toString());
		}

	}

}
