package com.airport.twitter;

import java.util.ArrayList;

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

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
