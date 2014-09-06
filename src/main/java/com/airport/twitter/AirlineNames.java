package com.airport.twitter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AirlineNames {
	private Document doc;

	public AirlineNames(Document doc) {
		this.doc = doc;
	}

	public List<String> generateAirlineNames() {
		List<String> list = new ArrayList<String>();
		Elements airlineListOfNames = doc.select("select[name=ctl00$Content$ctl00$ddlAirline]");
		for(Element e : airlineListOfNames){
			Elements airlineNames = e.getElementsByTag("option");
			for(int i = 1; i < airlineNames.size(); i++){
				list.add(airlineNames.get(i).text());
			}
		}
		return list;
	}

	public Object getDoc() {
		// TODO Auto-generated method stub
		return doc;
	}

}
