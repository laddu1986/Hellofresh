package utilities;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	static WebDriver driver= Browser.getBrowser();
	static WebDriverWait wait= new WebDriverWait(driver, 1000);
	
	public static WebDriverWait getWebDriverWait() {
		return new WebDriverWait(driver, 1000);
	}
	
	public static void  waitForElement(WebElement element){
		angularLoad();
		 wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public static WebElement  waitForClick(WebElement element){
		angularLoad();
		WebElement elem= wait.until(ExpectedConditions.elementToBeClickable(element));
		//System.out.println(elem.getClass()+elem.getText());
		return elem;
		
	}
	
	public static void  waitForElements(List<WebElement> elements){
		angularLoad();
		 wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
	
	
	 public static List<WebElement> fluentWait(List<WebElement> elements  ) {
		try { 
			angularLoad();
			 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					    .withTimeout(500, TimeUnit.SECONDS)
					    .pollingEvery(100, TimeUnit.MILLISECONDS)
					    .ignoring(NoSuchElementException.class)
					    .ignoring(StaleElementReferenceException.class);
			 		return (wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("md-option"))));
		} catch(Exception e) {
			//System.out.println("Elements are not found: "+elements.size());
			e.printStackTrace();
		}
		 return elements;
	 }
	 
	 public static List<WebElement> fluentWaitmdTag() {
			try { 
				angularLoad();
				 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						    .withTimeout(30, TimeUnit.SECONDS)
						    .pollingEvery(100, TimeUnit.MILLISECONDS)
						    .ignoring(NoSuchElementException.class)
						    .ignoring(StaleElementReferenceException.class);
				 		List<WebElement> elements=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("md-option")));
						return elements;
						
			} catch(Exception e) {
				
				e.printStackTrace();
			}
			 return null;
		 }
	 
	 public static WebElement fluentWait(WebElement element  ) {
		try { 
			//angularLoad();
			WebDriver driver=Browser.getBrowser();
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				    .withTimeout(500, TimeUnit.SECONDS)
				    .pollingEvery(1000, TimeUnit.MILLISECONDS)
				    .ignoring(NoSuchElementException.class)
				    .ignoring(StaleElementReferenceException.class);

				return wait.until(ExpectedConditions.visibilityOf(element));
				//return element;
		} catch(WebDriverException e) {
			//System.out.println(e.getMessage());
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return element;
		}
	}
	 
	 public static boolean invisibility(By locator) {
		try { 
			//angularLoad();
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				    .withTimeout(30, TimeUnit.SECONDS)
				    .pollingEvery(1000, TimeUnit.MILLISECONDS);
				return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); // returns true if invisible
				//return element;
		} catch(WebDriverException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
	 
	 
	 public static void angularLoad() {
	//	System.out.println(((JavascriptExecutor)driver).executeScript("return angular.element(document.body).injector().get(\'$http\').pendingRequests.length;"));
		//((JavascriptExecutor)driver).executeScript("return angular.element(document.body).injector().get(\'$http\').pendingRequests.length;");
		 wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				long l=(long) ((JavascriptExecutor)driver).executeScript("return angular.element(document.body).injector().get(\'$http\').pendingRequests.length;");
			//	System.out.println("value of l: "+l);
				boolean b= l==0?true:false;
				return b;
			}
		});
		 
			
	 }
	 
}

