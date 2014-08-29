package com.airport.twitter;

import java.io.*;
import java.util.*;

public class FileHandler {
	private ArrayList<String> newArrivalsList;
	private ArrayList<String> newDeparturesList;
	private ArrayList<String> historicalArrivalsList;// = new ArrayList<String>();
	private ArrayList<String> historicalDeparturesList;// = new ArrayList<String>();
	private ArrayList<String> arrivalsToSaveExternally;// = new ArrayList<String>();
	private ArrayList<String> departuresToSaveExternally;// = new ArrayList<String>();
	private String arrLocation, depLocation;

	public FileHandler() {
		this.arrLocation = "src/main/resources/arrivals.txt";
		this.depLocation = "src/main/resources/departures.txt";
	}

	public FileHandler(ArrayList<String> arrivals, ArrayList<String> departures) {
		this();
		this.newArrivalsList = this.arrivalsToSaveExternally  = arrivals;
		this.newDeparturesList = this.departuresToSaveExternally = departures;
	}
	

	public ArrayList<String> loadSavedFlightsFromFile(String location)
			throws IOException {
		String str = null;
		ArrayList<String> returnList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			returnList.add(str);
		}
		br.close();


		return returnList;

	}

	public void prepareDataSets() throws IOException {
		historicalArrivalsList = loadSavedFlightsFromFile(arrLocation);
		newArrivalsList = removePreviouslyTweetedFlights(newArrivalsList,
				historicalArrivalsList);
		historicalDeparturesList = loadSavedFlightsFromFile(depLocation);
		newDeparturesList = removePreviouslyTweetedFlights(newDeparturesList,
				historicalDeparturesList);

	}

	public ArrayList<String> removePreviouslyTweetedFlights(ArrayList<String> newList, ArrayList<String> oldList) {

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
