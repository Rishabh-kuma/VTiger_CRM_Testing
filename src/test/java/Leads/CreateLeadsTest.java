package Leads;

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
 * Test Class: CreateLeadsTest
 *
 * Description:
 * This automation script verifies the complete flow of creating a new
 * Lead in the Vtiger CRM application.
 *
 * Test Flow:
 * 1. Launch Chrome browser
 * 2. Maximize browser window
 * 3. Configure implicit wait
 * 4. Open Vtiger application
 * 5. Login using valid credentials
 * 6. Navigate to Leads module
 * 7. Open Create Lead page
 * 8. Enter Lead Last Name
 * 9. Enter Company Name
 * 10. Save the Lead
 * 11. Verify the created Lead
 * 12. Logout from Vtiger
 * 13. Close the browser
 *
 * Test Data:
 * Lead Last Name: Kumarr
 * Company Name: Google
 *
 * Expected Result:
 * Lead should be created successfully with the entered Last Name
 * and Company Name, and the user should be able to logout successfully.
 *
 * Application: Vtiger CRM
 * Test Type: Functional / UI Automation
 */

public class CreateLeadsTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Leads");

		//get data from json file
		
		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();
		
		System.out.println("==============================================");
		System.out.println("       CREATE LEADS TEST STARTED       ");
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

		WebElement usernameField = driver.findElement(By.name("user_name"));
		WebElement passwordField = driver.findElement(By.name("user_password"));

		System.out.println("Username and password fields located successfully.");

		usernameField.sendKeys(username);

		System.out.println("Username entered successfully.");

		passwordField.sendKeys(password + Keys.ENTER);

		System.out.println("Password entered and login submitted.");

		System.out.println("Login process completed.");


		// ============================================================
		// STEP 3: CREATE LEAD
		// ============================================================

		System.out.println("Step 3: Navigating to Leads module...");

		driver.findElement(By.linkText("Leads")).click();

		System.out.println("Leads module opened successfully.");

		driver.findElement(By.cssSelector("img[alt='Create Lead...']")).click();

		System.out.println("Create Lead page opened successfully.");


		// ============================================================
		// STEP 4: FILLING LEAD FORM
		// ============================================================

		// Get data from xlsx file
		String lName = FileUtility.getDataFromExcelFile("Leads", 1, 1);
		String compName = FileUtility.getDataFromExcelFile("Leads", 1, 2);
		String mNumber = FileUtility.getDataFromExcelFile("Leads", 1, 10);
		String leadSourceValue = FileUtility.getDataFromExcelFile("Leads", 1, 4);
		String industryValue = FileUtility.getDataFromExcelFile("Leads", 1, 5);
		
		
		// ------------------------------------------------------------
		// Adding Last Name
		// ------------------------------------------------------------

		System.out.println("Step 4: Filling Lead form...");

		System.out.println("Lead Last Name: " + lName);

		WebElement lastNameField = driver.findElement(By.name("lastname"));

		System.out.println("Lead Last Name field located successfully.");

		lastNameField.sendKeys(lName);

		System.out.println("Lead Last Name entered successfully.");
		
		// ------------------------------------------------------------
		// Adding Company Name
		// ------------------------------------------------------------

		System.out.println("Company Name: " + compName);

		WebElement compNameField = driver.findElement(By.name("company"));

		System.out.println("Company Name field located successfully.");

		compNameField.sendKeys(compName);

		System.out.println("Company Name entered successfully.");

		// ------------------------------------------------------------
		// Select Lead Source
		// ------------------------------------------------------------

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		
		System.out.println("Selecting Lead Source...");

		WebElement leadSourceField = driver.findElement(By.name("leadsource"));

		System.out.println("Lead Source dropdown located successfully.");

		wdUtil.select(leadSourceValue, leadSourceField);

		System.out.println("Lead Source " + "'" + leadSourceValue + "'" + " selected successfully.");
		
		// ------------------------------------------------------------
		// Select Industry
		// ------------------------------------------------------------

		System.out.println("Selecting Industry...");

		WebElement industryField = driver.findElement(By.name("industry"));

		System.out.println("Industry dropdown located successfully.");
		
		wdUtil.select(industryValue, industryField);

		System.out.println("Industy " + "'" + industryValue + "'" + " selected successfully.");
		
		// ------------------------------------------------------------
		// Adding Mobile Number
		// ------------------------------------------------------------

		System.out.println("Selecting Mobile...");

		System.out.println("Mobile Number to be entered: " + mNumber);

		WebElement mNumberField = driver.findElement(By.id("mobile"));

		System.out.println("Mobile field located successfully.");

		mNumberField.sendKeys(mNumber);

		System.out.println("Mobile Number entered successfully.");


		// ============================================================
		// STEP 5: SAVE LEAD
		// ============================================================

		System.out.println("Step 5: Saving Lead...");

		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		System.out.println("Save button clicked successfully.");

		System.out.println("Lead save operation completed.");


		// ============================================================
		// STEP 6: VERIFICATION
		// ============================================================

		System.out.println("Step 6: Verifying Lead creation...");

		// Fetch actual Last Name from the Lead details page
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		// Fetch actual Company Name from the Lead details page
		String actCompName = driver.findElement(By.id("dtlview_Company")).getText();
		
		// Fetch actual Last Name from the Lead details page
		String actMNumber = driver.findElement(By.id("dtlview_Mobile")).getText();

		System.out.println("Expected Lead Last Name: " + lName);
		System.out.println("Actual Lead Last Name: " + actLastName);

		System.out.println("Expected Company Name: " + compName);
		System.out.println("Actual Company Name: " + actCompName);
		System.out.println("Expected Mobile Number: " + mNumber);
		System.out.println("Actual Mobile Number: " + actMNumber);

		// Verify both Last Name AND Company Name
		if (actLastName.equals(lName) && actCompName.equals(compName) && actMNumber.equals(mNumber)) {

		    System.out.println("PASS: Lead created successfully with correct Last Name and Company Name !!!");

		} else {

		    System.out.println("FAIL: Lead creation failed or entered details do not match.....");

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