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
      //Headless_Intialization();
        signup = new SignUpPageClass();
        signup.Precondition();
        signup.userRegistrationForm();
        utilMailinator = new TestUtil_mailinator();
        utilMailinator.MailinatorLinkVerificationAndLoginNewUser();
        landPage = new LandingPageClass();
    }

    @Test(priority = 1, invocationCount = 1, enabled = true, description = "TC_CML_SS_001")
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

    @AfterMethod
    public void afterMethod() {

    }

}
