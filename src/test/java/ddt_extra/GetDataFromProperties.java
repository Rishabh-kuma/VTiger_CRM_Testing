package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromProperties {

	public static void main(String[] args) throws IOException{
		
		// Step 1: Create the java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		
		// Step 2: load all the keys by using mom static method load(fis)
		Properties pObj = new Properties();
		pObj.load(fis);
		
		// Step 3: get the value by using getproperty() and pass the key in double quote " "
		String browser = pObj.getProperty("bro");
		System.out.println(browser);
		
		// don't forget to close the browser
		fis.close();

	}

}
