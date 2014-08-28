package com.airport.twitter;

import java.util.*;

public class AirlineList {
	private ArrayList<String> airlineList;
	private String airlineStringList;

	public AirlineList(Properties config) {
		airlineList = new ArrayList<String>();
		airlineStringList = config.getProperty("airlines");
	}

	public ArrayList<String> generateList() {

		StringTokenizer st = new StringTokenizer(airlineStringList, ",");
		while (st.hasMoreTokens()) {
			airlineList.add(st.nextToken());
		}

		return airlineList;
	}

}
