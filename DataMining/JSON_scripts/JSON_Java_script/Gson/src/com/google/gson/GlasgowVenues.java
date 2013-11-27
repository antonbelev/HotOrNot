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

public class GlasgowVenues {
	
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
		String csvHeader = "ID User,Latitude,Longitude,City,Country\n";

		try {			
			Scanner scFile = new Scanner(
					new FileReader(
							"C:/Users/CeCeSOTI/COURSE/Gson/Gson/src/resources/glasgow.venues.json"));
			while (scFile.hasNextLine()) {

				InputStream is = new ByteArrayInputStream(scFile.nextLine().getBytes());
				JsonReader reader = new JsonReader(new InputStreamReader(is,"UTF-8"));

				reader.beginObject();

				while (reader.hasNext()) {
					String name = reader.nextName();
					if (name.equals("id")) {
						//System.out.print("ID: " + reader.nextString() + "\n");
						csvHeader += reader.nextString() + ","; 
					}
					else if (name.equals("location")) {
						reader.beginObject();
						while (reader.hasNext() == true) { 
							String names2 = reader.nextName();
							if (names2.equals("lat")) {
								//System.out.print("LATITUDE: " + reader.nextString() + "\n");
								csvHeader += reader.nextString() + ","; 
							}
							else if(names2.equals("lng")) {
								//System.out.print("LONGITUDE: " + reader.nextString() + "\n");
								csvHeader += reader.nextString() + ","; 
							}
							else if(names2.equals("city")) {
								//System.out.print("CITY: " + reader.nextString() + "\n");
								if(reader.peek() != JsonToken.NULL){
									csvHeader += reader.nextString() + ",";
								}else{
									csvHeader += "null" + ",";
									reader.skipValue();}
								 
							}
							else if(names2.equals("country")) {
								//System.out.print("COUNTRY: " + reader.nextString() + "\n\n\n");
								csvHeader += reader.nextString() + "\n"; 
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
			csvFileGenerator("Glasgow_Venues_Attributes", csvHeader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//csvFileGenerator("c:\\Glasgow_Venues_Attributes", csvHeader);

	}
}
