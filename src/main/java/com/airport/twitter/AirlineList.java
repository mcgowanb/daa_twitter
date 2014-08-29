package com.airport.twitter;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AirlineList {
	private Document doc;

	public AirlineList(Document doc) {
		this.doc = doc;
	}

	public ArrayList<String> generateAirlineNames() {
		ArrayList<String> list = new ArrayList<String>();
		Elements option = doc.select("select[name=ctl00$Content$ctl00$ddlAirline]");
		for(Element e : option){
			Elements index = e.getElementsByTag("option");
			for(int i = 2; i < index.size(); i++){
				list.add(index.get(i).text());
			}
		}
		return list;
	}

}
