package com.airport.twitter;

import java.io.IOException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	private String url;
	private String carrier;

	public HtmlParser(String url, Properties config) {
		this.url = url;
		this.carrier = config.getProperty("carrier");

	}

	public void urlProcess() throws IOException {

		Document doc = Jsoup.connect(url).get();
		Elements tr = doc.getElementsByTag("tr");

		for (Element t : tr) {
			if (t.text().contains(carrier)){
				Elements td = t.getElementsByTag("td");
				for(Element x : td){
					System.out.print(x.text());
				}
				System.out.println();
			}
		}

	}
}
