package pages.hellofresh;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestData.DataSet;
import utilities.Browser;
import utilities.WaitUtil;

public class Login {

	@FindBy(id = "login-button")
	private WebElement LOGINPOP;

	@FindBy(id = "capture-email")
	private WebElement EMAIL;
	
	@FindBy(id = "capture-email-password")
	private WebElement PASSWORD;
	
	@FindBy(id = "submit-login-button")
	private WebElement LOGIN;

	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement CONTINUE;
	
	
	Logger logger = Logger.getLogger(Login.class);
	private static Login instance = null;
	
	public static Login getInstance() {
		if (instance == null) {
			instance = new Login();
		}
		return instance;
	}


	private Login() {
		// this.driver = driver;
		PageFactory.initElements(Browser.getInstance(), this);
	}
	
	
	public void validateLogin() {
		Map<Integer, List<Object>> dataValues=DataSet.getLoginData();
		List<Object> data= dataValues.get(0);
		System.out.println(data.toString());
		login(data.get(0).toString(), data.get(1).toString());
	}
	
	public void login(String username, String password) {
		//WaitUtil.fluentWait(LOGINPOP).click();
		WaitUtil.fluentWait(EMAIL).sendKeys(username);
		WaitUtil.fluentWait(CONTINUE).click();
		WaitUtil.fluentWait(PASSWORD).sendKeys(password);
		WaitUtil.fluentWait(CONTINUE).click();
		//LOGIN.click();
	}
}
