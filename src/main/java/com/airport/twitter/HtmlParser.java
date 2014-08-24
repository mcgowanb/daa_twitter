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
	private String url;
	private String carrier;
	private String t1;
	private String t2;
	private boolean isArrivals;
	private ArrayList<FlightObject> flights = new ArrayList<FlightObject>();

	public HtmlParser(String url, Properties config) {
		this.url = url;
		this.carrier = config.getProperty("carrier");
		t1 = "t1";
		t2 = "t2";
		

	}

	public ArrayList<FlightObject> dataFetch() throws IOException {

		Document doc = Jsoup.connect(url).get();

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(carrier)) {

					FlightObject fo = new FlightObject();
					Elements tableCol = tr.getElementsByTag("td");

					for (int i = 0; i < tableCol.size(); i++) {
						Element td = tableCol.get(i);

						switch (i) {
						case 0: {
							if (td.outerHtml().contains(t1)) {
								fo.setTerminal(t1.toUpperCase());

							} else if (td.outerHtml().contains(t2)) {
								fo.setTerminal(t2.toUpperCase());
							}
							break;
						}
						case 1: {
							fo.setArrivFrom(td.text());
							break;
						}
						case 2: {
							fo.setAirline(td.text());
							break;
						}
						case 3: {
							fo.setFlightNo(td.text());
							break;
						}
						case 4: {
							fo.setDate(td.text());
							break;
						}
						case 5: {
							if (td.text().isEmpty())
								;
							else
								fo.setStatus(td.text());
							break;
						}
						}
					}
					flights.add(fo);
				}
			}
		}
		return flights;
	}

	public void sampleProcess() throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements tr = doc.getElementsByTag("tbody");

		for (Element t : tr) {
			int i = 0;
			for (Node node : t.childNodes()) {
				i++;
				System.out.println(String.format("%d %s %s", i, node.getClass()
						.getSimpleName(), node.toString()));
				// System.out.println(node.toString());
			}

		}

	}
}
