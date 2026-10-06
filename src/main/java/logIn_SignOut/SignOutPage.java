package logIn_SignOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object Model class for Vtiger CRM Sign Out functionality.
 *
 * Application : Vtiger CRM
 * Page        : Home Page / Profile Menu
 */
public class SignOutPage {

	public SignOutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	// ============================================================
	// DECLARATIONS
	// ============================================================

	@FindBy(css = "img[src='themes/softed/images/user.PNG']")
	private WebElement profileField;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutField;


	// ============================================================
	// GETTERS
	// ============================================================

	public WebElement getProfileField() {
		return profileField;
	}


	public WebElement getSignOutField() {
		return signOutField;
	}
}