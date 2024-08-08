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
        log.info("**** Starting SignUp Page Test Cases Execution ****");
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {

        util = new TestUtil();
        intialization();
        loginPage = new LoginPageClass();
        signup = new SignUpPageClass();
        signup.Precondition();
        utilMailinator = new TestUtil_mailinator();
        landPage = new LandingPageClass();
    }

    @Test(priority = 1, invocationCount = 1, enabled = false, description = "CML_REG_001")
    @Description("CML_REG_001->SignUp Page->Verify that user is able to validate signUp Page Title")
    @Epic("SignUp->EP001")
    @Feature("SignUp->Feature:001")
    @Story("SignUp Page TestCases")
    @Step("Login->Home->Verify Elements")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_002() throws IOException {
        log.info("Execution of SignUp Page Title verification 'CML_REG_001' Started");
        String SignUpTitleVerify = signup.VerifySignUpTitle();
        try {
            Assert.assertEquals(SignUpTitleVerify, "Register", "SignUp title does not match");
            log.info("SignUp title has been successfully verified");
        } catch (AssertionError e) {
            log.info("SignUp title verification failed: " +e.getMessage());
            util.TakeScreenshot(driver," Screenshot_SignUpPage Failed TestCase 'CML_REG_001'");
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of SignUp Page Title verification 'CML_REG_001' Ended");
    }

    @Test(priority = 1, description = "TC_CML_SS_020", enabled = true, invocationCount = 1)
    @Description("TC_CML_SS_020->Verify that user can register successfully as parent user")
    @Epic("SingUp_EP001")
    @Feature("Signup_001")
    @Story("Verify that user is able to register himself successfully")
    @Step("Signup>>Home page")
    @Severity(SeverityLevel.CRITICAL)
    @Attachment()
    public void TC_CML_SS_020() throws InterruptedException, IOException {

        log.info("Execution of SignUp Page Title verification 'TC_CML_SS_020' Started");
        loginPage = signup.userRegistrationForm();

        log.info("\n" + "->Page Url is: " + driver.getCurrentUrl() + " and Title is-> " + driver.getTitle() + "\n");

        if (signup.VerifyUniqueEmail().contains("   Please check your email. Click the button or link inside the ACCOUNT REGISTRATION CONFIRMATION email to confirm your registration and email. To resend your email confirmation, ")) {
            log.info("Email is available. Proceeding with Mailinator verification");
            landPage = utilMailinator.MailinatorLinkVerificationAndLoginNewUser();
            log.info("mail is verified successfully");
            String ExpectedUrl = "https://staging.certifiedmaillabels.com/";
            Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);
            util.TakeScreenshot(driver, "TC_CML_SS_020_LinkVerifySuccess_");
        } else if (signup.VerifyEmailAlreadyTaken().contains("The email has already been taken.")) {
            log.info("Email is already taken->Test failed.");
            util.TakeScreenshot(driver," Screenshot_SignUpPage Failed TestCase 'TC_CML_SS_020'");
            Assert.assertTrue(false, "Test case failed because, Email is already taken");
        } else {
            //	throw new SkipException("Skipping the test case execution, something went wrong");
            log.error("Skipping the test case execution, something went wrong");
            util.TakeScreenshot(driver," Screenshot_SignUpPage Failed TestCase 'TC_CML_SS_020'");
        }
        log.info("**** Execution of Register successfully as parent user 'TC_CML_SS_020' Ended ****");
    }

    @AfterMethod
    public void TearDown() {

    }

}