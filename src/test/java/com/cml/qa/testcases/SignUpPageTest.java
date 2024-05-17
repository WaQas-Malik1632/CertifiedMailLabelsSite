package com.cml.qa.testcases;

import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
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

	LoginPageClass loginpage;
	DashboardPageClass dashboard;
	SignUpPageClass signup;
	TestUtil_mailinator utilMailinator;

	public SignUpPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		signup = new SignUpPageClass();
		signup.Precondition();
		utilMailinator = new TestUtil_mailinator();
		dashboard = new DashboardPageClass();
	}

	@Test(priority = 1, invocationCount = 1, enabled = false, description = "TC_CML_SS_001")
	@Description("SignUp Page->Verify that user is able to validate signUp Page Title")
	@Epic("EP001")
	@Feature("Feature:001")
	@Story("Sign Up Page TestCases")
	@Step("Login->Home->Verify Elements")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateSignUpPageTitle() throws IOException {
		String SignUpTitleVerify = signup.VerifySignUpTitle();
		Assert.assertEquals(SignUpTitleVerify, "Register", "Register");
	}

	@Test(priority = 1, description = "CML_REG_001", enabled = true, invocationCount = 1)
	@Description("CML_REG_001_Verify that user is able to register himself successfully")
	@Epic("Singup_EP001")
	@Feature("Signup_001")
	@Story("Verify that user is able to register himself successfully")
	@Step("Signup>>Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Attachment()
	public void TC_CML_SS_020() throws IOException, InterruptedException {
		loginpage = signup.userRegistrationForm("TestParent", "Staging178", "Destiny Planners",
				"4678 James Martin " + "Circle", "Columbus, OH 43215", "US", "Florida", "43215", "46478",
				"614-370-3225", "TestUserTwoss@mailinator.com", "Pass@123", "Pass@123");

		// Verify that Page url is belongs to Login Page
		// Verify page url after sign up is matched or not

		System.out.print("After Sign up Current Page Url is:" + driver.getCurrentUrl());

		//Validate email via mailinator
		dashboard = utilMailinator.MailinatorLinkVerification("TestUserTwoss@mailinator.com");

		// Verify page url after Email verification is matched or not
		String ExpectedUrlDashboard = "//https://staging.certifiedmaillabels.com/user/dashboard";
		String ActualUrlDashboard = driver.getCurrentUrl();
		Assert.assertEquals(dashboard, ActualUrlDashboard, ExpectedUrlDashboard);
		System.out.print("Current Page Url is:" + driver.getCurrentUrl());
	}

	@AfterMethod
	public void TearDown() {
	}
}
