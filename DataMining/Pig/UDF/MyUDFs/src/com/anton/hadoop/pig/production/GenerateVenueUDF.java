package com.anton.hadoop.pig.production;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

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
		// expect one string
		Scanner sn = new Scanner(new File("/users/level3/1103816b/Desktop/HotOrNot_repo/DataMining/Pig/venues_regex.txt"));
		regex = sn.nextLine(); // should be one line only !!
		sn.close();
		p = Pattern.compile(regex);
		
		if (tuple == null) {
			throw new IllegalArgumentException(
					"BagTupleExampleUDF: requires at least one input parameter.");
		}
		try {
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

