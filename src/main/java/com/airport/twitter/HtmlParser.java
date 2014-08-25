package com.airport.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class HtmlParser {
	private String carrier;
	private String t1;
	private String t2;
	private ArrayList<FlightObject> flights = new ArrayList<FlightObject>();

	public HtmlParser(String url, Properties config) {
		this.carrier = config.getProperty("carrier");
		t1 = "t1";
		t2 = "t2";
		

	}

	public ArrayList<FlightObject> arrivalsFetch(String url) throws IOException {

		Document doc = Jsoup.connect(url).get();

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(carrier)) {

					FlightObject arr = new Arrivals();
					Elements tableCol = tr.getElementsByTag("td");

					for (int i = 0; i < tableCol.size(); i++) {
						Element td = tableCol.get(i);

						switch (i) {
						case 0: {
							if (td.outerHtml().contains(t1)) {
								arr.setTerminal(t1.toUpperCase());

							} else if (td.outerHtml().contains(t2)) {
								arr.setTerminal(t2.toUpperCase());
							}
							break;
						}
						case 1: {
							((Arrivals) arr).setArrivFrom(td.text());
							break;
						}
						case 2: {
							arr.setAirline(td.text());
							break;
						}
						case 3: {
							arr.setFlightNo(td.text());
							break;
						}
						case 4: {
							arr.setDate(td.text());
							break;
						}
						case 5: {
							if (td.text().isEmpty())
								;
							else
								arr.setStatus(td.text());
							break;
						}
						}
					}
					flights.add(arr);
				}
			}
		}
		return flights;
	}
	
	public ArrayList<FlightObject> departuresFetch(String url) throws IOException {

		Document doc = Jsoup.connect(url).get();

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(carrier)) {

					FlightObject dep = new Departures();
					Elements tableCol = tr.getElementsByTag("td");

					for (int i = 0; i < tableCol.size(); i++) {
						Element td = tableCol.get(i);

						switch (i) {
						case 0: {
							if (td.outerHtml().contains(t1)) {
								dep.setTerminal(t1.toUpperCase());

							} else if (td.outerHtml().contains(t2)) {
								dep.setTerminal(t2.toUpperCase());
							}
							break;
						}
						case 1: {
							((Departures) dep).setDepartTo(td.text());
							break;
						}
						case 2: {
							dep.setAirline(td.text());
							break;
						}
						case 3: {
							dep.setFlightNo(td.text());
							break;
						}
						case 4: {
							dep.setDate(td.text());
							break;
						}
						case 5: {
							if (td.text().isEmpty())
								;
							else
								dep.setStatus(td.text());
							break;
						}
						}
					}
					flights.add(dep);
				}
			}
		}
		return flights;
	}
	
	

}
