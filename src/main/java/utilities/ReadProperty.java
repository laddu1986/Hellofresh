package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {

	private static Properties property=new Properties();
	
	public static Properties readConfig() throws FileNotFoundException, IOException{
		//System.out.println(System.getProperty("user.dir"));
		//property= 
		property.load(new FileInputStream(System.getProperty("user.dir")+"/config/config.properties"));
		return property;
	}
	
	public static Properties readConfig(String file) throws FileNotFoundException, IOException{
		//System.out.println(System.getProperty("user.dir"));
		//property= new Properties();
		property.load(new FileInputStream(System.getProperty("user.dir")+"/config/"+file+".properties"));
		return property;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//ReadProperty property= new ReadProperty();
		Properties properties=ReadProperty.readConfig();
		System.out.println(properties.getProperty("browser"));
	}
	
}
