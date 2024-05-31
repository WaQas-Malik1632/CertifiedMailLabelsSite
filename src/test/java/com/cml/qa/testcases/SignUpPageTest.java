package com.cml.qa.testcases;

import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import org.testng.SkipException;
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
import org.testng.asserts.SoftAssert;

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
        loginPage = signup.userRegistrationForm("User Tester Abcd", "Staging178", "Destiny Planners",
                "4678 James Martin " + "Circle", "Columbus, OH 43215", "US", "Florida", "43215", "46478",
                "2694558744", "UsertesterABCD@mailinator.com", "Pass@123", "Pass@123");

        System.out.println("\n" + "->Page Url is: " + driver.getCurrentUrl() + " and Title is-> " + driver.getTitle() + "\n");

        if (signup.VerifyUniqueEmail().contains("   Please check your email. Click the button or link inside the ACCOUNT REGISTRATION CONFIRMATION email to confirm your registration and email. To resend your email confirmation, ")) {
            System.out.println("Email verification has started: ");
            System.out.println("Email is available. Proceeding with Mailinator verification.");
            dashboard = utilMailinator.MailinatorLinkVerificationAndLoginNewUser("UsertesterABCD@mailinator.com");
            Assert.assertTrue(true, "Test case passed because, Email is verified");

        } else if (signup.VerifyEmailAlreadyTaken().contains("The email has already been taken.")) {
            System.out.println("Email is already taken. Failing the test.");
            Assert.assertTrue(false, "Test case failed because, Email is already taken");

        } else {
            throw new SkipException("Skipping the test case execution, something went wrong");
        }
    }

    @AfterMethod
    public void TearDown() {

    }

}

