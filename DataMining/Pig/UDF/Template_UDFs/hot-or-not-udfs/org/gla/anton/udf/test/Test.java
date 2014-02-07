package org.gla.anton.udf.test;

import java.util.regex.*;

class Test {
	public static void main(String[] args) throws java.lang.Exception {
		checkVenue("Test Venue1 test test");
		checkVenue("Test Venue2 test test");
		checkVenue("Test Venue3 test test");
		checkVenue("Test Venue1 fgdgdfgd Venue1 asdasd Venue3 asdjioo Venue2 dasf test");
	}

	public static void checkVenue(String tweet) {
		Pattern p = Pattern.compile("(.*)(Venue1||Venue2|Venue3)(.*)");
		Matcher m = p.matcher(tweet);
		System.out.print(tweet + ":\t ");
		while (m.find()) {
			System.out.println("found " + m.group(3));
		}
	}
}