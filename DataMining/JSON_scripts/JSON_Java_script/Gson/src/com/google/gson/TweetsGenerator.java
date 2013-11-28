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

import javax.swing.plaf.SliderUI;

import com.google.gson.stream.JsonReader;

public class TweetsGenerator {
	
	public static void main(String[] args) // throws IOException
	{

		String body = "text,dayofweek,month,day,time,zone,year\n";
		try {
			int countLine = 0;
			Scanner scFile = new Scanner(
					new FileReader(
							"C:/Users/antonbelev/Desktop/JSON_scripts/Gson/src/com/google/gson/sample.tweets"));
			while (scFile.hasNextLine()) {
				String line = "";
				InputStream is = new ByteArrayInputStream(scFile.nextLine()
						.getBytes());
				JsonReader reader = new JsonReader(new InputStreamReader(is,
						"UTF-8"));
				reader.setLenient(false);
				reader.beginObject();
				countLine++;
				while (reader.hasNext()) {
					
					String name = reader.nextName();
					
					if (name.equals("text")) {
						line += reader.nextString().replaceAll("[\n\r,:/;\"\'#$!^&*]", "")
								+ ",";
						System.out.println("Line " + countLine + " " + name);
					} else if (name.equals("created_at")) {
						System.out.println("Line " + countLine + " " + name);
						//line += reader.nextString() + ",";
						String dayofweek, month, day,time,zone,year = "";
						String ts = reader.nextString();
						//System.out.println("ts = " + ts + "count + " + count++);
						dayofweek = ts.substring(0, 3);

						month = ts.substring(4,7);
						day = ts.substring(8, 10);
						time = ts.substring(11, 19);
						zone = ts.substring(20, 25);
						year = ts.substring(26);
						line += dayofweek + "," + month + "," + day + "," + time + "," + zone + "," + year;
						
					}/* else if (name.equals("user")) {
						
						JsonParser jsonParser = new JsonParser();
						String userStr = reader.nextString();
						System.out.println(reader.nextString());
						JsonElement user = jsonParser.parse(userStr);						
						JsonObject userObject = user.getAsJsonObject();
						
						line += userObject.get("location");
					}*/ else {
						reader.skipValue(); // avoid some unhandle events
					}

				}
				// line = line.replaceAll("[\n\r,]", "");
				//System.out.println("\n\n\n\n");

				body += line + "\n";
				reader.endObject();
				reader.close();
			}
			//System.out.println("ts " + countTs + "text " + countText);
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
					"src/resources/tweets_19_11.csv"));
			out.write(csvStr);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
