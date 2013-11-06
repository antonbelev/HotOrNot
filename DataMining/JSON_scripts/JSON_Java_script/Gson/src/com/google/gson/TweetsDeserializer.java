package com.google.gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

public class TweetsDeserializer implements JsonDeserializer<TweetJson> {
	
	 public List<Message> readJsonStream(InputStream in) throws IOException {
	        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
	        List<Message> messages = new ArrayList<Message>();
	        reader.beginArray();
	        while (reader.hasNext()) {
	            Message message = gson.fromJson(reader, Message.class);
	            messages.add(message);
	        }
	        reader.endArray();
	        reader.close();
	        return messages;
	    }

  /*@Override
  public TweetJson deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {

    TweetJson books = new TweetJson();
    // Parsing will be done here.
    return books;
  }*/
}

