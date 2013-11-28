package com.google.gson;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.google.gson.stream.JsonReader;

public class VenuesGenerator {
	public static void main(String[] args) // throws IOException
	{

		String body = "name,city,type\n";
		try {

			Scanner scFile = new Scanner(
					new FileReader(
							"C:/Users/antonbelev/Desktop/JSON_scripts/Gson/src/com/google/gson/glasgow.venues.json"));
			while (scFile.hasNextLine()) {
				String line = "";
				InputStream is = new ByteArrayInputStream(scFile.nextLine()
						.getBytes());
				JsonReader reader = new JsonReader(new InputStreamReader(is,
						"UTF-8"));
				reader.beginObject();
				while (reader.hasNext()) {
					String name = reader.nextName();
					if (name.equals("name")) {
						line += reader.nextString().replaceAll("[\n\r,]", "")
								+ ",";
					} else if (name.equals("categories")) {
						String categories = reader.nextString();
						System.out.println("categories: " + categories);
						InputStream is2 = new ByteArrayInputStream(categories.getBytes());
						JsonReader reader2 = new JsonReader(
								new InputStreamReader(is, "UTF-8"));
						while (reader2.hasNext()) {
							String nextName = reader2.nextName();
							if (nextName.equals("name")) {
								line += reader2.nextString() + "\n";
							} else {
								reader2.skipValue();
							}
						}
						is2.close();
						reader2.close();
					} else if (name.equals("location")) {
						System.out.println(reader.nextString());
						InputStream is2 = new ByteArrayInputStream(reader
								.nextString().getBytes());
						JsonReader reader2 = new JsonReader(
								new InputStreamReader(is, "UTF-8"));
						while (reader2.hasNext()) {
							String nextName = reader2.nextName();
							if (nextName.equals("name")) {
								line += reader2.nextString() + ",";
							} else {
								reader2.skipValue();
							}
						}
						is2.close();
						reader2.close();
					} else {
						reader.skipValue(); // avoid some unhandle events
					}

				}
				// line = line.replaceAll("[\n\r,]", "");

				body += line + "\n";
				reader.endObject();
				reader.close();
			}
			scFile.close();
			writeFile(body);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFile(String csvStr) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"src/resources/venues_19_11.csv"));
			out.write(csvStr);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
