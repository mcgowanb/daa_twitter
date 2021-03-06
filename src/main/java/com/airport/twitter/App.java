package com.airport.twitter;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class App {

	private List<FlightObject> departuresObjectList = new ArrayList<FlightObject>();
	private List<FlightObject> arrivalsObjectList = new ArrayList<FlightObject>();
	private List<String> airlineListString = new ArrayList<String>();
	private List<String> arrivedFlightsStringList = new ArrayList<String>();
	private List<String> departedFlightsStringList = new ArrayList<String>();
	private String filePath, lastArrival, lastDeparture, arrivalsUrl, departuresUrl;
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
		app.createDocs();
		app.airlineListString = new AirlineNames(app.arrivalsDoc).generateAirlineNames();

		System.out.println("Fetching data from the DAA Website...........................");
		System.out.println();
		// System.out.println("====Listing Airlines=====");

		// ConsolePrinter.printStringList(app.airlineListString);

		System.out.println();
		System.out.println("==========PROCESSING ARRIVALS DATA==========");
		System.out.println();

		for (String str : app.airlineListString) {
			app.generateArrivalsData(str);
			app.generateDeparturesData(str);
		}

		FileHandler fh = new FileHandler(app.arrivedFlightsStringList, app.departedFlightsStringList);

		fh.executeFileActions();

		ConsolePrinter.printStringList(app.arrivedFlightsStringList);
		ConsolePrinter.insertGap();
		ConsolePrinter.printStringList(app.departedFlightsStringList);
	}

	public void createDocs() throws IOException {
		arrivalsDoc = Jsoup.connect(arrivalsUrl).get();
		departuresDoc = Jsoup.connect(departuresUrl).get();
	}

	public void generateArrivalsData(String airline) throws IOException {
		HtmlParser parser = new HtmlParser(airline);
		arrivalsObjectList = parser.arrivalsFetch(arrivalsDoc); //change this, sort before returning the list
		arrivalsObjectList = parser.removeIncompleteFlights(arrivalsObjectList);
		if (!arrivalsObjectList.isEmpty()) {
			arrivedFlightsStringList.add(arrivalsObjectList.get(0).toString());
		}
		// else something needed here for no flights by airline
	}

	public void generateDeparturesData(String airline) throws IOException {
		HtmlParser parser = new HtmlParser(airline);
		departuresObjectList = parser.departuresFetch(departuresDoc);
		departuresObjectList = parser.removeIncompleteFlights(departuresObjectList);
		if (!departuresObjectList.isEmpty()) {
			departedFlightsStringList.add(departuresObjectList.get(0).toString());
		}
		// else something needed here too
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


}
