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

public class VenuesGeneratorNew {

	/*
	 * public static void csvFileGenerator(String fileName, String content) {
	 * try { File file = new File(fileName + ".csv"); file.delete();
	 * System.out.println("Entered file generator"); BufferedWriter out = new
	 * BufferedWriter(new FileWriter(fileName + ".csv")); out.write(content);
	 * out.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */

	public static void main(String[] args) // throws IOException
	{
		String csvHeader = "Name,Type,Latitude,Longitude,City,Country\n";
		try {
			File file = new File("src/resources/Glasgow_Venues_Attributes"
					+ ".csv");
			file.delete();
			System.out.println("Entered file generator");
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"src/resources/Glasgow_Venues_Attributes" + ".csv"));
			out.write(csvHeader);

			try {
				Scanner scFile = new Scanner(
						new FileReader(
								"C:/Users/antonbelev/Desktop/JSON_scripts/Gson/src/com/google/gson/glasgow.venues.json"));
				String[] line = new String[6];
				String outputLine = "";
				while (scFile.hasNextLine()) {

					InputStream is = new ByteArrayInputStream(scFile.nextLine()
							.getBytes());
					JsonReader reader = new JsonReader(new InputStreamReader(
							is, "UTF-8"));

					reader.beginObject();
					while (reader.hasNext()) {
						String name = reader.nextName();
						if (name.equals("name")) {
							line[0] = reader.nextString().replaceAll("[\n\r,:/;\"\'@?#$!^&*]", "")
									.toLowerCase();
						} else if (name.equals("categories")) {
							reader.beginArray();							
							while (reader.hasNext() == true) {
								reader.beginObject();
								while (reader.hasNext() == true) {
									String names2 = reader.nextName();
									if (names2.equals("name")) {
										line[1] = reader.nextString().replaceAll("[\n\r,:/;\"\'@?#$!^&*]", "")
												.toLowerCase();;
									} else {
										reader.skipValue();
									}
								}
								reader.endObject();
							}
							reader.endArray();
							//
						} else if (name.equals("location")) {
							reader.beginObject();
							while (reader.hasNext() == true) {
								String names2 = reader.nextName();
								if (names2.equals("lat")) {
									line[2] = reader.nextString();
								} else if (names2.equals("lng")) {
									line[3] = reader.nextString();

								} else if (names2.equals("city")) {
									line[4] = reader.nextString().replaceAll("[\n\r,:/;\"\'@?#$!^&*]", "")
											.toLowerCase();;

								} else if (names2.equals("country")) {
									line[5] = reader.nextString().replaceAll("[\n\r,:/;\"\'@?#$!^&*]", "")
											.toLowerCase();;

								} else {
									reader.skipValue();
								}
							}
							reader.endObject();
						}else {
							reader.skipValue(); // avoid some unhandle events
						}
					}
					for (int j = 0; j < line.length; j++) {
						if (j != line.length - 1)
							outputLine += (line[j] == null ? "" : line[j])
									+ ",";

						else
							outputLine += (line[j] == null ? "" : line[j]);
					}
					outputLine += '\n';
					out.write(outputLine);
					line = new String[6];
					outputLine = "";

					reader.endObject();
					reader.close();
				}
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// csvFileGenerator("c:\\Glasgow_Venues_Attributes", csvHeader);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
