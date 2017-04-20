package utilities;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Saju.Thomas
 *
 *April 3, 2017
 */
public class SelectElements {

	//to select elements on the basis of tag and options value displayed.
	static int count=0;
	private static Logger log = Logger.getLogger(SelectElements.class);
	
	public static void selectElementByValue(WebElement element, String valueToBeSelected) {
		try {	
			//Thread.sleep(3000);
			if(element!=null) {
			Select select= new Select(WaitUtil.fluentWait(element));
				//System.out.println(select.getOptions());
				for(WebElement elementToBeSelected: select.getOptions()) {
					if(elementToBeSelected.getAttribute("value").trim().equalsIgnoreCase(valueToBeSelected)) {
						elementToBeSelected.click();
						System.out.println("Selected value using selectElement: " + valueToBeSelected);
						break;
					}
				}
			} else 
			    log.error("Passed Element is null.");
			//	Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getSelectedValue(WebElement element) {
		try {	
			Select select= new Select(WaitUtil.fluentWait(element));
			return select.getFirstSelectedOption().getText().trim();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void selectElementByVisibleText(WebElement element, String valueToBeSelected) {
		try {	
			Select select= new Select(WaitUtil.fluentWait(element));
					select.selectByVisibleText(valueToBeSelected);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectElementByLabel(WebElement element, String valueToBeSelected) {
		try {	
		//	Thread.sleep(1000);
			Select select= new Select(WaitUtil.fluentWait(element));
				//System.out.println(select.getOptions());
				for(WebElement elementToBeSelected: select.getOptions()) {
					if(elementToBeSelected.getAttribute("label").trim().equalsIgnoreCase(valueToBeSelected)) {
						elementToBeSelected.click();
					//	System.out.println("Selected value using selectElement: " + valueToBeSelected);
						break;
					}
				}
			//	Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void mdSelectElement(WebDriver driver, String valueToBeSelected) {
		//Select select= new Select(element);
		try {
		//	System.out.println("Recieved value to be selected is: "+count++);	
		//	Thread.sleep(500);
			List<WebElement> select = WaitUtil
						.fluentWait(driver.findElements(By.tagName("md-option")));// driver.findElements(By.tagName("md-option"));
				// List<WebElement> disabilityList =disabilityOptions.getOptions();
			if(select.size()>0 && select!=null) {
				for (WebElement elementToBeSelected : select) {
					//System.out.println(elementToBeSelected.getAttribute("value").trim());
					if (elementToBeSelected.getAttribute("value").trim().equalsIgnoreCase(valueToBeSelected)) {
						elementToBeSelected.click();
					//	System.out.println("Selected value using mdSelectElement: " + valueToBeSelected);
						break;
					}
				}
			}
			//	Thread.sleep(3000);
			} catch(Exception e) {
				System.out.println("Elements not found");
				e.printStackTrace();
			}
	}
	
	public static void mdSelectElement(String valueToBeSelected) {
		//Select select= new Select(element);
		try {
		//	System.out.println("Recieved value to be selected is: "+valueToBeSelected);	
		//	Thread.sleep(500);
			List<WebElement> select = WaitUtil
						.fluentWaitmdTag();// driver.findElements(By.tagName("md-option"));
				// List<WebElement> disabilityList =disabilityOptions.getOptions();
				for (WebElement elementToBeSelected : select) {
					//System.out.println(elementToBeSelected.getAttribute("value").trim());
					if (elementToBeSelected.getAttribute("value").trim().equalsIgnoreCase(valueToBeSelected)) {
						elementToBeSelected.click();
			//			System.out.println("Selected value using mdSelectElement: " + valueToBeSelected);
						break;
					}
				}
			//	Thread.sleep(3000);
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}
