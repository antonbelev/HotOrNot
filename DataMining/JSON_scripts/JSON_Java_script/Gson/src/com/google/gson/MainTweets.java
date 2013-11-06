package com.google.gson;

import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class MainTweets {
	public static void main(String [ ] args) //throws IOException
	{
			 
		     try {
			JsonReader reader = new JsonReader(new FileReader("C:/Users/CeCeSOTI/COURSE/Gson/Gson/output.json"));
		 
			reader.beginObject();
		 
			while (reader.hasNext() == true) {
				 String name = reader.nextName(); 
			     if (name.equals("NAME")) {
			    	 System.out.println(reader.nextString());
			    	 }  
			     else if (name.equals("P_LANGUAGE")) {
			    	 System.out.println(reader.nextString());
			    	 } 
			     /*else if (name.equals("LOCATION")) {
			    	 System.out.println(reader.nextString());
			    	 } */ 
			     else if (name.equals("EXAM")) {
			    	 // read array
			    	 //System.out.println("TUK!!!");
			    	 reader.beginObject();
			    	 while (reader.hasNext() == true) {
			    		 String names2 = reader.nextName();
			    		 if (names2.equals("SUBJECT")) {
					    	 System.out.println(reader.nextString());
					    	 }
			    		 else if (names2.equals("GRADE")) {
					    	 System.out.println(reader.nextInt());
					    	 }
			    		 else if(names2.equals("USPEH")){
			    			 //System.out.println("zashtoooo");
					    	 reader.beginArray();
					    	 while (reader.hasNext()) {
					    		 System.out.println(reader.nextString());
					    		 }
					    	 reader.endArray();
					    	 }
			    		 else {
					    	 reader.skipValue(); //avoid some unhandle events
					    	 }
			    		 }
			    	 reader.endObject();
			    	 } 
			     else {
			    	 reader.skipValue(); //avoid some unhandle events
			    	 }
			     }
		 
			reader.endObject();
			reader.close();
			} 
		     catch (FileNotFoundException e) {
		    	 e.printStackTrace();
		    	 }
		     catch (IOException e) {
		    	 e.printStackTrace();
		    	 }
		 
		
		
		/*try(Reader reader = new InputStreamReader(MainTweets.class.getResourceAsStream("/resources/glasgow.venues.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            TweetJson t = gson.fromJson(reader, TweetJson.class);
            System.out.println(t);
        }*/
	}

}