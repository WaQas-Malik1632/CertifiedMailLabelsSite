package com.cml.qa.testcases;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.LandingPageClass;
import com.cml.qa.pages.SignUpPageClass;
import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

public class LandingPageTest extends TestBaseClass {

    TestUtil util;
    SignUpPageClass signup;
    TestUtil_mailinator utilMailinator;
    LandingPageClass landPage;
    public static Logger log;

    public LandingPageTest() throws IOException {

        log = LogManager.getLogger(LandingPageTest.class);
        super();
    }

    @BeforeMethod
    public void beforeMethod() throws IOException, InterruptedException {

        log.info("**** Starting Landing Page Test cases execution ****");
        util = new TestUtil();
        intialization();
     // Headless_Intialization();
        signup = new SignUpPageClass();
        signup.Precondition();
        signup.userRegistrationForm();
        utilMailinator = new TestUtil_mailinator();
        utilMailinator.MailinatorLinkVerificationAndLoginNewUser();
        landPage = new LandingPageClass();
    }

    @Test(priority = 1, invocationCount = 1, enabled = false, description = "TC_CML_SS_001")
    @Description("TC_CML_SS_001->Landing Page->Verify that user is able to validate Landing page UI is showing correctly")
    @Epic("LandingPage->EP001")
    @Feature("LandingPage->Feature:001")
    @Story("LandingPage TestCases")
    @Step("After successful Login->LandingPage->Verify Landing page UI is showing correctly")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_CML_SS_001() throws IOException {

        try {
            log.info("**** Execution of Landing Page UI verification 'TC_CML_SS_001' Started ****");
            landPage.VerifyLandingPageUIElements();
            Assert.assertTrue(true, "UI elements verification done");
            log.info("**** Execution of Landing Page UI verification 'TC_CML_SS_001' Ended ****");
        } catch (Exception e) {
            log.error("Landing Page UI verification Test Case failed: " + e.getMessage());
            util.TakeScreenshot(driver, " _LandingPage Test Failure Screenshot_'TC_CML_SS_001' ");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2, invocationCount = 1, enabled = true, description = "TC_CML_SS_002")
    @Description("TC_CML_SS_002->Landing Page->Verify that right side bar links are working correctly")
    @Epic("LandingPage->EP001")
    @Feature("LandingPage->Feature:001")
    @Story("LandingPage TestCases")
    @Step("After successful Login->LandingPage->Verify Landing page UI is showing correctly")
    @Severity(SeverityLevel.BLOCKER)
    public void TC_CML_SS_002() throws IOException {

        try {
            log.info("**** Execution of Landing Page right side bar links 'TC_CML_SS_002' Started ****");
            landPage.RightSideBarLinks();
            Assert.assertTrue(true, "Right side links verification done");
            log.info("**** Execution of Landing Page UI verification 'TC_CML_SS_002' Ended ****");
        } catch (Exception e) {
            log.error("Landing Page Right side bar links verification Test Case failed: " + e.getMessage());
            util.TakeScreenshot(driver, " _LandingPage Test Failure Screenshot_'TC_CML_SS_002' ");
            throw new RuntimeException(e);
        }
    }
    @Test(priority = 3, invocationCount = 1, enabled = false, description = "TC_CML_SS_003")
    @Description("TC_CML_SS_003->Landing Page->Verify that user clicks on CML logo then app navigates to CML logo")
    @Epic("LandingPage->EP001")
    @Feature("LandingPage->Feature:001")
    @Story("LandingPage TestCases")
    @Step("After successful Login->LandingPage->Verify that user clicks on CML logo then app navigates to CML logo")
    @Severity(SeverityLevel.BLOCKER)
    public void TC_CML_SS_003() throws IOException {

        try {
            log.info("**** Execution of verify that user clicks on CML logo then app navigates to CML logo 'TC_CML_SS_003' Started ****");
            landPage.VerifyCMLLogo();
            Assert.assertTrue(true, "Verification of CML Logo is done");
            log.info("**** Execution of Verify that user clicks on CML logo then app navigates to CML logo 'TC_CML_SS_003' Ended ****");
        } catch (Exception e) {
            log.error("Landing Page TC_CML_SS_003 Test Case failed: " + e.getMessage());
            util.TakeScreenshot(driver, " _LandingPage Test Failure Screenshot_'TC_CML_SS_003' ");
            throw new RuntimeException(e);
        }
    }
    @Test(priority = 4, invocationCount = 1, enabled = false, description = "TC_CML_SS_006")
    @Description("TC_CML_SS_006->Landing Page->Verify that if user click on Get started today on right side bar then app navigates user to registration page")
    @Epic("LandingPage->EP001")
    @Feature("LandingPage->Feature:001")
    @Story("LandingPage TestCases")
    @Step("After successful Login->LandingPage->verify that user clicks on GET STARTED TODAY")
    @Severity(SeverityLevel.BLOCKER)
    public void TC_CML_SS_006() throws IOException {

        try {
            log.info("**** Execution of verify that user clicks on GET STARTED TODAY 'Tc' Started ****");
            landPage.GetStartedToday_ImageClick();
            Assert.assertTrue(true, "Verification of 'GET STARTED TODAY Done' ");
            log.info("**** Execution of verify that user clicks on GET STARTED TODAY 'TC_CML_SS_006' Ended ****");
        } catch (Exception e) {
            log.error("Landing Page TC_CML_SS_006 Test Case failed: " + e.getMessage());
            util.TakeScreenshot(driver, " _LandingPage Test Failure Screenshot_'TC_CML_SS_006' ");
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void afterMethod() {

    }

}
