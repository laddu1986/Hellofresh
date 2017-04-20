package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.Browser;

public class BaseTest {

	Logger logger= Logger.getLogger(BaseTest.class);
	WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		this.driver= Browser.getBrowser();
		
	}

	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
