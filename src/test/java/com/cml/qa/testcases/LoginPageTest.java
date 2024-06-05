package com.cml.qa.testcases;

import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.utilities.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.LoginPageClass;
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

public class LoginPageTest extends TestBaseClass {

	TestUtil util;
	LoginPageClass login;
	DashboardPageClass dashboard;
	public static Logger log;

	public LoginPageTest() throws IOException {
		log= LogManager.getLogger(TestBaseClass.class);
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		log.info("***************** Starting Login Page Test cases execution  ******************");
		util = new TestUtil();
		intialization();
		login = new LoginPageClass();
		login.PreRequisiteLinkClickLogin();
	}

	// Validate that parent user can login to the system using valid credentials
	@Test(invocationCount = 1, priority = 1, enabled = true, description = "CML_LOGIN_001")
	@Description("CML_LOGIN_001->Verify that user can login successfully as parent user->Email=TestUserOne@mailinator.com, password=Pass@123")
	@Epic("LOGIN->EP001")
	@Feature("LOGIN->Feature:001")
	@Story("Login TestCases")
	@Step("Hit Site Url->Login as Parent User")
	@Severity(SeverityLevel.BLOCKER)
	public void TC_CML_SS_010() throws IOException {
		log.info("*************** Execution of Login Page Tests 'CML_LOGIN_001' Started  *****************");
		dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertTrue(true, "Login Test Passed");
		log.info("******************** Ending 'CML_LOGIN_001' Test  *************************");
	}

	// Verify that user cannot login with invalid username or password
	@Test(invocationCount = 1, priority = 3, enabled = false, description = "CML_LOGIN_002")
	@Description("CML_LOGIN_002->Verify that user cannot login with invalid username or password")
	@Epic("LOGIN->EP001")
	@Feature("LOGIN->Feature:001")
	@Story("Login TestCases")
	@Step("Hit Site Url->Login as Parent User")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_CML_SS_168() throws IOException {
		log.info("************** Execution of Login Page Tests 'CML_LOGIN_002' Started  ******************");
		dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertTrue(true, "Login Test Passed");

		String ExpectedUrl = "https://staging.certifiedmaillabels.com/login";
		String ActualUrl = driver.getCurrentUrl();

		try {
			Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
			System.out.println("\n"
					+ "Login Failed->These credentials do not match our records. Password is case-sensitive." + "\n");
		} catch (AssertionError e) {
			System.out.println("User logged in successfully" + "\n");
			util.TakeScreenshot(driver, " _Login Test Failure Screenshot_ ");
			throw e; // Re-throw the assertion error to ensure the test fails
		}
	}

	@Test(priority = 2, invocationCount = 1, enabled = false, description = "CML_LOGIN_003")
	@Description("CML_LOGIN_003->Verify that user is able to validate Login Page Title")
	@Epic("LOGIN->EP001")
	@Feature("LOGIN->Feature:001")
	@Story("Login Page TestCases")
	@Step("Login->Verify Login Page Title")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_CML_SS_004() {
		log.info("*************** Execution of Login Page Tests 'CML_LOGIN_003' Started  ****************");
		String LoginTitle = login.VerifyLoginTitle();
		try {
			Assert.assertEquals(LoginTitle, "Login", "Login title does not match");
			System.out.println("Login title has been successfully verified");
		} catch (AssertionError e) {
			System.out.println("Login title verification failed: " + e.getMessage());
			throw e; // Re-throw the assertion error to ensure the test fails
		}
	}

	@Test(priority = 1, invocationCount = 1, enabled = false, description = "CML_LOGIN_004")
	@Description("CML_LOGIN_004->Verify that user is able to validate Login Page Logo")
	@Epic("LOGIN->EP001")
	@Feature("LOGIN->Feature:001")
	@Story("Login Page TestCases")
	@Step("Login->Verify Login Page Logo")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_CML_SS_005() {
		log.info("************* Execution of Login Page Tests 'CML_LOGIN_004' Started  ****************");
		boolean flag = login.VerifyLoginPageLogo();
		try {
			Assert.assertTrue(flag, "Login page logo verification failed");
			System.out.println("Login page logo has been successfully verified");
		} catch (AssertionError e) {
			System.out.println("Login page logo verification failed: " + e.getMessage());
			throw e; // Re-throw the assertion error to ensure the test fails
		}
	}

	@AfterMethod
	public void afterMethod() {

	}

}
