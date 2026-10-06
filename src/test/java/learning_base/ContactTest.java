package learning_base;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ContactTest extends Practice_Base {
	@Test
	public void createContactTest() {
		Reporter.log("create contact", true);
		Reporter.log("verify contact", true);
	}
}