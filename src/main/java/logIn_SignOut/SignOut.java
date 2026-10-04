package logIn_SignOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOut {

	public SignOut(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// <--------------------------- Declarations --------------------------->

	@FindBy(css = "img[src='themes/softed/images/user.PNG']")
	private WebElement profileField;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutField;

	// <--------------------------- Constructors --------------------------->

	public WebElement getProfileField() {
		return profileField;
	}

	public WebElement getSignOutField() {
		return signOutField;
	}

}
