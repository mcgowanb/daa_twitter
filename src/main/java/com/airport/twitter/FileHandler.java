package com.airport.twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
	private Map<String,String> arrivalsMap = new TreeMap<String,String>();
	private Map<String,String> departuresMap = new TreeMap<String,String>();
	private ArrayList<String> arrivals = new ArrayList<String>();
	private ArrayList<String> departures = new ArrayList<String>();
	private String arrLocation, depLocation;
	
	public FileHandler(ArrayList<String> arrivals, ArrayList<String> departures){
		this.arrivals = arrivals;
		this.departures = departures;
		this.arrLocation = "src/main/resources/arrivals.txt";
		this.depLocation = "src/main/resources/departures.txt";
		
	}

	public void loadFromFile(Map<String, String> list, String location) throws IOException {
		String str = null;
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null){
			String[] parts = str.split("=");
			list.put(parts[0], parts[1]);
		}
		br.close();
		
		
	}
	
	public void checkDuplicateFlights() throws IOException{
		loadFromFile(arrivalsMap, arrLocation);
		ConsolePrinter.printMap(arrivalsMap);
		System.out.println();
		System.out.println();
		loadFromFile(departuresMap, depLocation);
		ConsolePrinter.printMap(departuresMap);
		
	}
	
	
}

