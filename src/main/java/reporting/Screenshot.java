package reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	private static Logger logger = Logger.getLogger(Screenshot.class);
	
	
	public static void takeScreenshot(WebDriver driver, String testCase){
		try {
			File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ss");
			String screenShot="";
			if(driver.getCurrentUrl().contains("#")) 
			   screenShot="screenshot_"+testCase+driver.getCurrentUrl().split("#")[1].replaceAll("/", "_")+"_"+dateFormat.format(Date.from(Instant.now())).toString(); //defining the screenshot name
			else 
			    screenShot="screenshot_"+testCase+"_"+dateFormat.format(Date.from(Instant.now())).toString(); //defining the screenshot name
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/"+Screenshot.makeScreenShotFolder()+"/"+screenShot+".png"));
		} catch(Exception e) {
			logger.error("Exception occurred while taking screenshot, please refer the error message: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//method to create folder if not existed.
	private static File makeScreenShotFolder() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		String time = dateFormat.format(now);
		File dir = new File("screenshot"+time);
		dir.mkdir();
		return dir;
	}
	
	/*public static void takeScreenshot(WebDriver driver){
	try {
		File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//System.out.println(ReadProperty.readConfig().getProperty("screenshotPath"));
		//FileUtils.copyFile(scrFile, new File(ReadProperty.readConfig().getProperty("screenshotPath")+"\\"+driver.getCurrentUrl()+/*"_"+Date.from(Instant.now())+".png"));
		String dateStamp=Date.from(Instant.now()).toString().replace(" ","_").replace(":", "_");
		//System.out.println(date);
		//String screenshotName= "screenshot_"+dateStamp+driver.getCurrentUrl().replaceAll(regex, replacement);//+".png";
	//System.out.println(screenshotName);
		
	//	FileUtils.copyFile(scrFile, new File(ReadProperty.readConfig().getProperty("screenshotPath")+screenshotName+".png"));
	} catch(Exception e) {
		logger.error("Exception occurred while taking screenshot, please refer the error message: "+e.getMessage());
		e.printStackTrace();
	}
}*/
	
	/*public static void pika() {
		String url="http://uat-lms.cointribe.com/#/dealerfin/masterloan";
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm");
		System.out.println(dateFormat.format(Date.from(Instant.now())));
		System.out.println(url.split("#")[1].replaceAll("/", "_"));
	}
	
	public static void main(String[] args) {
		Screenshot.takeScreenshot(Browser.getBrowser());
		//System.out.println(System.getProperty("user.dir")+"/"+Screenshot.makeScreenShotFolder());
		//System.out.print(Screenshot.makeScreenShotFolder());
	}*/
}
