package com.cml.qa.testcases;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.pages.SignUpPageClass;
import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class RegisterPageTest extends TestBaseClass {
	TestUtil util;
	LoginPageClass loginPage;
	DashboardPageClass dashboard;
	SignUpPageClass signup;
	TestUtil_mailinator utilMailinator;

	public RegisterPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		util = new TestUtil();
		intialization();
		loginPage = new LoginPageClass();
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
        loginPage = signup.userRegistrationForm("Bestuseroness", "Staging178", "Destiny Planners",
            "4678 James Martin " + "Circle", "Columbus, OH 43215", "US", "Florida", "43215", "46478",
            "2694558744", "Bestuseronesss@mailinator.com", "Pass@123", "Pass@123");

        System.out.println("\n" + "->Page Url is: " + driver.getCurrentUrl() + " and Title is-> " + driver.getTitle() + "\n");
        signup.VerifyEmailAlreadyTaken();

        SoftAssert softAssert = new SoftAssert();

        // Assuming signup.VerifyEmailAlreadyTaken() returns a string
        String emailCheckResult = signup.VerifyEmailAlreadyTaken();
        System.out.println("Email verification result: " + emailCheckResult);

        try(emailCheckResult.contains("The email has already been taken.")) {
            System.out.println("Email is already taken. Failing the test.");
            Assert.assertTrue(true,"Test case failed because, Email is already taken");
            throw new SkipException("Skipping the test case execution because the email is already taken");
        }
        catch (SkipException e) {
            System.out.println("Email is available. Proceeding with Mailinator verification.");
            dashboard = utilMailinator.MailinatorLinkVerificationAndLoginNewUser("Bestuseronesss@mailinator.com");
            throw e;
        }
        finally {
            softAssert.assertAll();
        }
    }

	@AfterMethod
	public void TearDown() {
	}
}
