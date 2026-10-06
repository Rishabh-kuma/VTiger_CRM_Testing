package logIn_SignOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object Model class for Vtiger CRM Login page.
 *
 * Application : Vtiger CRM
 * Page        : Login Page
 */
public class LogInPage {

	public LogInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	// ============================================================
	// DECLARATIONS
	// ============================================================

	@FindBy(name = "user_name")
	private WebElement usernameField;

	@FindBy(name = "user_password")
	private WebElement passwordField;


	// ============================================================
	// GETTERS
	// ============================================================

	public WebElement getUsernameField() {
		return usernameField;
	}


	public WebElement getPasswordField() {
		return passwordField;
	}
}