package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import TestData.DataSet;
import reporting.CTListener;

/**
 * @author Saju.Thomas
 *
 *April 3, 2017
 */
public class Browser {

	// to get driver and perform driver functions
	private static WebDriver driver = null;
	private static Logger logger = Logger.getLogger(Browser.class);
	static CTListener ctListener;
	private static EventFiringWebDriver eventFiringWebDriver;

	public static WebDriver getBrowser() {
		try {
			Properties properties = ReadProperty.readConfig();
			if (!isBrowserOpen()) {
				instantiateBrowser(properties.getProperty("browser"));
				driver.get(DataSet.getURL());
				// return driver;
			} else {
				logger.info("Browser already opened");
				// return driver;
			}
		} catch (NullPointerException e) {
			logger.error("Unable to instantiate the browser, got exception: " + e.getMessage() + ", Please check.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Unable to read config, got exception: " + e.getMessage() + ", Please check.");
			e.printStackTrace();
		}
		return driver;
	}

	public static WebDriver getInstance() {
		if(driver!=null ) {
		    return driver;
		} else {
		   return  getBrowser();
		}
	}

	public static WebDriver getBrowser(String specs) {
		try {
			Properties properties = ReadProperty.readConfig(specs);
			if (!isBrowserOpen()) {	//checks whether browser is open or not
				instantiateBrowser(properties.getProperty("browser"));
				driver.get(properties.getProperty("testurl"));
			} else {
				logger.info("Browser already opened.Returning the instance");
			}
		} catch (NullPointerException e) {
			logger.error("Unable to instantiate the browser, got exception: " + e.getMessage() + ", Please check.");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Unable to read config, got exception: " + e.getMessage() + ", Please check.");
			e.printStackTrace();
		}
		return driver;
	}

	private static WebDriver instantiateBrowser(String browser) throws IOException {
		logger.info("Instantiating: " + browser.toUpperCase());
		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					ExecutableFunctionalities.getPath(Browser.class, "/Users/sajupt/Desktop/chromedriver"));
			ChromeOptions options = new ChromeOptions();
			Map<String, Boolean> prefs = new Hashtable<String, Boolean>();
			prefs.put("download.prompt_for_download", true);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			File file= new File("/Users/sajupt/Desktop/geckodriver");
			System.setProperty("webdriver.gecko.driver",
					file.getAbsolutePath());
			driver = new FirefoxDriver();
			break;
		case "phantom":
		    	File file1= new File("/Users/sajupt/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");
		    	System.setProperty("phantomjs.binary.path", file1.getAbsolutePath());
			driver = new PhantomJSDriver();
			break;
		default:
			logger.error("Unsupported browser: " + browser.toLowerCase());
			break;
		}
		eventFiringWebDriver = new EventFiringWebDriver(driver);
		ctListener = new CTListener();
		eventFiringWebDriver.register(ctListener);
		eventFiringWebDriver.manage().window().maximize();
		eventFiringWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Capabilities cap= ((RemoteWebDriver) driver).getCapabilities() ;
		logger.info("Browser Name: "+cap.getBrowserName()+"Browser Version: "+cap.getVersion());
		return eventFiringWebDriver;
	}

	private static boolean isBrowserOpen() {
		if (driver != null)
			return true;
		else
			return false;
	}
}
