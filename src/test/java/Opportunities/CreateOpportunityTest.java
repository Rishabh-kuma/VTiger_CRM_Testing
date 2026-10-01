package Opportunities;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

/**
 * Test Class: CreateOpportunityTest
 *
 * Description:
 * This automation script verifies the complete flow of creating a new
 * Opportunity in the Vtiger CRM application.
 *
 * Test Flow:
 * 1. Launch Chrome browser
 * 2. Maximize browser window
 * 3. Configure implicit wait
 * 4. Open Vtiger application
 * 5. Login using valid credentials
 * 6. Navigate to Opportunities module
 * 7. Open Create Opportunity page
 * 8. Enter Opportunity Name
 * 9. Select Organizations from Related To dropdown
 * 10. Open Organization selection popup
 * 11. Select required Organization from popup
 * 12. Switch back to parent window
 * 13. Save the Opportunity
 * 14. Verify the created Opportunity
 * 15. Logout from Vtiger
 * 16. Close the browser
 *
 * Test Data:
 * Opportunity Name: MakunaiDeal
 * Organization Name: MakunaiGlobal_
 *
 * Expected Result:
 * Opportunity should be created successfully with the entered
 * Opportunity Name and the selected Organization.
 *
 * Application: Vtiger CRM
 * Test Type: Functional / UI Automation
 */

public class CreateOpportunityTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Opportunity");

		//get data from json file

		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();
		
		System.out.println("==============================================");
		System.out.println("       CREATE OPPORTUNITIES TEST STARTED       ");
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
		// STEP 3: CREATE OPPORTUNITY
		// ============================================================

		System.out.println("Step 3: Navigating to Opportunities module...");

		driver.findElement(By.linkText("Opportunities")).click();

		System.out.println("Opportunities module opened successfully.");

		driver.findElement(By.cssSelector("img[alt='Create Opportunity...']")).click();

		System.out.println("Create Opportunity page opened successfully.");


		// ============================================================
		// STEP 4: FILLING OPPORTUNITY FORM
		// ============================================================

		
		 WebDriverUtility wdUtil = new WebDriverUtility(driver);
		// Get data from xlsx file
		
		String oppoName = FileUtility.getDataFromExcelFile("Opportunities", 1, 0);
		String relatedValue = FileUtility.getDataFromExcelFile("Opportunities", 1, 1);
		String typeValue = FileUtility.getDataFromExcelFile("Opportunities", 1, 2);
		String leadSrcValue = FileUtility.getDataFromExcelFile("Opportunities", 1, 3);
		
		System.out.println("Step 4: Filling Opportunity form...");

		// ------------------------------------------------------------
		// Enter Opportunity Name
		// ------------------------------------------------------------

		System.out.println("Opportunity Name: " + oppoName);

		WebElement oppoNameField = driver.findElement(By.name("potentialname"));

		System.out.println("Opportunity Name field located successfully.");

		oppoNameField.sendKeys(oppoName);

		System.out.println("Opportunity Name entered successfully.");


		// ------------------------------------------------------------
		// Select Organizations from Related To dropdown
		// ------------------------------------------------------------

		System.out.println("Selecting Organizations from Related To...");

		WebElement relatedToDropdown = driver.findElement(By.name("related_to_type"));

		Select select = new Select(relatedToDropdown);

		select.selectByVisibleText("Organizations");

		System.out.println("Organizations selected successfully.");


		// ------------------------------------------------------------
		// Organization Name to be selected
		// ------------------------------------------------------------
		
		String orgName = relatedValue;

		System.out.println("Organization to be selected: " + orgName);


		// ------------------------------------------------------------
		// Open Organization Selection Popup
		// ------------------------------------------------------------

		System.out.println("Opening Organization selection popup...");

		String parentWindow = driver.getWindowHandle();

		/*
		 * Clicking the lookup/select icon beside the Related To field.
		 *
		 * This opens a separate popup window where the required
		 * Organization can be selected.
		 */

		driver.findElement(By.xpath("//input[@id='related_to']/following-sibling::img")).click();

		System.out.println("Organization selection popup opened successfully.");


		// ============================================================
		// STEP 5: SELECT ORGANIZATION FROM POPUP
		// ============================================================

		wdUtil.switchToWindowByTitle(parentWindow);
		
		// ------------------------------------------------------------
		// Select Organization
		// ------------------------------------------------------------

		System.out.println("Searching for Organization: " + orgName);

		/*
		 * Select the required Organization from the popup window.
		 * The XPath searches for an Organization containing the
		 * specified Organization Name.
		 */

		driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();

		System.out.println("Organization selected successfully.");


		// ------------------------------------------------------------
		// Switch back to Parent Window
		// ------------------------------------------------------------

		driver.switchTo().window(parentWindow);

		System.out.println("Returned to Create Opportunity page successfully.");

		
		// ------------------------------------------------------------
		// Select Organization type from Type To dropdown
		// ------------------------------------------------------------

		System.out.println("Selecting Organizations from Related To...");

		WebElement relatedToType = driver.findElement(By.name("opportunity_type"));

		wdUtil.select(typeValue, relatedToType);

		System.out.println("Organization Type selected successfully.");

		// ------------------------------------------------------------
		// Select Lead Source type from Type To dropdown
		// ------------------------------------------------------------

		System.out.println("Selecting Organizations from Related To...");

		WebElement relatedToLeadSrc = driver.findElement(By.name("leadsource"));

		wdUtil.select(leadSrcValue, relatedToLeadSrc);	

		System.out.println("Organization Lead Source selected successfully.");


		// ============================================================
		// STEP 6: SAVE OPPORTUNITY
		// ============================================================

		System.out.println("Step 6: Saving Opportunity...");

		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		System.out.println("Save button clicked successfully.");

		System.out.println("Opportunity save operation completed.");


		// ============================================================
		// STEP 7: VERIFICATION
		// ============================================================

		System.out.println("Step 7: Verifying Opportunity creation...");


		// ------------------------------------------------------------
		// Get Actual Opportunity Name
		// ------------------------------------------------------------

		String actOppoName = driver.findElement(By.id("dtlview_Opportunity Name")).getText();

		System.out.println("Expected Opportunity Name: " + oppoName);

		System.out.println("Actual Opportunity Name: " + actOppoName);


		// ------------------------------------------------------------
		// Verify Opportunity Name
		// ------------------------------------------------------------

		if (actOppoName.equals(oppoName)) {
			System.out.println("PASS: Opportunity created successfully with correct Opportunity Name !!!");
		} else {

			System.out.println("FAIL: Opportunity creation failed or entered details do not match.....");
		}


		// ============================================================
		// STEP 8: LOGOUT
		// ============================================================

		System.out.println("Step 8: Starting logout process...");

		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		System.out.println("Profile icon located successfully.");

		Actions act = new Actions(driver);

		System.out.println("Actions object created successfully.");

		act.moveToElement(profile).build().perform();

		System.out.println("Mouse hovered over profile icon.");

		driver.findElement(By.linkText("Sign Out")).click();

		System.out.println("Sign Out option clicked successfully.");

		System.out.println("Logout process completed.");


		// ============================================================
		// STEP 9: CLOSE THE BROWSER
		// ============================================================

		System.out.println("Step 9: Closing the browser...");

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