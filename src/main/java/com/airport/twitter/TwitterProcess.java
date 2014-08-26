package com.airport.twitter;

import java.util.Properties;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterProcess {
	
	private TwitterFactory tf;
	private String status;
	
	public TwitterProcess(String status){
		this.status = status;
	}
	
	public void initialiseArrivals(Properties config){
		
	ConfigurationBuilder cb = new ConfigurationBuilder();
	cb.setDebugEnabled(true)
	  .setOAuthConsumerKey(config.getProperty("arrival.oauth.consumerKey"))
	  .setOAuthConsumerSecret(config.getProperty("arrival.oauth.consumerSecret"))
	  .setOAuthAccessToken(config.getProperty("arrival.oauth.accessToken"))
	  .setOAuthAccessTokenSecret(config.getProperty("arrival.oauth.accessTokenSecret"));
	this.tf = new TwitterFactory(cb.build());
	}
	
	public void initialiseDepartures(Properties config){
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(config.getProperty("departures.oauth.consumerKey"))
		  .setOAuthConsumerSecret(config.getProperty("departures.oauth.consumerSecret"))
		  .setOAuthAccessToken(config.getProperty("departures.oauth.accessToken"))
		  .setOAuthAccessTokenSecret(config.getProperty("departures.oauth.accessTokenSecret"));
		this.tf = new TwitterFactory(cb.build());
		}
	
	public void postToTwitter(){
		Twitter twitter = tf.getInstance();
	    Status result = null;
		try {
			result = twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Successfully updated twitter status to [" + result.getText() + "].");
	}
	
	
}
