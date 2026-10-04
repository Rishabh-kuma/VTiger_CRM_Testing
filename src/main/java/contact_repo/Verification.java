package contact_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Verification {

	public Verification(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verification part

	@FindBy(id = "dtlview_Last Name")
	private WebElement actLNameField;

	@FindBy(id = "dtlview_Email")
	private WebElement actEmailField;
	
	@FindBy(id = "dtlview_Assistant")
	private WebElement actAstsnField;
	
	@FindBy(id = "dtlview_Birthdate")
	private WebElement actDobField;

	// <--------------------------- Constructors --------------------------->

	public WebElement getActLNameField() {
		return actLNameField;
	}

	public WebElement getActEmailField() {
		return actEmailField;
	}

	public WebElement getActAstsnField() {
		return actAstsnField;
	}

	public WebElement getActDobField() {
		return actDobField;
	}	

}
