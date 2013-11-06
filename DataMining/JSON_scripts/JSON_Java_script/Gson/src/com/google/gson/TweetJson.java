package com.google.gson;
import java.util.ArrayList;
import java.util.List;

public class TweetJson {
	
	private List<String> tweetsFilters = new ArrayList<>();
	public void addTweetFilter(String filter){
		tweetsFilters.add(filter);
	}
	
	@Override
	 public String toString() {
	   return tweetsFilters.toString();
	 }
	

}
