package pages.hellofresh;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp {
	@FindBy(name = "email")
	private WebElement EMAIL;

	@FindBy(name = "password")
	private WebElement PASSWORD;
	
}
