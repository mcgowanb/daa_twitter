package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Hello world!
 * 
 */
public class App {
	
	private ArrayList<FlightObject> flights = new ArrayList<FlightObject>();
	
	public static void main(String[] args) throws IOException {
		App app = new App();

		String filePath = "/" + args[0];
		Properties config = new Properties();
		config.load(App.class.getResourceAsStream(filePath));
		
		Arrivals arr = new Arrivals(config);
		arr.gatherData();

		app.processArrivals(config);
		app.printList();
		
		System.out.println("random text here");

	}

	public void processArrivals(Properties config) throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url,config);
		flights = parser.dataFetch();
		//parser.sampleProcess();

	}
	
	public void printList(){
		for (FlightObject fo : flights){
			System.out.println(fo);
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
