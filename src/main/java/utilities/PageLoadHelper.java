package utilities;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageLoadHelper {
	
	private PageLoadHelper () {
		
	}
	
	public static PageLoadHelper get() {
		PageLoadHelper pageLoadHelper= new PageLoadHelper();
		return pageLoadHelper;
	}
	
	public PageLoadHelper whenElementIsVisible(WebElement element) {
		try {
			 if(element.isDisplayed()) {
				 return this;
			 } else {
				 throw new Error("Element is not visible: "+element.toString());
			 }
		} catch(WebDriverException e) {
			throw new Error("Element is not visible: "+element.toString(), e);
		}
	} 
	
	public PageLoadHelper whenElementIsEnabled(WebElement element) {
		try {
			 if(element.isEnabled()) {
				 return this;
			 } else {
				 throw new Error("Element is not enabled: "+element.toString());
			 }
		} catch(WebDriverException e) {
			throw new Error("Element is not enabled: "+element.toString(), e);
		}
	} 
	
	public PageLoadHelper whenElementIsClickable(WebElement element) {
		try {
			 if(ExpectedConditions.elementToBeClickable(element) != null) {
				 return this;
			 } else {
				 throw new Error("Element is not Clickable: "+element.toString());
			 }
		} catch(WebDriverException e) {
			throw new Error("Element is not Clickable: "+element.toString(), e);
		}
	} 
	
	
}
