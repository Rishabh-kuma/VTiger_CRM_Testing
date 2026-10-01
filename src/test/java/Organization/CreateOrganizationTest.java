package Organization;

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
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;

/**
 * Test Class: CreateOrganizationTest
 *
 * Description:
 * This automation script verifies the complete flow of creating a new
 * Organization in the Vtiger CRM application.
 *
 * Test Flow:
 * 1. Launch Chrome browser
 * 2. Maximize browser window
 * 3. Configure implicit wait
 * 4. Open Vtiger application
 * 5. Login using valid credentials
 * 6. Navigate to Organizations module
 * 7. Open Create Organization page
 * 8. Generate a unique Organization Name
 * 9. Enter Organization Name
 * 10. Save the Organization
 * 11. Verify the created Organization
 * 12. Logout from Vtiger
 * 13. Close the browser
 *
 * Expected Result:
 * Organization should be created successfully with the generated
 * Organization Name and the user should be able to logout successfully.
 *
 * Application: Vtiger CRM
 * Test Type: Functional / UI Automation
 */

public class CreateOrganizationTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Organization");

		//get data from json file
		
		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();
		
		System.out.println("==============================================");
		System.out.println("       CREATE ORGANIZATION TEST STARTED       ");
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
		// STEP 3: CREATE ORGANIZATION
		// ============================================================

		System.out.println("Step 3: Navigating to Organizations module...");

		driver.findElement(By.linkText("Organizations")).click();

		System.out.println("Organizations module opened successfully.");

		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();

		System.out.println("Create Organization page opened successfully.");

		long random = JavaUtility.generateRandomNumber();
		
		// Get data from xlsx file
		
		String orgName = FileUtility.getDataFromExcelFile("Org", 1, 0) + random;
		String phn = FileUtility.getDataFromExcelFile("Org", 1, 10);
		String email = FileUtility.getDataFromExcelFile("Org", 1, 13);
		String industryValue = FileUtility.getDataFromExcelFile("Org", 1, 6);
		String typeValue = FileUtility.getDataFromExcelFile("Org", 1, 7);
		
		// ============================================================
		// STEP 4: FILLING ORGANIZATION FORM
		// ============================================================
		
		System.out.println("Step 4: Filling Organization form...");

		// ------------------------------------------------------------
		// Select Organization
		// ------------------------------------------------------------

		System.out.println("Generated Organization Name: " + orgName);

		WebElement orgField = driver.findElement(By.name("accountname"));

		System.out.println("Organization Name field located successfully.");

		orgField.sendKeys(orgName);

		System.out.println("Organization Name entered successfully.");

		// ------------------------------------------------------------
		// Select Industry
		// ------------------------------------------------------------

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		
		System.out.println("Selecting Industry...");

		WebElement industryField = driver.findElement(By.name("industry"));

		System.out.println("Industry dropdown located successfully.");
		
		wdUtil.select(industryValue, industryField);

		System.out.println("Industy" + "'" + industryValue +"'" + "selected successfully.");

		// ------------------------------------------------------------
		// Select Type
		// ------------------------------------------------------------

		System.out.println("Selecting Type...");

		WebElement typeField = driver.findElement(By.name("accounttype"));

		System.out.println("Type dropdown located successfully.");
		
		wdUtil.select(typeValue, typeField);

		System.out.println("Type" + "'" + typeValue + "'" + "selected successfully.");

		// ------------------------------------------------------------
		// Select Phone Number
		// ------------------------------------------------------------

		System.out.println("Added Phone Number: " + phn);

		WebElement phnField = driver.findElement(By.id("phone"));

		System.out.println("Phone Number field located successfully.");

		phnField.sendKeys(phn);

		System.out.println("Phone Number entered successfully.");
		
		// ------------------------------------------------------------
		// Adding Email
		// ------------------------------------------------------------

		System.out.println("Selecting Email Source...");

		System.out.println("Email to be entered: " + email);

		WebElement emailField = driver.findElement(By.id("email1"));

		System.out.println("Email field located successfully.");

		emailField.sendKeys(email);

		System.out.println("Email entered successfully.");	
		
		// ============================================================
		// STEP 5: SAVE ORGANIZATION
		// ============================================================

		System.out.println("Step 5: Saving Organization...");

		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		System.out.println("Save button clicked successfully.");

		System.out.println("Organization save operation completed.");


		// ============================================================
		// STEP 6: VERIFICATION
		// ============================================================

		System.out.println("Step 6: Verifying Organization creation...");

		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

		System.out.println("Expected Organization Name: " + orgName);
		System.out.println("Actual Organization Name: " + actOrgName);

		if (actOrgName.equals(orgName)) {

			System.out.println("PASS: Organization Successfully created !!!");

		} else {

			System.out.println("FAIL: Organization creation failed.....");

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