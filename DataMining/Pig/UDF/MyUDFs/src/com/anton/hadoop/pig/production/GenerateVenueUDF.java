package com.anton.hadoop.pig.production;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.UDFContext;

public class GenerateVenueUDF extends EvalFunc<String> {
	private String regex;
	private static Pattern p;
	
//	public GenerateVenueUDF() throws IOException{
//		String fileName = "venues_regex.txt";	
//		FileSystem fs = FileSystem.get(UDFContext.getUDFContext().getJobConf());
//		FSDataInputStream in = fs.open(new Path(fileName));
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//		regex = br.readLine(); // should be one line only !!!
//		p = Pattern.compile(regex);
//	}

	@Override
	public String exec(Tuple tuple) throws IOException {

		if (tuple == null) {
			throw new IllegalArgumentException(
					"BagTupleExampleUDF: requires at least one input parameter.");
		}
		try {
			String fileName = "venues_regex.txt";	
			FileSystem fs = FileSystem.get(UDFContext.getUDFContext().getJobConf());
			FSDataInputStream in = fs.open(new Path(fileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			regex = br.readLine(); // should be one line only !!!
			p = Pattern.compile(regex);
			String tweet = (String) tuple.get(0);
//			TupleFactory tf = TupleFactory.getInstance();
//			BagFactory mBagFactory = BagFactory.getInstance();
//			Tuple t = tf.newTuple();
//			t.append(tweet);
//			t.append(checkVenue(tweet));
//			DataBag output = mBagFactory.newDefaultBag();
//			output.add(t);
			return checkVenue(tweet);
		} catch (Exception e) {
			throw new IOException(
					"BagTupleExampleUDF: caught exception processing input.", e);
		}
	}

	public static String checkVenue(String tweet) {
		Matcher m = p.matcher(tweet);
		if (m.find()) {
			return m.group(1);
		} else {
			return "";
		}
	}

}

