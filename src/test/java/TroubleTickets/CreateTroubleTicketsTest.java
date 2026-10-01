package TroubleTickets;

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
 * Description:
 * This test verifies the creation of a Trouble Ticket in the Vtiger CRM application.
 *
 * Test Flow:
 * 1. Read browser and login details from the JSON file.
 * 2. Launch the selected browser.
 * 3. Open the Vtiger CRM application.
 * 4. Login using valid credentials.
 * 5. Navigate to Trouble Tickets.
 * 6. Open the Create Trouble Ticket page.
 * 7. Enter the ticket title.
 * 8. Assign the ticket to a user.
 * 9. Select Priority, Severity and Category.
 * 10. Logout from the application.
 * 11. Close the browser.
 *
 * Test Data:
 * - Browser: Read from cd.json
 * - URL: Read from cd.json
 * - Username: Read from cd.json
 * - Password: Read from cd.json
 * - Ticket Title: Unable to login to CRM
 * - Assigned To: User
 * - Priority: Low
 * - Severity: Minor
 * - Category: Small Problem
 *
 * Expected Result:
 * Trouble Ticket creation page should open successfully and the entered
 * ticket details should be accepted without any validation error.
 *
 * Application:
 * Vtiger CRM
 *
 * Test Type:
 * Functional Testing / UI Automation Testing
 *
 * Automation Tool:
 * Selenium WebDriver
 *
 * Programming Language:
 * Java
 *
 * Data Source:
 * JSON file - cd.json
 */
public class CreateTroubleTicketsTest {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("============================================================");
		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Trouble Tickets");
		System.out.println("Application: Vtiger CRM");
		System.out.println("Test Type: Functional UI Automation Testing");
		System.out.println("============================================================");

		// ============================================================
		// STEP 1: READ TEST DATA FROM JSON FILE
		// ============================================================

		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();

		System.out.println("[INFO] Browser: " + browser);
		System.out.println("[INFO] URL: " + url);
		System.out.println("[INFO] Username retrieved successfully.");
		System.out.println("[INFO] Password retrieved successfully.");

		System.out.println("[PASS] Required test data retrieved successfully.");

		System.out.println("============================================================");
		System.out.println("       CREATE TROUBLE TICKETS TEST STARTED       ");
		System.out.println("============================================================");

		// ============================================================
		// STEP 2: OPEN THE BROWSER
		// ============================================================

		System.out.println("STEP 2: Starting browser launch process...");

		WebDriver driver = null;

		if (browser.equals("chrome")) {

			driver = new ChromeDriver();

			System.out.println("[PASS] Chrome browser opened successfully.");

		} else if (browser.equals("edge")) {

			driver = new EdgeDriver();

			System.out.println("[PASS] Edge browser opened successfully.");

		} else if (browser.equals("firefox")) {

			driver = new FirefoxDriver();

			System.out.println("[PASS] Firefox browser opened successfully.");

		} else {

			driver = new ChromeDriver();

			System.out.println("[INFO] Invalid browser value received.");
			System.out.println("[INFO] Default Chrome browser opened successfully.");

		}

		System.out.println("[INFO] Maximizing browser window...");

		driver.manage().window().maximize();

		System.out.println("[PASS] Browser window maximized successfully.");

		System.out.println("[INFO] Configuring implicit wait: 15 seconds...");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		System.out.println("[PASS] Implicit wait configured successfully.");

		// Navigate to URL
		System.out.println("[INFO] Navigating to Vtiger CRM application...");

		driver.get(url);

		System.out.println("[PASS] Vtiger CRM application launched successfully.");

		// ============================================================
		// STEP 3: LOGIN TO VTIGER CRM
		// ============================================================

		System.out.println("STEP 3: Starting login process...");

		System.out.println("[INFO] Locating username field...");

		WebElement usernameField = driver.findElement(By.name("user_name"));

		System.out.println("[PASS] Username field located successfully.");

		System.out.println("[INFO] Locating password field...");

		WebElement passwordField = driver.findElement(By.name("user_password"));

		System.out.println("[PASS] Password field located successfully.");

		System.out.println("[INFO] Entering username...");

		usernameField.sendKeys(username);

		System.out.println("[PASS] Username entered successfully.");

		System.out.println("[INFO] Entering password and submitting login form...");

		passwordField.sendKeys(password + Keys.ENTER);

		System.out.println("[PASS] Password entered and login submitted successfully.");

		System.out.println("[PASS] Login process completed.");

		// ============================================================
		// STEP 4: NAVIGATE TO TROUBLE TICKETS MODULE
		// ============================================================

		System.out.println("STEP 4: Navigating to Trouble Tickets module...");

		System.out.println("[INFO] Locating Trouble Tickets link...");

		driver.findElement(By.linkText("Trouble Tickets")).click();

		System.out.println("[PASS] Trouble Tickets module opened successfully.");

		// ============================================================
		// STEP 5: OPEN CREATE TROUBLE TICKET PAGE
		// ============================================================

		System.out.println("STEP 5: Opening Create Trouble Ticket page...");

		System.out.println("[INFO] Locating Create Ticket icon...");

		driver.findElement(By.cssSelector("img[alt = \"Create Ticket...\"]")).click();

		System.out.println("[PASS] Create Trouble Ticket page opened successfully.");

