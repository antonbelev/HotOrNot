package org.gla.anton.udf.test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.pig.FilterFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;

/**
 * SQL IN for Pig.
 *
 * Example Filter function: returns Boolean, can only be used in a FILTER statement.
 *
 * Ex:
 *
 * DEFINE VenuesRegex org.gla.anton.udf.main.VenuesRegex('3,5,7');
 *
 * FILTER my_relation by VenuesRegex(first_field);
 */
public class VenuesRegex extends FilterFunc {

	 	private static final String ALLCHARS = "(.*)";

	    private String regex;

	    public VenuesRegex(DataBag venuesBag) {
	        Iterator<Tuple> it = venuesBag.iterator();
	        String current = "";
	        while (it.hasNext()){
	        	Tuple t = it.next();
	        	try {
					current = "(" + ALLCHARS + t.get(0) + ALLCHARS + ")";
				} catch (ExecException e) {
					throw new IllegalArgumentException("VenuesRegex: requires tuple with at least one value");
				}
	        	regex += current + (it.hasNext() ? "|" : "");
	        }
	    }

	    @Override
	    public Boolean exec(Tuple input) throws IOException {
	        if (input == null || input.size() != 1) {
	            throw new IllegalArgumentException("VenuesRegex: requires one input parameter.");
	        }
	        try {
	            String value = (String) input.get(0);
	            return value.matches(regex);
	        }
	        catch (ExecException e) {
	            throw new IOException("VenuesRegex: caught exception processing input.", e);
	        }
	    }
}