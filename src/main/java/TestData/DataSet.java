package TestData;

import java.util.List;
import java.util.Map;

import utilities.ExcelUtil;

public class DataSet {

	/*to get data for the test cases based 
	 * on the specific data source- Excel or DB*/
	static ExcelUtil excelUtil=null;
	public static Map<Integer, List<Object>> getRegisterData() {
		excelUtil= new ExcelUtil();
		return excelUtil.getDataObject("testData", "Register");
	}
	
	public static Map<Integer, List<Object>> getLoginData() {
		excelUtil= new ExcelUtil();
		return excelUtil.getDataObject("testData", "Login");
	}
	
	public static String getURL() {
		excelUtil= new ExcelUtil();
		Map<Integer, List<Object>> urlData= excelUtil.getDataObject("testData", "URL");
		List<Object>url=urlData.get(0);
		return url.get(0).toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(DataSet.getURL());
//	}
}
