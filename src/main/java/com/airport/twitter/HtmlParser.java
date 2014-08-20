package com.airport.twitter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	private String url;

	public HtmlParser(String url) {
		this.url = url;

	}

	public void urlProcess() throws IOException {

		Document doc = Jsoup.connect(url).get();
		Elements tr = doc.getElementsByTag("tr");
		
		for (Element t : tr){
			System.out.println(t);
		}

	}

}
