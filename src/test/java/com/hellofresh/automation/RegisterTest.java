package com.hellofresh.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.hellofresh.Register;
import test.BaseTest;
import utilities.Browser;

public class RegisterTest extends BaseTest{

	@BeforeTest
	public void setUp() {
		
	}
	
	@Test()
	public void verifyRegister() {

		//Login.getInstance().login("sajupt@yahoo.com", "password");
		Register.getInstance().validateRegister();
		//newRegister("Saju", "Thomas", "sajupt@rediffmail.com", "12345678", "12", "12", "1980");
		
		try {
			//Thread.sleep(5000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod //this will fulfill the purpose
	public void tearDown() {
		Browser.getInstance().quit();
	}
}
