package org.gla.anton.udf.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class GenerateVenues extends EvalFunc<Tuple> {

	TupleFactory mTupleFactory = TupleFactory.getInstance();
	BagFactory mBagFactory = BagFactory.getInstance();
	
	private static final String ALLCHARS = "(.*)";
	private ArrayList<String> venues;

    private String regex;

    public GenerateVenues(DataBag venuesBag) {
        Iterator<Tuple> it = venuesBag.iterator();
        venues = new ArrayList<String>((int) (venuesBag.size() + 1)); // possible fails!!!
        String current = "";
        regex = "";
        while (it.hasNext()){
        	Tuple t = it.next();
        	try {
				current = "(" + ALLCHARS + t.get(0) + ALLCHARS + ")";
				venues.add((String) t.get(0));
			} catch (ExecException e) {
				throw new IllegalArgumentException("VenuesRegex: requires tuple with at least one value");
			}
        	regex += current + (it.hasNext() ? "|" : "");
        }
    }

	@Override
	public Tuple exec(Tuple tuple) throws IOException {
		// expect one string
		if (tuple == null || tuple.size() != 2) {
			throw new IllegalArgumentException(
					"BagTupleExampleUDF: requires two input parameters.");
		}
		try {
			String tweet = (String) tuple.get(0);
			for (String venue: venues)
			{
				if (tweet.matches(ALLCHARS + venue + ALLCHARS))
				{
					Tuple output = mTupleFactory.newTuple(Collections.singletonList(venue));
					return output;
				}
			}
			return null;
		} catch (Exception e) {
			throw new IOException(
					"BagTupleExampleUDF: caught exception processing input.", e);
		}
	}

	@Override
	public List<String> getCacheFiles() { 
        List<String> list = new ArrayList<String>(1); 
        list.add("/user/pig/tests/data/small#smallfile"); 
        return list; 
    } 
}
