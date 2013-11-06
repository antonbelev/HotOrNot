package com.google.gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConsumeJ {
	public static void main(String [ ] args) throws IOException
	{
		try(Reader reader = new InputStreamReader(ConsumeJ.class.getResourceAsStream("/resources/Output.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            Person p = gson.fromJson(reader, Person.class);
            System.out.println(p);
        }
	}

}
