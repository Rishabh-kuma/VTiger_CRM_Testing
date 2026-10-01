package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Create the java rep object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigertestdata.xlsx");
		
		// get access of workbook by using WorkbookFactory
		Workbook wb = WorkbookFactory.create(fis);
		
		// get access of sheet by using getSheet() and pass the sheetname
		Sheet sh = wb.getSheet("Leads");
		
		// get the access of row by using getRow() and pass the row index
		Row row = sh.getRow(1);
		
		// get the access of cell by using getCell() and pass the cell index
		Cell cell = row.getCell(0);
		
		// get the value from cell using getStringCellValue
		String fName = cell.getStringCellValue();
		
		System.out.println(fName);
		
		// always close the file
		fis.close();
		wb.close();
		
	}

}
