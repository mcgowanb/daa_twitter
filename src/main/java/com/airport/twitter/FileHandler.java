package com.airport.twitter;

import java.io.*;
import java.util.*;

public class FileHandler {
	private ArrayList<String> newArrivalsList;
	private ArrayList<String> newDeparturesList;
	private ArrayList<String> historicalArrivalsList = new ArrayList<String>();
	private ArrayList<String> historicalDeparturesList = new ArrayList<String>();
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

	public ArrayList<String> loadSavedFlightsFromFile(String location)
			throws IOException {
		String str = null;
		ArrayList<String> returnList = new ArrayList<String>();
		Map<String, String> list = new TreeMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			String[] parts = str.split("=");
			list.put(parts[0], parts[1]);
		}
		br.close();

		returnList = parseMapToList(list);

		return returnList;

	}

	public void prepareDataSets() throws IOException {
		historicalArrivalsList = loadSavedFlightsFromFile(arrLocation);
		newArrivalsList = removePreviouslyTweetedFlights(newArrivalsList,
				historicalArrivalsList);
		ConsolePrinter.printStringList(newArrivalsList);
		//clearFile(arrLocation);

		ConsolePrinter.insertGap();

		historicalDeparturesList = loadSavedFlightsFromFile(depLocation);
		newDeparturesList = removePreviouslyTweetedFlights(newDeparturesList,
				historicalDeparturesList);
		ConsolePrinter.printStringList(newDeparturesList);

	}

	public ArrayList<String> removePreviouslyTweetedFlights(
			ArrayList<String> newList, ArrayList<String> oldList) {

		for (Iterator<String> iterator = oldList.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			for (Iterator<String> iter = newList.iterator(); iter.hasNext();) {
				String str = iter.next();
				if (str.equals(string)) {
					iter.remove();
					continue;
				}
			}
		}
		return newList;
	}

	public void clearFile(String location) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(location));
		bw.close();
	}

	public ArrayList<String> parseMapToList(Map<String, String> oldList) {
		ArrayList<String> newList = new ArrayList<String>();

		for (Map.Entry<String, String> entry : oldList.entrySet()) {
			newList.add(entry.getValue());
		}
		return newList;
	}
}
