package ddt_extra;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetDataFromJSONFile {

	public static void main(String[] args) throws IOException, ParseException {
		
		// Step 1 : Create a java rep onject of the physcial file
		FileReader fr = new FileReader("./src/test/resources/cd.json");
		
		// Step 2: Pass the jro non static method => parse(fr) to convert to object
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(fr);
		
		// Step 3: Downcast Object to JSONObject  to get the value
		JSONObject jObj = (JSONObject)obj;
		
		// Step 4: by using get() and parsing the key get the value
		String username = jObj.get("usernme").toString();
		String password = jObj.get("password").toString();
		
		System.out.println(username);
		System.out.println(password);

	}

}
