package pages.hellofresh;

import java.time.Year;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestData.DataSet;
import utilities.Browser;
import utilities.SelectElements;
import utilities.WaitUtil;

public class Register {

	@FindBy(id = "register-user-link")
	private WebElement NEWREGISTER;
	
	@FindBy(id = "login-button")
	private WebElement LOGINPOP;
	
	@FindBy(id = "gender-input")
	private WebElement GENDER;
	
	@FindBy(id = "first-name-input")
	private WebElement FIRSTNAME;
	
	@FindBy(id = "last-name-input")
	private WebElement LASTNAME;
	
	@FindBy(id = "email-input")
	private WebElement EMAIL;
	
	@FindBy(id = "password-input")
	private WebElement PASSWORD;
	
	@FindBy(id = "month-input")
	private WebElement MONTH;
	
	@FindBy(id = "day-input")
	private WebElement DAY;
	
	@FindBy(id = "year-input")
	private WebElement YEAR;
	
	@FindBy(id = "register-button")
	private WebElement REGISTER;
	
	@FindBy(xpath = "//img[@alt='user']/following-sibling::span")
	private WebElement USER;
	
	
	Logger logger = Logger.getLogger(Register.class);
	private static Register instance = null;
	
	public static Register getInstance() {
		if (instance == null) {
			instance = new Register();
		}
		return instance;
	}

	private Register() {
		// this.driver = driver;
		PageFactory.initElements(Browser.getInstance(), this);
	}
	
	public void validateRegister() {
		Map<Integer, List<Object>> dataValues=DataSet.getRegisterData();
		List<Object> data= dataValues.get(0);
		System.out.println(data.toString());
		newRegister(data.get(0).toString(), data.get(1).toString(), data.get(2).toString(), data.get(3).toString(),
				data.get(4).toString(), data.get(5).toString(), data.get(6).toString());
	}
	
	public void newRegister(String firstName,String lastName, String email, String password, String day, String month, String year) {
		LOGINPOP.click();
		WaitUtil.fluentWait(NEWREGISTER).click();
		WaitUtil.fluentWait(FIRSTNAME);
		SelectElements.selectElementByValue(GENDER, "male");
		FIRSTNAME.sendKeys(firstName);
		LASTNAME.sendKeys(lastName);
		EMAIL.sendKeys(email);
		PASSWORD.sendKeys(password);
		DAY.sendKeys(day);
		MONTH.sendKeys(month);
		YEAR.sendKeys(year);
		WaitUtil.fluentWait(REGISTER).click();
		logger.info("User name is: "+WaitUtil.fluentWait(USER).getText().trim());
	}
	
}
