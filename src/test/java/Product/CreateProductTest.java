package Product;

import java.io.IOException;
import java.time.Duration;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

/**
 * Test Class: CreateProductTest
 *
 * Description:
 * This automation script verifies the complete flow of creating a new
 * Product in the Vtiger CRM application.
 *
 * Test Flow:
 * 1. Launch Chrome browser
 * 2. Maximize browser window
 * 3. Configure implicit wait
 * 4. Open Vtiger application
 * 5. Login using valid credentials
 * 6. Navigate to Products module
 * 7. Open Create Product page
 * 8. Enter Product Name
 * 9. Save the Product
 * 10. Verify the created Product
 * 11. Logout from Vtiger
 * 12. Close the browser
 *
 * Test Data:
 * Product Name: E-commerce
 *
 * Expected Result:
 * Product should be created successfully with the entered Product Name
 * and the user should be able to logout successfully.
 *
 * Author: Rishabh
 * Application: Vtiger CRM
 * Test Type: Functional / UI Automation
 */

public class CreateProductTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Product");

		//get data from json file

		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();
		
		System.out.println("==============================================");
		System.out.println("       CREATE PRODUCTS TEST STARTED       ");
		System.out.println("==============================================");
	
		// ============================================================
		// STEP 1: OPEN THE BROWSER
		// ============================================================

		System.out.println("[INFO] Launching Chrome browser...");
		
		WebDriver driver = null;
		if (browser.equals("chrome")) { 
			driver = new ChromeDriver();
			System.out.println("Chrome browser oppenned successfully");
		}
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
			System.out.println("Edge browser oppenned successfully");
			}
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
			System.out.println("FireFox browser oppenned successfully");
			}
		else {
			driver = new ChromeDriver();
			System.out.println("Default browser oppenned successfully");
		}
		
		System.out.println("[INFO] Maximizing browser window...");
		driver.manage().window().maximize();

		System.out.println("[INFO] Configuring implicit wait: 15 seconds...");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Navigate to URL
		System.out.println("[INFO] Navigating to Vtiger CRM application...");
		driver.get(url);
		System.out.println("[INFO] Application launched successfully.");


		// ============================================================
		// STEP 2: LOGIN
		// ============================================================

		System.out.println("Step 2: Starting login process...");

		System.out.println("Vtiger application launched successfully.");

		WebElement usernameField = driver.findElement(By.name("user_name"));
		WebElement passwordField = driver.findElement(By.name("user_password"));

		System.out.println("Username and password fields located successfully.");

		usernameField.sendKeys(username);

		System.out.println("Username entered successfully.");

		passwordField.sendKeys(password + Keys.ENTER);

		System.out.println("Password entered and login submitted.");

		System.out.println("Login process completed.");

		// ============================================================
		// STEP 3: CREATE PRODUCT
		// ============================================================

		System.out.println("Step 3: Navigating to Products module...");

		driver.findElement(By.linkText("Products")).click();

		System.out.println("Products module opened successfully.");

		driver.findElement(By.cssSelector("img[alt = 'Create Product...']")).click();

		System.out.println("Create Product page opened successfully.");


		// Get data from xlsx file
		
		String productName = FileUtility.getDataFromExcelFile("Products", 0, 0);
		String saleStart = FileUtility.getDataFromExcelFile("Products", 0, 0);
		String saleEnd = FileUtility.getDataFromExcelFile("Products", 0, 0);
		String productCatValue = FileUtility.getDataFromExcelFile("Products", 0, 0);
		
		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		
		// ============================================================
		// STEP 4: FILLING PRODUCT FORM
		// ============================================================

		// Product name
		
		System.out.println("Step 4: Filling Product form...");

		System.out.println("Product Name to be entered: " + productName);

		WebElement productNameField = driver.findElement(By.name("productname"));

		System.out.println("Product Name field located successfully.");

		productNameField.sendKeys(productName);

		System.out.println("Product Name entered successfully.");

		// Product Sales Start Date
		
		System.out.println("Filling Product Sales Start Date");
		
		WebElement saleStartField = driver.findElement(By.name("sales_start_date"));
		
		System.out.println("Sales Start Date located successfully");
		
		System.out.println("Entering Sales Start Date: " + saleStart);
		
		saleStartField.sendKeys(saleStart);
		
		System.out.println("Sales Start Date Entered successfully");
		
		// ------------------------------------------------------------
		// Select Product Category from Related To dropdown
		// ------------------------------------------------------------
		
		System.out.println("Selecting Product Category from Related To...");
		
		WebElement relatedToProCat = driver.findElement(By.name("productcategory"));
		
		wdUtil.select(productCatValue, relatedToProCat);
		
		System.out.println("Product Category selected sucessfully");
		
		// Product Sales End Date
		
		System.out.println("Filling Product Sales End Date");
		
		System.out.println("Locating Sales End Date Field");
		
		WebElement salesEndField = driver.findElement(By.name("sales_end_date"));
				
		salesEndField.sendKeys(saleEnd);
		
		System.out.println("Sales end date entered successfully");
		
		// ============================================================
		// STEP 5: SAVE PRODUCT
		// ============================================================

		System.out.println("Step 5: Saving Product...");

		Thread.sleep(2000);

		System.out.println("Wait completed. Clicking Save button...");

		driver.findElement(By.cssSelector("input[title = 'Save [Alt+S]']")).click();

		System.out.println("Save button clicked successfully.");

		System.out.println("Product save operation completed.");


		// ============================================================
		// STEP 6: VERIFICATION
		// ============================================================

		System.out.println("Step 6: Verifying Product creation...");

		String actProductName = driver.findElement(By.id("dtlview_Product Name")).getText();

		System.out.println("Expected Product Name: " + productName);

		System.out.println("Actual Product Name: " + actProductName);


		// ------------------------------------------------------------
		// Verify Product Name
		// ------------------------------------------------------------

		if (actProductName.equals(productName)) {

			System.out.println("PASS: Product created successfully...");

		} else {

			System.out.println("FAIL: Product Creation failed...");
		}


		// ============================================================
		// STEP 7: LOGOUT
		// ============================================================

		System.out.println("Step 7: Starting logout process...");

		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		System.out.println("Profile icon located successfully.");

		System.out.println("Actions object created successfully.");

		wdUtil.hover(profile);

		System.out.println("Mouse hovered over profile icon.");

		driver.findElement(By.linkText("Sign Out")).click();

		System.out.println("Sign Out option clicked successfully.");

		System.out.println("Logout process completed.");


		// ============================================================
		// STEP 8: CLOSE THE BROWSER
		// ============================================================

		System.out.println("Step 8: Closing the browser...");

		Thread.sleep(2000);

		System.out.println("Wait completed.");

		driver.quit();

		System.out.println("Browser closed successfully.");


		// ============================================================
		// TEST EXECUTION END
		// ============================================================

		System.out.println("========== TEST EXECUTION COMPLETED ==========");

	}

}