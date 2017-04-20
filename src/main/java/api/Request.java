package api;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import utilities.ReadProperty;

/**
 * @author Saju.Thomas
 *
 */
public class Request {

    private static Logger log = Logger.getLogger(Request.class);

    public static  <T> Object getRequest(String URI ,Class<?> className) {
    	Object response = null;
    	try {
    	    Response resp = null;
    	    Class<?> clazz = null;
    	    clazz = Class.forName(className.getName());
    	    response = clazz.newInstance();
    	    Gson gson = new Gson();
    	    RestAssured.baseURI = ReadProperty.readConfig().getProperty("apiEndPoint");
    	    resp=RestAssured.given().when().get(URI);
    	    Assert.assertEquals(resp.getStatusCode(), 200,"Status code is not 200. ");
    	    log.info("Response : " + resp.toString());
    	    System.out.println(resp.getBody().asString());
    	    response = gson.fromJson(resp.getBody().asString(), className);
    	    System.out.println("Returning the mapped response: "+response.toString());
    	    log.info("Request recieved and api has been hit, now returning the response");
    	} catch (ClassNotFoundException e1) {
    	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
    	    e1.printStackTrace();
    	} catch (InstantiationException e1) {
    	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
    	    e1.printStackTrace();
    	} catch (IllegalAccessException e1) {
    	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
    	    e1.printStackTrace();
    	} catch (Exception e) {
    	    log.error("Something went wrong !!! Please check the stack trace for more details: ");
    	    e.printStackTrace();
    	} 
    	
    		return response;
        }

    
//    public static void main(String[] args) {
//		Request.getRequest("country/get/all");
//	}
//    public static <T> Object postRequest(String URI, Object object, Class<?> className) {
//	Object response = null;
//	try {
//	    Response resp = null;
//	    Class<?> clazz = null;
//	    clazz = Class.forName(className.getName());
//	    response = clazz.newInstance();
//	    Gson gson = new Gson();
//	    String request = gson.toJson(object);
//	    RestAssured.baseURI = ReadProperty.readConfig().getProperty("apiEndPoint");
//	    log.info("Request : " + request);
//	    resp = RestAssured.given().contentType("application/json").body(request).when().post(URI);
//	    log.info("Response : " + resp.asString());
//	    response = gson.fromJson(resp.asString(), className);
//	    log.info("Request recieved and api has been hit, now returning the response");
//	} catch (ClassNotFoundException e1) {
//	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
//	    e1.printStackTrace();
//	} catch (InstantiationException e1) {
//	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
//	    e1.printStackTrace();
//	} catch (IllegalAccessException e1) {
//	    log.error("Some error has been occurred. Please check.: " + e1.getMessage());
//	    e1.printStackTrace();
//	} catch (Exception e) {
//	    log.error("Something went wrong !!! Please check the stack trace for more details: ");
//	    e.printStackTrace();
//	}
//	return response;
//    }
}
