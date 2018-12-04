package test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String time = String.valueOf(System.currentTimeMillis());
		System.out.println(time);
		
		String res = "";
		
		for(int i = 0; i < 7; i++) {
		    char ch = (char) ((Math.random() * 26) + 97);
			res = res+ch;
		}
		
		System.out.println(time+res);
	    System.out.println(res);
	    
	    String entity = "{\"question\": \"hi what my name\", \"qid\": \"1542781190816aapzvrz\", \"prediction\": 1}";
	    JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(entity);
		System.out.print("question : " + jsonObject.get("question").getAsString());
	}

}
