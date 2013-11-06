package com.google.gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.GsonBuilder;

public class MainClass {
	public static void main(String [ ] args) throws IOException
	{
		 try(Writer writer = new OutputStreamWriter(new FileOutputStream("Output.json") , "UTF-8")){
	            Gson gson = new GsonBuilder().create();
	            String name= "NAME";
	            String valueNAME = "Albert Attard";
	            String location= "LOCATION";
	            String valueLCT = "Malta";
	            // JsonPrimitive JvalueNAME = new JsonPrimitive(valueNAME);
	            // JsonPrimitive JvalueLCT = new JsonPrimitive(valueLCT);
	            JsonObject person = new JsonObject();
	            person.addProperty(name, valueNAME);
	            person.addProperty(location, valueLCT);
	            
	            gson.toJson(person, writer);
	            
	        }
	}
	
}
