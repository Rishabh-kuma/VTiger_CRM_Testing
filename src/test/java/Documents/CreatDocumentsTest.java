package Documents;

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

public class CreatDocumentsTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		System.out.println("========== TEST EXECUTION STARTED ==========");
		System.out.println("Test Case: Create Documents");

		// get data from json file

		String browser = FileUtility.getDataFromJsonFile("browser").toString();
		String url = FileUtility.getDataFromJsonFile("url").toString();
		String username = FileUtility.getDataFromJsonFile("username").toString();
		String password = FileUtility.getDataFromJsonFile("password").toString();

		System.out.println("==============================================");
		System.out.println("       CREATE DOCUMENTS TEST STARTED       ");
		System.out.println("==============================================");

		// ============================================================
		// STEP 1: OPEN THE BROWSER
		// ============================================================

		System.out.println("[INFO] Launching Chrome browser...");

		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
			System.out.println("Chrome browser oppenned successfully");
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
			System.out.println("Edge browser oppenned successfully");
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
			System.out.println("FireFox browser oppenned successfully");
		} else {
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

		// coding
		
		String tileValue = FileUtility.getDataFromExcelFile("Documents", 0, 0);
//		String Value = FileUtility.getDataFromExcelFile("Documents", 0, 0);
//		String tileValue = FileUtility.getDataFromExcelFile("Documents", 0, 0);
		
		WebDriverUtility wdUtility = new WebDriverUtility(driver);

		// Get data from xlsx file

		// ============================================================
		// STEP 7: LOGOUT
		// ============================================================

		System.out.println("Step 7: Starting logout process...");

		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		System.out.println("Profile icon located successfully.");

		wdUtility.hover(profile);

		System.out.println("Mouse hovered over profile icon.");

		driver.findElement(By.linkText("Sign Out")).click();

		System.out.println("Sign Out option clicked successfully.");

		System.out.println("Logout process completed.");

		// ============================================================
		// STEP 8: CLOSE BROWSER
		// ============================================================

		Thread.sleep(2000);

		System.out.println("Waiting time completed...");

		driver.quit();

		System.out.println("Browser closed successfully.");

		System.out.println("========== TEST EXECUTION COMPLETED ==========");

	}

}
