package com.google.gson;
import java.io.BufferedWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Tweets {
	public static void csvFileGenerator(String fileName, String content) {
		try {
			File file = new File(fileName + ".csv");
			file.delete();
			System.out.println("Entered file generator");
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName
					+ ".csv"));
			out.write(content);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) // throws IOException
	{
		String csvHeader = "Text,Date,Location,Language,Followers_count,Friends_count\n";

		try {			
			Scanner scFile = new Scanner(
					new FileReader(
							"C:/Users/CeCeSOTI/COURSE/Gson/Gson/src/resources/sample.tweets"));
			while (scFile.hasNextLine()) {

				InputStream is = new ByteArrayInputStream(scFile.nextLine().getBytes());
				JsonReader reader = new JsonReader(new InputStreamReader(is,"UTF-8"));

				reader.beginObject();

				while (reader.hasNext()) {
					String name = reader.nextName();
					if (name.equals("text")) {
						csvHeader += reader.nextString() + ","; 
					}
					else if (name.equals("created_at")) {
						csvHeader += reader.nextString() + ","; 
					}
					/*else if (name.equals("place")) {
						if(reader.peek() != JsonToken.NULL){
							csvHeader += reader.nextString() + ",";
						}else{
							csvHeader += "null" + ",";
							reader.skipValue();} 
					}*/
					else if (name.equals("user")) {
						reader.beginObject();
						while (reader.hasNext() == true) { 
							String names2 = reader.nextName();
							if (names2.equals("location")) {
								csvHeader += reader.nextString() + ","; 
							}
							else if(names2.equals("lang")) {
								csvHeader += reader.nextString() + ","; 
							}
							else if(names2.equals("followers_count")) {
								//System.out.print("CITY: " + reader.nextString() + "\n");
								csvHeader += reader.nextInt() + ","; 
							}
							else if(names2.equals("friends_count")) {
								//System.out.print("COUNTRY: " + reader.nextString() + "\n\n\n");
								csvHeader += reader.nextInt() + "\n"; 
							}
							else{
								reader.skipValue();
							}
						}
						reader.endObject();
					}
					
					else {
						reader.skipValue(); // avoid some unhandle events
					}
				}
				
				reader.endObject();
				reader.close();
				
				
			}
			csvFileGenerator("Tweets_Attributes", csvHeader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	
	}
}
