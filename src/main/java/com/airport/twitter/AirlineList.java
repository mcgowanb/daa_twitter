package com.airport.twitter;

import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

public class AirlineList {
	private Properties config;
	private ArrayList<String> airlineList;
	private String airlineStringList;
	
	public AirlineList(Properties config){
		this.config = config;
		airlineList = new ArrayList<String>();
		airlineStringList = config.getProperty("airlines");
	}
	
	public ArrayList<String> generateList(){
		
		StringTokenizer st = new StringTokenizer(airlineStringList,",");
		while (st.hasMoreTokens()){
			airlineList.add(st.nextToken());
		}
		
		
		return airlineList;
	}
	

}
