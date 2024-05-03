package com.cml.qa.testcases;

import org.testng.annotations.Test;
import com.cml.qa.base.TestBaseClass;
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
	SignUpPageClass signup;

	public SignUpPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		signup = new SignUpPageClass();
		signup.Precondition();
	}

	@Test(priority = 1, invocationCount = 1, enabled = true, description = "SignUp Page Test #1")
	@Description("SignUp Page->Verify that user is able to validate signUp Page Title")
	@Epic("EP001")
	@Feature("Feature:001")
	@Story("Sign Up Page TestCases")
	@Step("Login->Home->Verify Elements")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateSignUpPageTitle() throws IOException {
		signup.Precondition();
		String SignUpTitleVerify = signup.VerifySignUpTitle();
		Assert.assertEquals(SignUpTitleVerify, "Register", "Register");
	}

	@Test(priority = 2, description = "Signup TC001", enabled = true, invocationCount = 1)
	@Description("Verify that user is able to register himself successfully")
	@Epic("Singup_EP001")
	@Feature("Signup_001")
	@Story("Verify that user is able to register himself successfully")
	@Step("Signup>>Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Attachment()
	public void Testcases_ToVerifyRegisterUserSuccessfully() throws IOException {
		signup.userRegistrationForm("User", "ABCTest", "User ABCTest", "Return address", "Address2", "US", "Florida",
				"12", "1111", "0000000000", "Testabc@mail.com", "Test@123", "Test@123");
		// Verify page title is matched "nopCommerce demo store"
		String ExpectedUrl = "https://staging.certifiedmaillabels.com/";
		Assert.assertEquals(signup, driver.getCurrentUrl(), ExpectedUrl);
		System.out.print("Currrent Page Url is:" + driver.getCurrentUrl());
	}

	@AfterMethod
	public void afterMethod() {
	}

}
