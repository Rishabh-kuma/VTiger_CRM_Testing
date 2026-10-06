package learning_base;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsTest extends Practice_Base{
	@Test
	public void createleadsTest() {
		Reporter.log("create leads", true);
		Reporter.log("verify leads", true);
	}
}