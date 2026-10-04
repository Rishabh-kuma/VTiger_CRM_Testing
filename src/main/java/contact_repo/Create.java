package contact_repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create {

	public Create(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// <--------------------------- Declarations --------------------------->

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

	@FindBy(name = "emailoptout")
	private WebElement emailCheckField;

	// <--------------------------- Constructors --------------------------->

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

	public WebElement getEmailCheckField() {
		return emailCheckField;
	}

}
