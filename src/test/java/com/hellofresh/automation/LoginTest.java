package com.hellofresh.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.hellofresh.Login;
import pages.hellofresh.Plan;
import test.BaseTest;
import utilities.Browser;

public class LoginTest extends BaseTest{

	@BeforeTest
	public void setUp() {
		
	}
	
	@Test()
	public void verifyLogin() {

		//Login.getInstance().login("sajupt@yahoo.com", "password");
		Plan.getInstance().selectPlan();
		Login.getInstance().validateLogin();//("sajupt@yahoo.com", "password");
		Plan.getInstance().validate();
		
//		try {
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@AfterMethod
	public void tearDown() {
		Browser.getInstance().quit();
	}
}
