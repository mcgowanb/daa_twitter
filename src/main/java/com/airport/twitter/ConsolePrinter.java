package com.airport.twitter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class ConsolePrinter {

	public static void printObjecList(ArrayList<FlightObject> list) {
		for (FlightObject fo : list) {
			System.out.println(fo);
		}
	}

	public static void printStringList(ArrayList<String> list) {
		for (String str : list) {
			System.out.println(str);
		}

	}
	
	public static void printMap(Map<String, String> list){
		for (Entry<String, String> entry : list.entrySet())
		{
		    System.out.println(entry.getKey() + " \t/\t " + entry.getValue());
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
	
	public static void insertGap(){
		System.out.println();
		System.out.println("===GAP===");
		System.out.println();
	}

}
