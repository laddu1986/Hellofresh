package pages.hellofresh;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Browser;
import utilities.WaitUtil;

public class Plan {

	
	@FindBy(xpath = "//img[@alt='HelloFresh']")
	private WebElement HELLOFRESH;
	
	@FindBy(id = "banner-view-plans-button")
	private WebElement VIEWPLANS;
	
	@FindBy(id = "classic-menu-product-detail-button")
	private WebElement CLASSICPLAN;
	
	@FindBy(xpath = "//span[@ng-bind='grandTotal']")
	private WebElement GRANDTOTAL;
	
	@FindBy(xpath = "//strong[@class='sushi-detail-price']")
	private WebElement CHECKOUTTOTAL;
	
	@FindBy(id = "add-to-cart-button")
	private WebElement ADDTOCART;
	
	
	Logger logger = Logger.getLogger(Plan.class);
	private static Plan instance = null;
	
	public static Plan getInstance() {
		if (instance == null) {
			instance = new Plan();
		}
		return instance;
	}


	private Plan() {
		// this.driver = driver;
		PageFactory.initElements(Browser.getInstance(), this);
	}
	
	public void selectPlan() {
		WaitUtil.fluentWait(HELLOFRESH).click();
		try {
			WaitUtil.fluentWait(VIEWPLANS).click();
		} catch(NoSuchElementException e) {
			WaitUtil.fluentWait(HELLOFRESH).click();
			WaitUtil.fluentWait(VIEWPLANS).click();
		}
		WaitUtil.fluentWait(CLASSICPLAN).click();
	}
	
	public void validate() {
		String total=GRANDTOTAL.getText().trim().toUpperCase();
	System.out.println("Total: "+total);
		WaitUtil.fluentWait(ADDTOCART).click();
		String checkoutTotal=WaitUtil.fluentWait(CHECKOUTTOTAL).getText().trim().toUpperCase();
		System.out.println("Checkout total: "+checkoutTotal);
		Assert.assertEquals(total.equalsIgnoreCase(checkoutTotal),true,"Checkout price and total are not same: ");
	}
	
}
