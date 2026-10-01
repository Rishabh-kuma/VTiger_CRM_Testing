package generic_utility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility 
{
    public static String getDataFromJsonFile(String key) throws IOException, ParseException{
      
    	//step 1> create a java rep object of the physical file
		FileReader fr = new FileReader("./src/test/resources/cd.json");
		
		//step 2> pass the jro  non static method => parse(fr) to convert to Object
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(fr);
		
		//step 3> downcast Object to JSONObject to get the value
		JSONObject jObj = (JSONObject) obj;
	
    	String value = jObj.get(key).toString();
    	return value; 
    }
    
    public static String getDataFromExcelFile(String sheetName, int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
    	
    	// Create the java rep object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigertestdata.xlsx");
		
		// get access of workbook by using WorkbookFactory
		Workbook wb = WorkbookFactory.create(fis);
		
		// get access of sheet by using getSheet() and pass the sheetname
		Sheet sh = wb.getSheet(sheetName);
		
		// get the access of row by using getRow() and pass the row index
		Row row = sh.getRow(rowIndex);
		
		// get the access of cell by using getCell() and pass the cell index
		Cell cell = row.getCell(cellIndex);
		
		// get the value from cell using getStringCellValue
		String value = cell.getStringCellValue();
	
    	return value;
    }
}
