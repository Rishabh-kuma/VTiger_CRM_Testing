package learning_base;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class OrgTest extends Practice_Base {
	@Test
	public void createOrgTest() {
		Reporter.log("create Org", true);
		Reporter.log("verify Org", true);
	}
}