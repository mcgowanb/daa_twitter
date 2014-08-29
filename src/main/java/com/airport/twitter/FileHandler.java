package com.airport.twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
	private Map<String, String> arrivalsMap = new TreeMap<String, String>();
	private Map<String, String> departuresMap = new TreeMap<String, String>();
	private ArrayList<String> newArrivalsList;
	private ArrayList<String> newDeparturesList;
	private ArrayList<String> oldArrivalsList = new ArrayList<String>();
	private ArrayList<String> oldDeparturesList = new ArrayList<String>();
	private String arrLocation, depLocation;

	public FileHandler() {
		this.arrLocation = "src/main/resources/arrivals.txt";
		this.depLocation = "src/main/resources/departures.txt";
	}

	public FileHandler(ArrayList<String> arrivals, ArrayList<String> departures) {
		this();
		this.newArrivalsList = arrivals;
		this.newDeparturesList = departures;
	}

	public Map<String, String> loadFromFile(String location) throws IOException {
		String str = null;
		Map<String, String> list = new TreeMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			String[] parts = str.split("=");
			list.put(parts[0], parts[1]);
		}
		br.close();
		return list;

	}

	public void prepareDataSets() throws IOException {
		arrivalsMap = loadFromFile(arrLocation);
		oldArrivalsList = parseMapToList(arrivalsMap);
		newArrivalsList = removeDuplicates(newArrivalsList,oldArrivalsList);
		ConsolePrinter.printStringList(newArrivalsList);
		
		ConsolePrinter.insertGap();

		departuresMap = loadFromFile(depLocation);
		oldDeparturesList = parseMapToList(departuresMap);
		newDeparturesList = removeDuplicates(newDeparturesList,oldDeparturesList);
		ConsolePrinter.printStringList(newDeparturesList);

	}
	
	public ArrayList<String> removeDuplicates(ArrayList<String>newList,ArrayList<String>oldList){
		
		for(Iterator<String> iterator = oldList.iterator(); iterator.hasNext();){
			String string = iterator.next();
			for(Iterator<String> iter = newList.iterator(); iter.hasNext();){
				String str = iter.next();
				if(str.equals(string)){
					iter.remove();
					continue;
				}
			}
		}
		return newList;
	}

	public ArrayList<String> parseMapToList(Map<String, String> oldList) {
		ArrayList<String> newList = new ArrayList<String>();

		for (Map.Entry<String, String> entry : oldList.entrySet()) {
			newList.add(entry.getValue());
		}
		return newList;
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
		return newArrivalsList;
	}

	public void setArrivals(ArrayList<String> arrivals) {
		this.newArrivalsList = arrivals;
	}

	public ArrayList<String> getDepartures() {
		return newDeparturesList;
	}

	public void setDepartures(ArrayList<String> departures) {
		this.newDeparturesList = departures;
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
