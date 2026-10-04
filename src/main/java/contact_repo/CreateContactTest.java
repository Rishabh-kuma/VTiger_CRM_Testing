package contact_repo;

import java.io.IOException;
import java.time.Duration;
import org.json.simple.parser.ParseException;
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
import logIn_SignOut.LogIn;
import logIn_SignOut.SignOut;

/**
 * Test Class: CreateContactTest
 *
 * Description: This automation script verifies the complete flow of creating a
 * new Contact in the Vtiger CRM application.
 *
 * Test Flow: 1. Launch Chrome browser 2. Maximize browser window 3. Configure
 * implicit wait 4. Open Vtiger application 5. Login using valid credentials 6.
 * Navigate to Contacts module 7. Open Create Contact page 8. Enter Last Name 9.
 * Save the contact 10. Verify the created contact 11. Logout from Vtiger 12.
 * Close the browser
 *
 * Expected Result: Contact should be created successfully with the entered Last
 * Name and the user should be able to logout successfully.
 *
 * Author: Rishabh Application: Vtiger CRM
 */

public class CreateContactTest {

	@Test
	public void createContactTest() throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		Reporter.log("========== TEST EXECUTION STARTED ==========", true);
		Reporter.log("Test Case: Create Organization", true);

		// get data from json file
		String browser = FileUtility.getDataFromJsonFile("browser");
		String url = FileUtility.getDataFromJsonFile("url");
		String username = FileUtility.getDataFromJsonFile("username");
		String password = FileUtility.getDataFromJsonFile("password");

		Reporter.log("==============================================", true);
		Reporter.log("       CREATE ORGANIZATION TEST STARTED       ", true);
		Reporter.log("==============================================", true);

		// ============================================================
		// STEP 1: OPEN THE BROWSER
		// ============================================================

