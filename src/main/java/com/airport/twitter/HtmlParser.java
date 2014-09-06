package com.airport.twitter;

import java.io.IOException;
import java.util.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	private String airline;
	private String t1 = "t1";
	private String t2 = "t2";
	private String hashTag = "#DAA";
	private List<FlightObject> flights = new ArrayList<FlightObject>();

	public HtmlParser(String airline) {
		this.airline = airline;
	}

	public List<FlightObject> arrivalsFetch(Document doc) throws IOException {

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(airline)) {

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
					arr.setHashTag(hashTag);
					flights.add(arr);
				}
			}
		}
		return flights;
	}

	public List<FlightObject> departuresFetch(Document doc) throws IOException {

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(airline)) {

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
					dep.setHashTag(hashTag);
					flights.add(dep);
				}
			}
		}
		return flights;
	}

	public List<FlightObject> removeIncompleteFlights(List<FlightObject> list) {
		for (Iterator<FlightObject> iter = list.iterator(); iter.hasNext();) {
			FlightObject fo = iter.next();
			if (fo.status == null) {
				iter.remove();
				continue;
			}
			if (fo.status.contains("Delayed") || fo.status.contains("Due")) {
				iter.remove();
			}

		}
		Collections.sort(list, FlightObject.SORT_BY_DATE);
		return list;
	}

	public String getAirline() {
		return this.airline;
	}
}
