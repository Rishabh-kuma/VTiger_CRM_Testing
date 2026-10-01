package contact_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactModule {

	public ContactModule(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// <--------------------------- Declarations --------------------------->

	@FindBy(name = "user_name")
	private WebElement usernameField;

	@FindBy(name = "user_password")
	private WebElement passwordField;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(css = "img[alt = 'Create Contact...']")
	private WebElement createBtn;

	@FindBy(name = "lastname")
	private WebElement lNameField;

	@FindBy(name = "leadsource")
	private WebElement leadSrcField;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "assistant")
	private WebElement astntField;

	@FindBy(id = "jscal_field_birthday")
	private WebElement dobField;

	@FindBy(css = "input[title = 'Save [Alt+S]']")
	private WebElement saveField;

	@FindBy(css = "img[src='themes/softed/images/user.PNG']")
	private WebElement profileField;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutField;

	@FindBy(name = "emailoptout")
	private WebElement emailCheckField;

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

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCreateBtn() {
		return createBtn;
	}

	public WebElement getLNameField() {
		return lNameField;
	}

	public WebElement getLeadSrcField() {
		return leadSrcField;
	}

	public WebElement getEmailField() {
		return emailField;
	}

	public WebElement getAstsnField() {
		return astntField;
	}

	public WebElement getDOBField() {
		return dobField;
	}

	public WebElement getSaveField() {
		return saveField;
	}

	public WebElement getProfileField() {
		return profileField;
	}

	public WebElement getSignOutField() {
		return signOutField;
	}

	public WebElement getEmailCheckField() {
		return emailCheckField;
	}

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