		Reporter.log("[INFO] Launching Chrome browser...", true);

		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
			Reporter.log("Chrome browser oppenned successfully", true);
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
			Reporter.log("Edge browser oppenned successfully", true);
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
			Reporter.log("FireFox browser oppenned successfully", true);
		} else {
			driver = new ChromeDriver();
			Reporter.log("Default browser oppenned successfully", true);
		}

		Reporter.log("[INFO] Maximizing browser window...", true);
		driver.manage().window().maximize();

		Reporter.log("[INFO] Configuring implicit wait: 15 seconds...", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Navigate to URL
		Reporter.log("[INFO] Navigating to Vtiger CRM application...", true);
		driver.get(url);
		Reporter.log("[INFO] Application launched successfully.", true);

		LogIn login = new LogIn(driver);
		Create fields = new Create(driver);
		Verification verify = new Verification(driver);
		SignOut profile = new SignOut(driver);

		// ============================================================
		// STEP 2: LOGIN
		// ============================================================

		WebElement usernameField = login.getUsernameField();
		WebElement passwordField = login.getPasswordField();

		driver.navigate().refresh();

		Reporter.log("Step 2: Starting login process...", true);

		Reporter.log("Username and password fields located successfully.", true);

		usernameField.sendKeys(username);

		Reporter.log("Username entered successfully.", true);

		passwordField.sendKeys(password + Keys.ENTER);

		Reporter.log("Password entered and login submitted.", true);

		Reporter.log("Login process completed.", true);

		// ============================================================
		// STEP 3: CREATE CONTACT
		// ============================================================

		Reporter.log("Step 3: Navigating to Contacts module...", true);

		fields.getContactLink().click();

		Reporter.log("Contacts module opened successfully.", true);

		fields.getCreateBtn().click();

		Reporter.log("Create Contact page opened successfully.", true);

		// get data from excel file

		String lName = FileUtility.getDataFromExcelFile("Contact", 1, 1);
		String email = FileUtility.getDataFromExcelFile("Contact", 1, 6);
		String leadValue = FileUtility.getDataFromExcelFile("Contact", 1, 3);
		String dobValue = FileUtility.getDataFromExcelFile("Contact", 1, 17);
		String astntValue = FileUtility.getDataFromExcelFile("Contact", 1, 7);
		String emailOptValue = FileUtility.getDataFromExcelFile("Contact", 1, 9);

		WebDriverUtility wdUtil = new WebDriverUtility(driver);

		// ============================================================
		// STEP 4: FILLING CONTACT FORM
		// ============================================================

		// ------------------------------------------------------------
		// Adding last name
		// ------------------------------------------------------------

		Reporter.log("Step 4: Filling Contact form...", true);

		Reporter.log("Last Name to be entered: " + lName, true);

		WebElement lNameField = fields.getLNameField();

		Reporter.log("Last Name field located successfully.", true);

		lNameField.sendKeys(lName);

		Reporter.log("Last Name entered successfully.", true);

		// ------------------------------------------------------------
		// Select Contact Lead Source
		// ------------------------------------------------------------

		Reporter.log("Selecting Contact Source...", true);

		WebElement leadField = fields.getLeadSrcField();

		Reporter.log("Lead Source dropdown located successfully.", true);

		wdUtil.select(leadValue, leadField);

		Reporter.log("Lead Source 'Employee' selected successfully.", true);

		// ------------------------------------------------------------
		// Adding Email
		// ------------------------------------------------------------

		Reporter.log("Selecting Email Source...", true);

		Reporter.log("Email to be entered: " + email);

		WebElement emailField = fields.getEmailField();

		Reporter.log("Email field located successfully.", true);

		emailField.sendKeys(email);

		Reporter.log("Email entered successfully.", true);

		// ------------------------------------------------------------
		// Adding Assistant
		// ------------------------------------------------------------

		Reporter.log("Selecting Assistant Source...", true);

		Reporter.log("Assistant to be entered: " + astntValue, true);

		WebElement astntField = fields.getAstsnField();

		Reporter.log("Assistant field located successfully.", true);

		astntField.sendKeys(astntValue);

		Reporter.log("Assistant entered successfully.", true);

		// ------------------------------------------------------------
		// Adding DOB
		// ------------------------------------------------------------

		Reporter.log("Selecting DOB Source...", true);

		Reporter.log("DOB to be entered: " + dobValue, true);

		WebElement dobField = fields.getDOBField();

		Reporter.log("DOB field located successfully.", true);

		dobField.sendKeys(dobValue);

		Reporter.log("DOB entered successfully.", true);

		// ------------------------------------------------------------
		// Check Box: Email Opt Out
		// ------------------------------------------------------------

		WebElement emailCheckField = fields.getEmailCheckField();

		if (emailOptValue.equals("Yes")) {
			emailCheckField.click();
			Reporter.log("Email opt out clicked", true);
		} else {
			Reporter.log("Email opt out not clicked", true);
		}

		// ============================================================
		// STEP 5: SAVE CONTACT
		// ============================================================

		Reporter.log("Step 5: Saving Contact...", true);

		Thread.sleep(2000);

		Reporter.log("Wait completed. Clicking Save button...", true);

		fields.getSaveField().click();

		Reporter.log("Save button clicked successfully.", true);

		Reporter.log("Contact save operation completed.", true);

		// ============================================================
		// STEP 6: VERIFICATION
		// ============================================================

		Reporter.log("Step 6: Verifying Contact creation...");
		// fetch actual Last name from contact details page
		String actLastName = verify.getActLNameField().getText();

		// fetch actual Email from contact details page
		String actEmail = verify.getActEmailField().getText();

		// fetch actual Assistant from contact details page
		String actAstnt = verify.getActAstsnField().getText();

		// fetch actual DOB from contact details page
		String actDob = verify.getActDobField().getText();

		Reporter.log("Expected Last Name: " + lName, true);
		Reporter.log("Actual Last Name: " + actLastName, true);
		Reporter.log("Expected Assistant: " + astntValue, true);
		Reporter.log("Actual Assistant: " + actAstnt, true);
		Reporter.log("Expected Email: " + email, true);
		Reporter.log("Actual Email: " + actEmail, true);
		Reporter.log("Expected DOB: " + dobValue, true);
		Reporter.log("Actual DOB: " + actDob, true);

		if (actLastName.equals(lName) && actEmail.equals(email) && actAstnt.equals(astntValue)
				&& actDob.equals(dobValue)) {

			Reporter.log("PASS: Contact created successfully...", true);

		} else {

			Reporter.log("FAIL: Contact Creation failed...", true);

		}

		// ============================================================
		// STEP 7: LOGOUT
		// ============================================================

		Reporter.log("Step 7: Starting logout process...", true);

		WebElement profileIcon = profile.getProfileField();

		Reporter.log("Profile icon located successfully.", true);

		Reporter.log("Actions object created successfully.", true);

		wdUtil.hover(profileIcon);

		Reporter.log("Mouse hovered over profile icon.", true);

		profile.getSignOutField().click();

		Reporter.log("Sign Out option clicked successfully.", true);

		Reporter.log("Logout process completed.", true);

		// ============================================================
		// STEP 8: CLOSE BROWSER
		// ============================================================

		Thread.sleep(2000);

		Reporter.log("Waiting time completed...", true);

		driver.quit();

		Reporter.log("Browser closed successfully.", true);

		Reporter.log("========== TEST EXECUTION COMPLETED ==========", true);

	}
}