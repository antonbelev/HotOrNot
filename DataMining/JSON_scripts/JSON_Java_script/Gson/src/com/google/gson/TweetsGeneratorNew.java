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

public class TweetsGeneratorNew {
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
		String csvHeader = "Text,WeekDay,Month,Day,Time,+SMT,Year,Location,Language,Followers_count,Friends_count\n";
		try {
			File file = new File("Tweets_Attributes" + ".csv");
			file.delete();
			System.out.println("Entered file generator");
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"src/resources/Tweets_Attributes" + ".csv"));
			out.write(csvHeader);

			try {
				Scanner scFile = new Scanner(
						new FileReader(
								"C:/Users/antonbelev/Desktop/JSON_scripts/Gson/src/com/google/gson/sample.tweets"));
				String[] line = new String[11];
				String outputLine = "";
				while (scFile.hasNextLine()) {
					InputStream is = new ByteArrayInputStream(scFile.nextLine()
							.getBytes());
					JsonReader reader = new JsonReader(new InputStreamReader(
							is, "UTF-8"));
					reader.beginObject();
					while (reader.hasNext()) {						
						String name = reader.nextName();
						if (name.equals("text")) {
							line[0] = reader.nextString()
									.replaceAll("[\n\r,:/;\"\'()@?#$!^&*]", "")
									.toLowerCase();
						} 
						else if (name.equals("created_at")) {
							String[] splited = reader.nextString().split(" ");
							int i = 1;
							for (String s : splited) {
								line[i] = s.toLowerCase();
								i++;
							}
						}
						else if (name.equals("user")) {
							reader.beginObject();
							while (reader.hasNext() == true) {
								String names2 = reader.nextName();
								if (names2.equals("location")) {
									line[7] = reader.nextString().replaceAll("[\n\r,:/;\"\'@?#$!^&*]","").toLowerCase();
								} else if (names2.equals("lang")) {
									line[8] = reader.nextString().toLowerCase();
								} else if (names2.equals("followers_count")) {
									line[9] = String.valueOf(reader.nextInt());
								} else if (names2.equals("friends_count")) {
									line[10] = String.valueOf(reader.nextInt());
								} else {
									reader.skipValue();
								}
							}
							reader.endObject();
						}
						else {
							reader.skipValue(); // avoid some unhandle events
						}						
					}
					for (int j = 0; j < line.length; j++) {
						if (j != line.length)
							outputLine += (line[j] == null ? "" : line[j])
									+ ",";
						else
							outputLine += (line[j] == null ? "" : line[j]);
					}
					outputLine += '\n';					
					out.write(outputLine);
					line = new String[11];
					outputLine = "";
					reader.endObject();
					reader.close();
				}
				out.close(); // closing the csv file;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
