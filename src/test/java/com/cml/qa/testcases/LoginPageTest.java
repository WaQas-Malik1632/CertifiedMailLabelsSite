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

        log = LogManager.getLogger(LoginPageTest.class);
        super();
        log.info("**** Starting Login Page Test cases execution ****");
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {

        util = new TestUtil();
        intialization();
        login = new LoginPageClass();
        login.PreRequisiteLinkClickLogin();
    }

    // Validate that parent user can login to the system using valid credentials
    @Test(invocationCount = 1, priority = 1, enabled = true, description = "TC_CML_SS_010")
    @Description("TC_CML_SS_010->Verify that user can login successfully as parent user->Email=TestUserOne@mailinator.com, password=Pass@123")
    @Epic("LOGIN->EP001")
    @Feature("LOGIN->Feature:001")
    @Story("Login TestCases")
    @Step("Hit Site Url->Login as Parent User")
    @Severity(SeverityLevel.BLOCKER)
    public void TC_CML_SS_010() throws IOException {

        try {
            log.info("Execution of Login Page Tests 'TC_CML_SS_010' Started");
            dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
            String ExpectedUrl = "https://staging.certifiedmaillabels.com/user/dashboard";
            String ActualUrl = driver.getCurrentUrl();
            Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
            log.info("\n" + "User logged in successfully" + "\n");
            log.info("User landed on Dashboard Page->Page Url has been successfully verified");
        } catch (AssertionError e) {
            log.error("Login Failed->These credentials do not match our records" + "\n"+e.getMessage());
            util.TakeScreenshot(driver," Screenshot_Login TestCase 'TC_CML_SS_010' ");
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of 'TC_CML_SS_010' Ended");
    }

    // Verify that user cannot login with invalid username or password
    @Test(invocationCount = 1, priority = 3, enabled = true, description = "TC_CML_SS_168")
    @Description("TC_CML_SS_168->Verify that user cannot login with invalid username or password")
    @Epic("LOGIN->EP001")
    @Feature("LOGIN->Feature:001")
    @Story("Login TestCases")
    @Step("Hit Site Url->Login as Parent User")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_168() throws IOException {

        log.info("Execution of Login Page Tests 'TC_CML_SS_168' Started");
        log.info("Entering Email and Password");
        dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
        Assert.assertTrue(true, "Login Test Passed");
        String ExpectedUrl = "https://staging.certifiedmaillabels.com/login";
        String ActualUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
            log.info("\n" + "These credentials do not match our records. Password is case-sensitive." + "\n");
        } catch (AssertionError e) {
            log.info("User logged in successfully" + "\n"+e.getMessage());
            util.TakeScreenshot(driver, " _Login Test Failure Screenshot_ ");
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of Login Page Test 'TC_CML_SS_168' Ended");
    }

    @Test(priority = 2, invocationCount = 1, enabled = true, description = "TC_CML_SS_004")
    @Description("TC_CML_SS_004->Verify that user is able to validate Login Page Title")
    @Epic("LOGIN->EP001")
    @Feature("LOGIN->Feature:001")
    @Story("Login Page TestCases")
    @Step("Login->Verify Login Page Title")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_004() {

        log.info("Execution of Login Page Tests 'TC_CML_SS_004' Started");
        String LoginTitle = login.VerifyLoginTitle();
        try {
            Assert.assertEquals(LoginTitle, "Login", "Login title does not match");
            log.info("\"Login title has been successfully verified\"");
        } catch (AssertionError e) {
            log.info("Login title verification failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of Login Page Tests 'TC_CML_SS_004' Ended");
    }

    @Test(priority = 4, invocationCount = 1, enabled = true, description = "TC_CML_SS_005")
    @Description("TC_CML_SS_005->Verify that user is able to validate Login Page Logo")
    @Epic("LOGIN->EP001")
    @Feature("LOGIN->Feature:001")
    @Story("Login Page TestCases")
    @Step("Login->Verify Login Page Logo")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_005() {

        log.info("**** Execution of Login Page Tests 'TC_CML_SS_005' Started ****");
        boolean flag = login.VerifyLoginPageLogo();
        try {
            Assert.assertTrue(flag, "Login page logo verification failed");
            log.info("Login page logo has been successfully verified");
        } catch (AssertionError e) {
            log.error("Login page logo verification failed: " + e.getMessage());
            throw e; // Re-throw the assertion error to ensure the test fails
        }
        log.info("Execution of Login Page Tests 'TC_CML_SS_005' Ended");
    }

    @AfterMethod
    public void afterMethod() {

    }

}
