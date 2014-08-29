package com.airport.twitter;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class App {

	private ArrayList<FlightObject> departuresObjectList = new ArrayList<FlightObject>();
	private ArrayList<FlightObject> arrivalsObjectList = new ArrayList<FlightObject>();
	private ArrayList<String> airlineListString = new ArrayList<String>();
	private ArrayList<String> arrivedFlightsStringList = new ArrayList<String>();
	private ArrayList<String> departedFlightsStringList = new ArrayList<String>();
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
		app.createDocs();
		app.airlineListString = new AirlineNames(app.arrivalsDoc).generateAirlineNames();
		
		
		System.out
				.println("Fetching data from the DAA Website...........................");
		System.out.println();
		System.out.println("====Listing Airlines=====");
		
		ConsolePrinter.printStringList(app.airlineListString);
		
		System.out.println();
		System.out.println("==========PROCESSING ARRIVALS DATA==========");
		System.out.println();
		

		for (String str : app.airlineListString) {		//Looping through the airline list and processing the data methods for each carrier
			app.generateArrivalsData(str);
			app.generateDeparturesData(str);		
		}
		
			
		FileHandler fh = new FileHandler(app.arrivedFlightsStringList, app.departedFlightsStringList);
		fh.prepareDataSets();
		
		ConsolePrinter.printStringList(app.arrivedFlightsStringList);
		ConsolePrinter.insertGap();
		ConsolePrinter.printStringList(app.departedFlightsStringList);
	}


	public void createDocs() throws IOException {
		arrivalsDoc = Jsoup.connect(arrivalsUrl).get();
		departuresDoc = Jsoup.connect(departuresUrl).get();
	}



	public void generateArrivalsData(String airline) throws IOException {
		HtmlParser parser = new HtmlParser(arrivalsUrl, config, airline);
		arrivalsObjectList = parser.arrivalsFetch(arrivalsDoc);
		arrivalsObjectList = parser.processResults(arrivalsObjectList); //remove nulls & incomplete flights, sort in ascending order by time
		if (!arrivalsObjectList.isEmpty()) {
			arrivedFlightsStringList.add(arrivalsObjectList.get(0).toString());
		}
//else something needed here for no flights by airline
	}

	public void generateDeparturesData(String airline) throws IOException {
		HtmlParser parser = new HtmlParser(departuresUrl, config, airline);
		departuresObjectList = parser.departuresFetch(departuresDoc);
		departuresObjectList = parser.processResults(departuresObjectList);
		if (!departuresObjectList.isEmpty()) {
			departedFlightsStringList.add(departuresObjectList.get(0).toString());
		}
//else something needed here too
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



}
