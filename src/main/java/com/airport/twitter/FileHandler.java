package com.airport.twitter;

import java.io.*;
import java.util.*;

public class FileHandler {
	private Map<String, String> arrivalsMap = new TreeMap<String, String>();
	private Map<String, String> departuresMap = new TreeMap<String, String>();
	private ArrayList<String> arrivals;
	private ArrayList<String> departures;
	private String arrLocation, depLocation;
	
	public FileHandler(){
		this.arrLocation = "src/main/resources/arrivals.txt";
		this.depLocation = "src/main/resources/departures.txt";
	}

	public FileHandler(ArrayList<String> arrivals, ArrayList<String> departures) {
		this();
		this.arrivals = arrivals;
		this.departures = departures;
	}

	public Map<String, String> loadFromFile(String location) throws IOException {
		String str = null;
		Map <String, String> list = new TreeMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			String[] parts = str.split("=");
			list.put(parts[0], parts[1]);
		}
		br.close();
		return list;

	}

	public void checkDuplicateFlights() throws IOException {
		arrivalsMap = loadFromFile(arrLocation);
		ConsolePrinter.printMap(arrivalsMap);
		System.out.println();
		System.out.println();
		departuresMap = loadFromFile(depLocation);
		ConsolePrinter.printMap(departuresMap);

	}

	public Map<String, String> getArrivalsMap() {
		return arrivalsMap;
	}

	public void setArrivalsMap(Map<String, String> arrivalsMap) {
		this.arrivalsMap = arrivalsMap;
	}

	public Map<String, String> getDeparturesMap() {
		return departuresMap;
	}

	public void setDeparturesMap(Map<String, String> departuresMap) {
		this.departuresMap = departuresMap;
	}

	public ArrayList<String> getArrivals() {
		return arrivals;
	}

	public void setArrivals(ArrayList<String> arrivals) {
		this.arrivals = arrivals;
	}

	public ArrayList<String> getDepartures() {
		return departures;
	}

	public void setDepartures(ArrayList<String> departures) {
		this.departures = departures;
	}

	public String getArrLocation() {
		return arrLocation;
	}

	public void setArrLocation(String arrLocation) {
		this.arrLocation = arrLocation;
	}

	public String getDepLocation() {
		return depLocation;
	}

	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation;
	}
	
	
	

}
