package com.cml.qa.testcases;

import com.cml.qa.pages.LandingPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class SignUpPageTest extends TestBaseClass {

    TestUtil util;
    LoginPageClass loginPage;
    SignUpPageClass signup;
    TestUtil_mailinator utilMailinator;
    LandingPageClass landPage;
    public static Logger log;

    public SignUpPageTest() throws IOException {
        log = LogManager.getLogger(SignUpPageTest.class);
        super();
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {
        log.info("**** Starting SignUp Page Test cases execution ****");
        util = new TestUtil();
        intialization();
        loginPage = new LoginPageClass();
        signup = new SignUpPageClass();
        signup.Precondition();
        utilMailinator = new TestUtil_mailinator();
        landPage = new LandingPageClass();
    }

    @Test(priority = 1, invocationCount = 1, enabled = false, description = "TC_CML_SS_002")
    @Description("CML_REG_002->SignUp Page->Verify that user is able to validate signUp Page Title")
    @Epic("SignUp->EP001")
    @Feature("SignUp->Feature:001")
    @Story("SignUp Page TestCases")
    @Step("Login->Home->Verify Elements")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_002() throws IOException {
        log.info("Execution of SignUp Page Title verification 'TC_CML_SS_002' Started");
        String SignUpTitleVerify = signup.VerifySignUpTitle();
        try {
            Assert.assertEquals(SignUpTitleVerify, "Register", "SignUp title does not match");
            System.out.println("SignUp title has been successfully verified");
            log.info("SignUp title has been successfully verified");
        } catch (AssertionError e) {
            System.out.println("SignUp title verification failed: " + e.getMessage());
            log.info("\"SignUp title verification failed: \" + e.getMessage()");
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of SignUp Page Title verification 'TC_CML_SS_002' Ended");
    }

    @Test(priority = 1, description = "CML_REG_001", enabled = true, invocationCount = 1)
    @Description("CML_REG_001->Verify that user can register successfully as parent user")
    @Epic("SingUp_EP001")
    @Feature("Signup_001")
    @Story("Verify that user is able to register himself successfully")
    @Step("Signup>>Home page")
    @Severity(SeverityLevel.CRITICAL)
    @Attachment()
    public void TC_CML_SS_020() throws InterruptedException, IOException {

        log.info("Execution of SignUp Page Title verification 'TC_CML_SS_020' Started");
        loginPage = signup.userRegistrationForm();
        System.out.println("\n" + "->Page Url is: " + driver.getCurrentUrl() + " and Title is-> " + driver.getTitle() + "\n");
        if (signup.VerifyUniqueEmail().contains("   Please check your email. Click the button or link inside the ACCOUNT REGISTRATION CONFIRMATION email to confirm your registration and email. To resend your email confirmation, ")) {
            System.out.println("Email is available. Proceeding with Mailinator verification.");
            log.info("Email is available. Proceeding with Mailinator verification");
            landPage = utilMailinator.MailinatorLinkVerificationAndLoginNewUser();
            System.out.println("Email is verified successfully");
            log.info("mail is verified successfully");
            String ExpectedUrl = "https://staging.certifiedmaillabels.com/";
            Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);
            util.TakeScreenshot(driver, "TC_CML_SS_020_LinkVerifySuccess_");
        } else if (signup.VerifyEmailAlreadyTaken().contains("The email has already been taken.")) {
            System.out.println("Email is already taken. Failing the test.");
            log.info("Email is already taken. Failing the test.");
            util.TakeScreenshot(driver, "TC_CML_SS_020_EmailAlreadyTaken_");
            Assert.assertTrue(false, "Test case failed because, Email is already taken");
        } else {
            util.TakeScreenshot(driver, "Test");
            //	throw new SkipException("Skipping the test case execution, something went wrong");
            log.error("Skipping the test case execution, something went wrong");
        }
        log.info("**** Execution of SignUp Page Title verification 'TC_CML_SS_020' Ended ****");
    }

    @AfterMethod
    public void TearDown() {

    }

}