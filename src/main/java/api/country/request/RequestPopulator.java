package api.country.request;

import java.util.List;

import org.apache.log4j.Logger;

import api.Request;
import api.country.response.CountryResponse;
import api.country.response.Result;
//import api.country.response.SpecificCountry1;
import api.country.specific.response.SpecificCountry;

public class RequestPopulator {

	static CountryResponse countryResponse = null;
	static SpecificCountry rountryResponse = null;
	private static Logger log = Logger.getLogger(RequestPopulator.class);

	public static CountryResponse getAllCountryCodes() {
		try {
			countryResponse = (CountryResponse) Request.getRequest("country/get/all", CountryResponse.class);
			System.out.println(countryResponse.toString());
		} catch (Exception e) {
			log.error("Exception occurred. Please find the stack trace below:");
			e.printStackTrace();
		}
		try {
			log.info("Child Loan Response: " + countryResponse.toString());
		} catch (NullPointerException nullPointerException) {
			log.error("Some value in invoice response is null");
		}

		return countryResponse;
	}

	public static boolean getAlphaCodeExistence(String countryCode) {
		List<Result> results= getAllCountryCodes().getRestResponse().getResult();
		
		for(Result result:results) {
			if(result.getAlpha2Code().equalsIgnoreCase(countryCode)) {
				return true;
			}
		}
		return false;
	}
	
	public static SpecificCountry getCountry(String countryCode) {

		try {
			rountryResponse = (SpecificCountry) Request.getRequest("country/get/iso2code/"+countryCode, SpecificCountry.class);
			System.out.println(rountryResponse.toString());
		} catch (Exception e) {
			log.error("Exception occurred. Please find the stack trace below:");
			e.printStackTrace();
		}
		try {
			log.info("Child Loan Response: " + rountryResponse.toString());
		} catch (NullPointerException nullPointerException) {
			log.error("Some value in invoice response is null");
		}

		return rountryResponse;
	}

	public static void main(String[] args) {
		System.out.println(RequestPopulator.getAllCountryCodes().getRestResponse().getMessages());
		// System.out.println(RequestPopulator.getAllCountryCodes().getRestResponse().getResult().get(1).getName());
	}
}
