package com.airport.twitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	private List<String> newArrivalsList = new ArrayList<String>();
	private List<String> newDeparturesList = new ArrayList<String>();
	private List<String> historicalArrivalsList = new ArrayList<String>();
	private List<String> historicalDeparturesList = new ArrayList<String>();
	private List<String> arrivalsToSaveExternally = new ArrayList<String>();
	private List<String> departuresToSaveExternally = new ArrayList<String>();
	private static String arrLocation = "src/main/resources/arrivals.txt";
	private static String depLocation = "src/main/resources/departures.txt";

	public FileHandler() {
	}

	public FileHandler(List<String> arrivals, List<String> departures) {
		this.newArrivalsList = this.arrivalsToSaveExternally = arrivals;
		this.newDeparturesList = this.departuresToSaveExternally = departures;
	}

	public void executeFileActions() throws IOException {
		historicalArrivalsList = loadSavedFlightsFromFile(arrLocation);
		newArrivalsList = removePreviouslyTweetedFlights(newArrivalsList, historicalArrivalsList);
		historicalDeparturesList = loadSavedFlightsFromFile(depLocation);
		newDeparturesList = removePreviouslyTweetedFlights(newDeparturesList, historicalDeparturesList);
		checkForNewData(); // So, need to only remove the flights that have
							// matches from the file, otherwise there will be
							// multiple postings of stuff
		clearFile(arrLocation);
		clearFile(depLocation);
		saveFlightsToFile(arrLocation, arrivalsToSaveExternally);
		saveFlightsToFile(depLocation, departuresToSaveExternally);
	}

	public void checkForNewData() {
		if (newArrivalsList.isEmpty() & newDeparturesList.isEmpty()) {
			System.out.println("There are no flights to analyse");
			System.exit(0);
		}
	}

	public List<String> loadSavedFlightsFromFile(String location) throws IOException {
		String str = null;
		List<String> returnList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			returnList.add(str);
		}
		br.close();

		return returnList;

	}

	public void saveFlightsToFile(String location, List<String> list) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(location));
		for (String s : list) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();

	}

	public List<String> removePreviouslyTweetedFlights(List<String> newList, List<String> oldList) {
		newList.removeAll(oldList);
		return newList;
	}

	public void clearFile(String location) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(location));
		bw.close();
	}

	public String getArrLocation() {
		return arrLocation;
	}

	public String getDepLocation() {
		return depLocation;
	}

	public List<String> getNewArrivalsList() {
		return newArrivalsList;
	}

	public List<String> getNewDeparturesList() {
		return newDeparturesList;
	}
}
