package base_utility;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import logIn_SignOut.LogInPage;
import logIn_SignOut.SignOutPage;

/**
 * BaseClass provides common pre-condition and post-condition for Vtiger CRM
 * automation test cases.
 *
 * Test Flow: 1. Launch browser 2. Maximize browser 3. Configure implicit wait
 * 4. Navigate to Vtiger CRM application 5. Login using credentials from JSON
 * file 6. Execute test case 7. Logout from application 8. Close browser
 *
 * Test Data: - Browser : JSON file - URL : JSON file - Username : JSON file -
 * Password : JSON file
 *
 * Application : Vtiger CRM Test Type : Automation Testing
 */
public class BaseClass {

	// ============================================================
	// GLOBAL DECLARATIONS
	// ============================================================

	public WebDriver driver = null;
	public WebDriverUtility wdUtil;

	// ============================================================
	// @BEFORE CLASS
	// ============================================================

	/**
	 * Opens the browser before execution of the test class.
	 *
	 * Browser is selected dynamically from the JSON configuration file.
	 */
	@BeforeClass
	public void setUp() throws IOException, ParseException {

		// ============================================================
		// STEP 1: OPEN THE BROWSER
		// ============================================================

		String browser = FileUtility.getDataFromJsonFile("browser");

		Reporter.log("========== BROWSER SETUP STARTED ==========", true);
		Reporter.log("[INFO] Browser selected: " + browser, true);

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			Reporter.log("[WARN] Invalid browser specified. Launching Chrome by default.", true);
			driver = new ChromeDriver();
		}

		Reporter.log("[INFO] " + browser + " browser launched successfully.", true);

		// ============================================================
		// STEP 2: CREATE WEBDRIVER UTILITY OBJECT
		// ============================================================

		wdUtil = new WebDriverUtility(driver);

		Reporter.log("[INFO] WebDriverUtility object created successfully.", true);

		// ============================================================
		// STEP 3: MAXIMIZE BROWSER
		// ============================================================

		Reporter.log("[INFO] Maximizing browser window...", true);

		wdUtil.windowMax();

		Reporter.log("[INFO] Browser window maximized successfully.", true);

		// ============================================================
		// STEP 4: CONFIGURE IMPLICIT WAIT
		// ============================================================

		Reporter.log("[INFO] Configuring implicit wait: 15 seconds...", true);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		Reporter.log("[INFO] Implicit wait configured successfully.", true);
		Reporter.log("========== BROWSER SETUP COMPLETED ==========", true);
	}

	// ============================================================
	// @BEFORE METHOD
	// ============================================================

	/**
	 * Navigates to Vtiger CRM and performs login before execution of every test
	 * method.
	 */
	@BeforeMethod
	public void login() throws IOException, ParseException {

		// ============================================================
		// STEP 1: GET TEST DATA FROM JSON
		// ============================================================

		String url = FileUtility.getDataFromJsonFile("url");
		String username = FileUtility.getDataFromJsonFile("username");
		String password = FileUtility.getDataFromJsonFile("password");

		// ============================================================
		// STEP 2: CREATE LOGIN PAGE OBJECT
		// ============================================================

		LogInPage loginPage = new LogInPage(driver);

		Reporter.log("[INFO] LogInPage object created successfully.", true);

		// ============================================================
		// STEP 3: NAVIGATE TO APPLICATION
		// ============================================================

		Reporter.log("[INFO] Navigating to Vtiger CRM application...", true);

		driver.get(url);

		Reporter.log("[INFO] Vtiger CRM application launched successfully.", true);

		// ============================================================
		// STEP 4: LOGIN
		// ============================================================

		Reporter.log("[INFO] Starting login process...", true);

		WebElement usernameField = loginPage.getUsernameField();
		WebElement passwordField = loginPage.getPasswordField();

		Reporter.log("[INFO] Username and password fields located successfully.", true);

		usernameField.sendKeys(username);

		Reporter.log("[INFO] Username entered successfully.", true);

		passwordField.sendKeys(password + Keys.ENTER);

		Reporter.log("[INFO] Password entered and login submitted successfully.", true);
		Reporter.log("[PASS] Login process completed successfully.", true);
	}

	// ============================================================
	// @AFTER METHOD
	// ============================================================

	/**
	 * Performs logout after execution of every test method.
	 */
	@AfterMethod
	public void logout() {

		// ============================================================
		// STEP 1: CREATE SIGN OUT PAGE OBJECT
		// ============================================================

		SignOutPage signOutPage = new SignOutPage(driver);

		Reporter.log("[INFO] SignOutPage object created successfully.", true);

		// ============================================================
		// STEP 2: LOCATE PROFILE ICON
		// ============================================================

		Reporter.log("[INFO] Starting logout process...", true);

		WebElement profileIcon = signOutPage.getProfileField();

		Reporter.log("[INFO] Profile icon located successfully.", true);

		// ============================================================
		// STEP 3: HOVER OVER PROFILE ICON
		// ============================================================

		wdUtil.hover(profileIcon);

		Reporter.log("[INFO] Mouse hovered over profile icon successfully.", true);

		// ============================================================
		// STEP 4: CLICK SIGN OUT
		// ============================================================

		WebElement signOutField = signOutPage.getSignOutField();

		Reporter.log("[INFO] Sign Out option located successfully.", true);

		signOutField.click();

		Reporter.log("[PASS] Logout process completed successfully.", true);
	}

	// ============================================================
	// @AFTER CLASS
	// ============================================================

	/**
	 * Closes the browser after execution of all test methods.
	 */
	@AfterClass
	public void tearDown() {

		// ============================================================
		// STEP 1: CLOSE BROWSER
		// ============================================================

		Reporter.log("[INFO] Closing browser...", true);

		driver.quit();

		Reporter.log("[INFO] Browser closed successfully.", true);
		Reporter.log("========== TEST EXECUTION COMPLETED ==========", true);
	}
}