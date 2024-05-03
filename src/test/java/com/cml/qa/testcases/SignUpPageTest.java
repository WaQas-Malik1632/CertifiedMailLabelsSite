package com.cml.qa.testcases;

import org.testng.annotations.Test;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.HomePageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.pages.SignUpPageClass;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class SignUpPageTest extends TestBaseClass {
	LoginPageClass login;
	HomePageClass homepage;
	SignUpPageClass signup;
	
	public SignUpPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		login=new LoginPageClass();
		homepage = login.Login_Testcases(prop.getProperty("username"), prop.getProperty("password"));
		signup=new SignUpPageClass();
		
	}

	@Test(priority = 1, description = "Signup TC001", enabled = true, invocationCount = 1)
	@Description("Verify that user is able to register himself successfully")
	@Epic("Singup_EP001")
	@Feature("Signup_001")
	@Story("Verify that user is able to register himself successfully")
	@Step("Signup>>Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Attachment()
	public void Testcases_ToVerifyRegisterUserSuccessfully() throws IOException {

		signup.Precondition();
		signup.userRegistrationForm("User", "ABCTest", "User ABCTest", "Return address", "Address2", "US", "12",
				"1111", "0000000000", "Testabc@mail.com", "Test@123", "Test@123");
		// Verify page title is matched "nopCommerce demo store"
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
		String actual_url = driver.getCurrentUrl();
		String expected_url = "https://demo.nopcommerce.com/";
		Assert.assertEquals(actual_url, expected_url);
	}

	@AfterMethod
	public void afterMethod() {
	}

}
