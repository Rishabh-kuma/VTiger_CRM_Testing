package contact_repo;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;

/**
 * This class is used to create a Contact in Vtiger CRM.
 *
 * Test Flow: 1. Login is handled by BaseClass 2. Navigate to Contacts module 3.
 * Create new Contact 4. Fetch test data from Excel 5. Fill Contact details 6.
 * Save Contact 7. Verify Contact details 8. Logout is handled by BaseClass
 *
 * Test Data: - Contact data is fetched from Excel
 *
 * Application : Vtiger CRM Test Type : Automation Testing
 */
public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws InterruptedException, IOException, ParseException {

		// ============================================================
		// TEST EXECUTION START
		// ============================================================

		Reporter.log("========== TEST EXECUTION STARTED ==========", true);

		Reporter.log("Test Case: Create Contact", true);

		// ============================================================
		// EXTENT REPORT CONFIGURATION
		// ============================================================

		String browser = FileUtility.getDataFromJsonFile("browser");

		String time = JavaUtility.getCurrentDateTime();

		ExtentSparkReporter spark = new ExtentSparkReporter("./ad_reports/" + time + ".html");

		spark.config().setDocumentTitle("Vtiger CRM Reports");

		spark.config().setReportName("Contact Reports");

		spark.config().setTheme(Theme.DARK);

		ExtentReports report = new ExtentReports();

		report.attachReporter(spark);

		report.setSystemInfo("browser", browser);

		report.setSystemInfo("application", "Vtiger CRM");

		ExtentTest test = report.createTest("Create Contact");

		// ============================================================
		// STEP 1: CREATE PAGE OBJECTS
		// ============================================================

		Reporter.log("Step 1: Creating Contact page objects...", true);

		Create fields = new Create(driver);

		Verification verify = new Verification(driver);

		Reporter.log("Contact page objects created successfully.", true);

		test.log(Status.INFO, "Page objects created successfully.");

		// ============================================================
		// STEP 2: OPEN CONTACT MODULE
		// ============================================================

		Reporter.log("Step 2: Navigating to Contacts module...", true);

		test.log(Status.INFO, "Navigating to Contacts module...");

		fields.getContactLink().click();

		Reporter.log("Contacts module opened successfully.", true);

		fields.getCreateBtn().click();

		Reporter.log("Create Contact page opened successfully.", true);

		// ============================================================
		// STEP 3: GET DATA FROM EXCEL
		// ============================================================

		Reporter.log("Step 3: Fetching Contact data from Excel...", true);

		String lName = FileUtility.getDataFromExcelFile("Contact", 1, 1);

		String email = FileUtility.getDataFromExcelFile("Contact", 1, 6);

		String leadValue = FileUtility.getDataFromExcelFile("Contact", 1, 3);

		String dobValue = FileUtility.getDataFromExcelFile("Contact", 1, 17);

		String astntValue = FileUtility.getDataFromExcelFile("Contact", 1, 7);

		String emailOptValue = FileUtility.getDataFromExcelFile("Contact", 1, 9);

		Reporter.log("Contact test data fetched successfully.", true);

		// ============================================================
		// STEP 4: FILL CONTACT FORM
		// ============================================================

		Reporter.log("Step 4: Filling Contact form...", true);

		test.log(Status.INFO, "Filling Contact form...");

		// ------------------------------------------------------------
		// Last Name
		// ------------------------------------------------------------

		Reporter.log("Last Name to be entered: " + lName, true);

		WebElement lNameField = fields.getLNameField();

		lNameField.sendKeys(lName);

		Reporter.log("Last Name entered successfully.", true);

		// ------------------------------------------------------------
		// Lead Source
		// ------------------------------------------------------------

		Reporter.log("Selecting Contact Source...", true);

		WebElement leadField = fields.getLeadSrcField();

		wdUtil.select(leadValue, leadField);

		Reporter.log("Lead Source selected successfully.", true);

		// ------------------------------------------------------------
		// Email
		// ------------------------------------------------------------

		Reporter.log("Email to be entered: " + email, true);

		WebElement emailField = fields.getEmailField();

		emailField.sendKeys(email);

		Reporter.log("Email entered successfully.", true);

		// ------------------------------------------------------------
		// Assistant
		// ------------------------------------------------------------

		Reporter.log("Assistant to be entered: " + astntValue, true);

		WebElement astntField = fields.getAstsnField();

		astntField.sendKeys(astntValue);

		Reporter.log("Assistant entered successfully.", true);

		// ------------------------------------------------------------
		// DOB
		// ------------------------------------------------------------

		Reporter.log("DOB to be entered: " + dobValue, true);

		WebElement dobField = fields.getDOBField();

		dobField.sendKeys(dobValue);

		Reporter.log("DOB entered successfully.", true);

		// ------------------------------------------------------------
		// Email Opt Out
		// ------------------------------------------------------------

		WebElement emailCheckField = fields.getEmailCheckField();

		if (emailOptValue.equals("Yes")) {

			emailCheckField.click();

			Reporter.log("Email opt out clicked.", true);

		} else {

			Reporter.log("Email opt out not clicked.", true);
		}

		// ============================================================
		// STEP 5: SAVE CONTACT
		// ============================================================

		Reporter.log("Step 5: Saving Contact...", true);

		Thread.sleep(2000);

		fields.getSaveField().click();

		Reporter.log("Save button clicked successfully.", true);

		Reporter.log("Contact save operation completed.", true);

		// ============================================================
		// STEP 6: VERIFICATION
		// ============================================================

		Reporter.log("Step 6: Verifying Contact creation...", true);

		test.log(Status.INFO, "Verifying Contact creation...");

		String actLastName = verify.getActLNameField().getText();

		String actEmail = verify.getActEmailField().getText();

		String actAstnt = verify.getActAstsnField().getText();

		String actDob = verify.getActDobField().getText();

		// ------------------------------------------------------------
		// Display Expected and Actual Values
		// ------------------------------------------------------------

		Reporter.log("Expected Last Name: " + lName, true);

		Reporter.log("Actual Last Name: " + actLastName, true);

		Reporter.log("Expected Assistant: " + astntValue, true);

		Reporter.log("Actual Assistant: " + actAstnt, true);

		Reporter.log("Expected Email: " + email, true);

		Reporter.log("Actual Email: " + actEmail, true);

		Reporter.log("Expected DOB: " + dobValue, true);

		Reporter.log("Actual DOB: " + actDob, true);

		// ------------------------------------------------------------
		// Verification
		// ------------------------------------------------------------

		Assert.assertEquals(lName, actLastName);
		Assert.assertEquals(astntValue, actAstnt);
		Assert.assertEquals(email, actEmail);
		//Assert.assertEquals(dobValue, actDob);

		// ============================================================
		// STEP 7: FLUSH EXTENT REPORT
		// ============================================================

		report.flush();

		Reporter.log("Extent Report generated successfully.", true);

		Reporter.log("========== TEST EXECUTION COMPLETED ==========", true);
	}
}