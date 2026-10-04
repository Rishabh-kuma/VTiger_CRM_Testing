package logIn_SignOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogIn {

	public LogIn(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// <--------------------------- Declarations --------------------------->

	@FindBy(name = "user_name")
	private WebElement usernameField;

	@FindBy(name = "user_password")
	private WebElement passwordField;

	// <--------------------------- Constructors --------------------------->

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}	

}
