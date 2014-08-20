package com.airport.twitter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws IOException {
		App app = new App();

		String filePath = "/" + args[0];
		Properties config = new Properties();
		config.load(App.class.getResourceAsStream(filePath));
		
		Arrivals arr = new Arrivals(config);
		arr.gatherData();

		app.processArrivals(config);

	}

	public void processArrivals(Properties config) throws IOException {
		String url = config.getProperty("arrivals_url");
		HtmlParser parser = new HtmlParser(url,config);
		parser.urlProcess();

	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
