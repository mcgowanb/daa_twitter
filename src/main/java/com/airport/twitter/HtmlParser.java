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
	ArrayList<ArrayList<String>> flights = new ArrayList<ArrayList<String>>();

	public HtmlParser(String url, Properties config) {
		this.url = url;
		this.carrier = config.getProperty("carrier");

	}

	public void dataFetch() throws IOException {

		Document doc = Jsoup.connect(url).get();

		Elements table = doc.getElementsByTag("table");

		for (Element tab : table) {
			Elements tableRow = tab.getElementsByTag("tr");
			for (Element tr : tableRow) {
				if (tr.text().contains(carrier)) {
					Elements tableCol = tr.getElementsByTag("td");
					for (Element td : tableCol) {	//col in each row
						System.out.println(td + " ");
						
					}
					System.out.println();
				}
			}
		}

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
