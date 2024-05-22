package com.cml.qa.testcases;

import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.utilities.TestUtil;
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
	TestUtil util;
	LoginPageClass loginPage;
	DashboardPageClass dashboard;
	SignUpPageClass signup;
	TestUtil_mailinator utilMailinator;

	public SignUpPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		util=new TestUtil();
		intialization();
		loginPage=new LoginPageClass();
		signup = new SignUpPageClass();
		signup.Precondition();
		utilMailinator = new TestUtil_mailinator();
		dashboard = new DashboardPageClass();
	}

	@Test(priority = 1, invocationCount = 1, enabled = false, description = "CML_REG_002")
	@Description("CML_REG_002->SignUp Page->Verify that user is able to validate signUp Page Title")
	@Epic("SINGUP->EP001")
	@Feature("SINGUP->Feature:001")
	@Story("SignUp Page TestCases")
	@Step("Login->Home->Verify Elements")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_CML_SS_002() throws IOException {
		String SignUpTitleVerify = signup.VerifySignUpTitle();

		try {
			Assert.assertEquals(SignUpTitleVerify, "Register", "SignUp title does not match");
			System.out.println("SignUp title has been successfully verified");
		} catch (AssertionError e) {
			System.out.println("SignUp title verification failed: " + e.getMessage());
			throw e; // Re-throw the assertion error to ensure the test fails
		}
	}

	@Test(priority = 1, description = "CML_REG_001", enabled = true, invocationCount = 1)
	@Description("CML_REG_001->Verify that user can register successfully as parent user")
	@Epic("SingUp_EP001")
	@Feature("Signup_001")
	@Story("Verify that user is able to register himself successfully")
	@Step("Signup>>Home page")
	@Severity(SeverityLevel.CRITICAL)
	@Attachment()
	public void TC_CML_SS_020() throws IOException, InterruptedException {
		loginPage = signup.userRegistrationForm("TesSteveUser", "Staging178", "Destiny Planners",
				"4678 James Martin " + "Circle", "Columbus, OH 43215", "US", "Florida", "43215", "46478",
				"614-370-3225", "Teststevesone@mailinator.com", "Pass@123", "Pass@123");

		// Verify that Page url is belongs to Login Page
		// Verify page url after sign up is matched or not

		System.out.print("\n"+"After Sign up Current Page Url is:" + driver.getCurrentUrl()+"\n");

		//Validate email via mailinator
		dashboard = utilMailinator.MailinatorLinkVerification("Teststevesone@mailinator.com");

		driver.navigate().to("https://staging.certifiedmaillabels.com/login");
		loginPage.Login_Testcases("Teststevesone@mailinator.com","Pass@123");

		// Verify page url after Email verification is matched or not
		String ExpectedUrl = "https://staging.certifiedmaillabels.com/";
		String ActualUrl = driver.getCurrentUrl();

		try {
			Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
			System.out.println("User logged in successfully" + "\n");
			System.out.println("->Newly logged in user: Page Url has been verified successfully");

		} catch (AssertionError e) {
			System.out.println("Login Failed->These credentials do not match our records" + "\n");
			throw e; // Re-throw the assertion error to ensure the test fails
		}

		util.TakeScreenshot(driver, " _Signup Page Screenshot_ ");
	}

	@AfterMethod
	public void TearDown() {
	}
}
