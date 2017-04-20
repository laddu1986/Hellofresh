package com.hellofresh.automation;

import java.util.List;
import org.testng.annotations.Test;
//import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


import api.country.request.RequestPopulator;
import api.country.response.Result;
// import groovyjarjarantlr.collections.List;

public class ApiTest {
	
	@Test(description = "Verify presence of countries")
	public void getAllCountryName() {
		List<Result> results=RequestPopulator.getAllCountryCodes().getRestResponse().getResult();
		for (Result result  :results)
			System.out.println(result.getName());
	}

	@Test(description = "Verify presence of countries")
	public void validateCountry() {
		String[] countryCodes = { "us", "de", "gb" };
		for (String countryCode : countryCodes)
			Assert.assertEquals(RequestPopulator.getAlphaCodeExistence(countryCode), true, "Country doesn't exist.");
	}

	@Test(description = "Verify iso code of country")
	public void validateIsoCode() {
		String[] countryCodes = { "us", "de", "gb" };
		for (String countryCode : countryCodes)
			Assert.assertEquals(RequestPopulator.getCountry(countryCode).getRestResponse().getResult().getAlpha2Code(),
					countryCode.toUpperCase(), "Country doesn't exist.");
		
		
		
	}
}