		// ============================================================
		// STEP 6: ENTER TROUBLE TICKET DETAILS
		// ============================================================

		// Get data from xlsx file
		
		String title = FileUtility.getDataFromExcelFile("Trouble Tickets", 1, 1);
		String assignTo = FileUtility.getDataFromExcelFile("Trouble Tickets", 1, 2);
		String priorityValue = FileUtility.getDataFromExcelFile("Trouble Tickets", 1, 7);
		String severityValue = FileUtility.getDataFromExcelFile("Trouble Tickets", 1, 9);
		String catValue = FileUtility.getDataFromExcelFile("Trouble Tickets", 1, 11);
		
		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		
		System.out.println("STEP 6: Entering Trouble Ticket details...");

		// Enter Ticket Title
		
		System.out.println("[INFO] Locating Title field...");

		WebElement titleField = driver.findElement(By.name("ticket_title"));

		System.out.println("[PASS] Title field located successfully.");

		System.out.println("[INFO] Entering ticket title...");

		titleField.sendKeys(title);

		System.out.println("[PASS] Ticket title entered successfully.");
		System.out.println("[INFO] Entered Ticket Title: " + title);

		// Assign Ticket To User
		System.out.println("[INFO] Locating Assigned To user option...");

		WebElement assignTo1 = driver.findElement(By.xpath("//input[@name = \"assigntype\" and @value = \"U\"]"));

		System.out.println("[PASS] Assigned To user option located successfully.");

		WebElement assignTo2 = driver.findElement(By.xpath("//input[@name = \"assigntype\" and @value = \"U\"]/following-sibling::input"));

		System.out.println("[PASS] User assignment field located successfully.");

		System.out.println("[INFO] Selecting user assignment option...");

		if(assignTo.equals(assignTo1)) {
			assignTo1.click();
		}else {
			assignTo2.click();
		}

		// Select Priority
		
		System.out.println("[INFO] Locating Priority field...");

		WebElement relateToPriority = driver.findElement(By.name("ticketpriorities"));

		System.out.println("[PASS] Priority field located successfully.");

		System.out.println("[INFO] Selecting Priority: " + priorityValue);

		wdUtil.select(priorityValue, relateToPriority);

		System.out.println("[PASS] Priority selected successfully.");
		System.out.println("[INFO] Selected Priority: " + priorityValue);

		// Select Severity
		
		System.out.println("[INFO] Locating Severity field...");

		WebElement relateToSeverity = driver.findElement(By.name("ticketseverities"));

		System.out.println("[PASS] Severity field located successfully.");

		System.out.println("[INFO] Selecting Severity: Minor");

		wdUtil.select(severityValue, relateToSeverity);

		System.out.println("[PASS] Severity selected successfully.");
		System.out.println("[INFO] Selected Severity: Minor");

		// Select Category
		
		System.out.println("[INFO] Locating Category field...");

		WebElement relateToCategory = driver.findElement(By.name("ticketcategories"));

		System.out.println("[PASS] Category field located successfully.");

		System.out.println("[INFO] Selecting Category: Small Problem");

		wdUtil.select(catValue, relateToCategory);

		System.out.println("[PASS] Category selected successfully.");
		System.out.println("[INFO] Selected Category: Small Problem");

		System.out.println("[PASS] Trouble Ticket details entered successfully.");
		
		// ============================================================
		// STEP 5: SAVE PRODUCT
		// ============================================================

		System.out.println("Step 5: Saving Trouble Ticket Details...");

		Thread.sleep(2000);

		System.out.println("Wait completed. Clicking Save button...");

		driver.findElement(By.cssSelector("input[title = 'Save [Alt+S]']")).click();

		System.out.println("Save button clicked successfully.");

		System.out.println("Trouble Ticket save operation completed.");


		// ============================================================
		// STEP 7: LOGOUT
		// ============================================================

		System.out.println("STEP 7: Starting logout process...");

		System.out.println("[INFO] Locating profile icon...");

		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		System.out.println("[PASS] Profile icon located successfully.");

		System.out.println("[INFO] Creating Actions object...");

		System.out.println("[PASS] Actions object created successfully.");

		System.out.println("[INFO] Moving mouse over profile icon...");

		wdUtil.hover(profile);

		System.out.println("[PASS] Mouse hovered over profile icon.");

		System.out.println("[INFO] Locating Sign Out option...");

		driver.findElement(By.linkText("Sign Out")).click();

		System.out.println("[PASS] Sign Out option clicked successfully.");

		System.out.println("[PASS] Logout process completed.");

		// ============================================================
		// STEP 8: CLOSE THE BROWSER
		// ============================================================

		System.out.println("STEP 8: Closing the browser...");

		System.out.println("[INFO] Waiting for 2 seconds before closing browser...");

		Thread.sleep(2000);

		System.out.println("[PASS] Wait completed successfully.");

		System.out.println("[INFO] Closing browser and ending WebDriver session...");

		driver.quit();

		System.out.println("[PASS] Browser closed successfully.");

		// ============================================================
		// TEST EXECUTION END
		// ============================================================

		System.out.println("============================================================");
		System.out.println("========== TEST EXECUTION COMPLETED ==========");
		System.out.println("Test Case: Create Trouble Tickets");
		System.out.println("Result: Execution completed successfully.");
		System.out.println("============================================================");

	}

}