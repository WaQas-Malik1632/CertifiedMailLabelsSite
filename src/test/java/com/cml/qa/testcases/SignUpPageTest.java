package com.cml.qa.testcases;

import com.cml.qa.utilities.TestUtil_mailinator;
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
	TestUtil_mailinator utilMailinator;
	public SignUpPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		utilMailinator=new TestUtil_mailinator();
		signup = new SignUpPageClass();
		signup.Precondition();
	}

	@Test(priority = 1, invocationCount = 1, enabled = false, description = "TC_CML_SS_001")
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

	@Test(priority = 1, description = "Signup TC001", enabled = true, invocationCount = 1)
	@Description("Verify that user is able to register himself successfully")
	@Epic("Singup_EP001")
	@Feature("Signup_001")
	@Story("Verify that user is able to register himself successfully")
	@Step("Signup>>Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Attachment()
	public void TC_CML_SS_020() throws IOException, InterruptedException {
		signup.userRegistrationForm("Testparent", "Staging178", "Destiny Planners", "4678 James Martin Circle", "Columbus, OH 43215", "US", "Florida",
				"43215", "46478", "614-370-3225", "TestUserabcdefgh@mailinator.com", "Pass@123", "Pass@123");

		signup.MailinatorInbox("TestUserabcdefgh@mailinator.com");

		// Verify page url after Email verification is matched or not
		String ExpectedUrl = "//https://staging.certifiedmaillabels.com/user/dashboard";
		String ActualUrl=driver.getCurrentUrl();
		Assert.assertEquals(signup, ActualUrl, ExpectedUrl);
		System.out.print("Current Page Url is:" + driver.getCurrentUrl());
	}

	@AfterMethod
	public void afterMethod() {
	}
}
