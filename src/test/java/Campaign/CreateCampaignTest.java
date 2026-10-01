package Campaign;

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
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

public class CreateCampaignTest {

	@Test
	public void createCampaignTest() throws InterruptedException, IOException, ParseException {
		
		// ============================================================
		// TEST EXECUTION START
		// ============================================================
       
		Reporter.log("============================================================");
		Reporter.log("========== TEST EXECUTION STARTED ==========");
		Reporter.log("Test Case: Create Campaign");
		Reporter.log("Application: Vtiger CRM");
		Reporter.log("Test Type: Functional UI Automation Testing");
		Reporter.log("============================================================");

		// ============================================================
		// STEP 1: READ TEST DATA FROM JSON FILE
		// ============================================================


		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();

		Reporter.log("[INFO] Browser: " + browser);
		Reporter.log("[INFO] URL: " + url);
		Reporter.log("[INFO] Username retrieved successfully.");
		Reporter.log("[INFO] Password retrieved successfully.");

		Reporter.log("[PASS] Required test data retrieved successfully.");

		
		Reporter.log("============================================================");
		Reporter.log("       CREATE CAMPAIGN TEST STARTED       ");
		Reporter.log("============================================================");

		// ============================================================
		// STEP 2: OPEN THE BROWSER
		// ============================================================

		Reporter.log("STEP 2: Starting browser launch process...");

		WebDriver driver = null;

		if (browser.equals("chrome")) {

			driver = new ChromeDriver();

			Reporter.log("[PASS] Chrome browser opened successfully.");

		} else if (browser.equals("edge")) {

			driver = new EdgeDriver();

			Reporter.log("[PASS] Edge browser opened successfully.");

		} else if (browser.equals("firefox")) {

			driver = new FirefoxDriver();

			Reporter.log("[PASS] Firefox browser opened successfully.");

		} else {

			driver = new ChromeDriver();

			Reporter.log("[INFO] Invalid browser value received.");
			Reporter.log("[INFO] Default Chrome browser opened successfully.");

		}

		Reporter.log("[INFO] Maximizing browser window...");

		driver.manage().window().maximize();

		Reporter.log("[PASS] Browser window maximized successfully.");

		Reporter.log("[INFO] Configuring implicit wait: 15 seconds...");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		Reporter.log("[PASS] Implicit wait configured successfully.");

		// Navigate to URL
		Reporter.log("[INFO] Navigating to Vtiger CRM application...");

		driver.get(url);

		Reporter.log("[PASS] Vtiger CRM application launched successfully.");

		// ============================================================
		// STEP 3: LOGIN TO VTIGER CRM
		// ============================================================

		Reporter.log("STEP 3: Starting login process...");

		Reporter.log("[INFO] Locating username field...");

		WebElement usernameField = driver.findElement(By.name("user_name"));

		Reporter.log("[PASS] Username field located successfully.");

		Reporter.log("[INFO] Locating password field...");

		WebElement passwordField = driver.findElement(By.name("user_password"));

		Reporter.log("[PASS] Password field located successfully.");

		Reporter.log("[INFO] Entering username...");

		usernameField.sendKeys(username);

		Reporter.log("[PASS] Username entered successfully.");

		Reporter.log("[INFO] Entering password and submitting login form...");

		passwordField.sendKeys(password + Keys.ENTER);

		Reporter.log("[PASS] Password entered and login submitted successfully.");

		Reporter.log("[PASS] Login process completed.");

		// ============================================================
		// STEP 3: CREATE CAMPAIGN
		// ============================================================

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		
		// Navigate to CAMPAIGN model
		WebElement more = driver.findElement(By.linkText("More"));
		wdUtil.hover(more);
		
		driver.findElement(By.name("Campaigns")).click();
		
		// Navigate to create campaign
		
		driver.findElement(By.cssSelector("img[alt =\"Create Campaign...\"]")).click();
		
		// Get data from xlsx file
		
		
		
		// Fill Form Details
		
		
		
				
		// ============================================================
		// STEP 5: SAVE PRODUCT
		// ============================================================

//		Reporter.log("Step 5: Saving Trouble Ticket Details...");
//
//		Thread.sleep(2000);
//
//		Reporter.log("Wait completed. Clicking Save button...");
//
//		driver.findElement(By.cssSelector("input[title = 'Save [Alt+S]']")).click();
//
//		Reporter.log("Save button clicked successfully.");
//
//		Reporter.log("Trouble Ticket save operation completed.");


		// ============================================================
		// STEP 7: LOGOUT
		// ============================================================

		Reporter.log("STEP 7: Starting logout process...");

		Reporter.log("[INFO] Locating profile icon...");

		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		Reporter.log("[PASS] Profile icon located successfully.");

		Reporter.log("[INFO] Creating Actions object...");



		Reporter.log("[PASS] Actions object created successfully.");

		Reporter.log("[INFO] Moving mouse over profile icon...");

		wdUtil.hover(profile);

		Reporter.log("[PASS] Mouse hovered over profile icon.");

		Reporter.log("[INFO] Locating Sign Out option...");

		driver.findElement(By.linkText("Sign Out")).click();

		Reporter.log("[PASS] Sign Out option clicked successfully.");

		Reporter.log("[PASS] Logout process completed.");

		// ============================================================
		// STEP 8: CLOSE THE BROWSER
		// ============================================================

		Reporter.log("STEP 8: Closing the browser...");

		Reporter.log("[INFO] Waiting for 2 seconds before closing browser...");

		Thread.sleep(2000);

		Reporter.log("[PASS] Wait completed successfully.");

		Reporter.log("[INFO] Closing browser and ending WebDriver session...");

		driver.quit();

		Reporter.log("[PASS] Browser closed successfully.");

		// ============================================================
		// TEST EXECUTION END
		// ============================================================

		Reporter.log("============================================================");
		Reporter.log("========== TEST EXECUTION COMPLETED ==========");
		Reporter.log("Test Case: Create Trouble Tickets");
		Reporter.log("Result: Execution completed successfully.");
		Reporter.log("============================================================");
	}

}
