package com.airport.twitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FileHandler {
	private ArrayList<String> newArrivalsList;
	private ArrayList<String> newDeparturesList;
	private ArrayList<String> historicalArrivalsList;
	private ArrayList<String> historicalDeparturesList;
	private ArrayList<String> arrivalsToSaveExternally;
	private ArrayList<String> departuresToSaveExternally;
	private String arrLocation, depLocation;

	public FileHandler() {
		this.arrLocation = "src/main/resources/arrivals.txt";
		this.depLocation = "src/main/resources/departures.txt";
	}

	
	public FileHandler(ArrayList<String> arrivals, ArrayList<String> departures) {
		this();
		this.newArrivalsList = this.arrivalsToSaveExternally = arrivals;
		this.newDeparturesList = this.departuresToSaveExternally = departures;
	}
	
	public void checkForNewData(){
		if (newArrivalsList.isEmpty() & newDeparturesList.isEmpty()){
			System.out.println("There are no flights to analyse");
			System.exit(0);
		}
	}
	
	public void executeFileActions() throws IOException{
		historicalArrivalsList = loadSavedFlightsFromFile(arrLocation);
		newArrivalsList = removePreviouslyTweetedFlights(newArrivalsList, historicalArrivalsList);
		historicalDeparturesList = loadSavedFlightsFromFile(depLocation);
		newDeparturesList = removePreviouslyTweetedFlights(newDeparturesList, historicalDeparturesList);
		checkForNewData();	//So, need to only remove the flights that have matches from the file, otherwise there will be multiple postings of stuff
		clearFile(arrLocation);
		clearFile(depLocation);
		saveFlightsToFile(arrLocation, arrivalsToSaveExternally);
		saveFlightsToFile(depLocation, departuresToSaveExternally);
	}
	

	public ArrayList<String> loadSavedFlightsFromFile(String location) throws IOException {
		String str = null;
		ArrayList<String> returnList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(location));
		while ((str = br.readLine()) != null) {
			returnList.add(str);
		}
		br.close();

		return returnList;

	}
	
	public void saveFlightsToFile(String location, ArrayList<String> list) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(location));
		for(String s : list){
			bw.write(s);	
			bw.newLine();
		}
		bw.close();
		
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


	public Object getArrLocation() {
		return arrLocation;
	}


	public Object getDepLocation() {
		return depLocation;
	}


	public Object getNewArrivalsList() {
		// TODO Auto-generated method stub
		return newArrivalsList;
	}


	public Object getNewDeparturesList() {
		// TODO Auto-generated method stub
		return newDeparturesList;
	}
}
