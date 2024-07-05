package com.cml.qa.testcases;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.LandingPageClass;
import com.cml.qa.pages.SignUpPageClass;
import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import io.qameta.allure.*;
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
		log= LogManager.getLogger(LandingPageTest.class);
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException {
		log.info("**** Starting Landing Page Test cases execution ****");
		util = new TestUtil();
		intialization();
		signup = new SignUpPageClass();
		signup.Precondition();
		signup.userRegistrationForm();
		utilMailinator = new TestUtil_mailinator();
		utilMailinator.MailinatorLinkVerificationAndLoginNewUser();
		landPage = new LandingPageClass();
	}


	@Test(priority = 1, invocationCount = 1, enabled = true, description = "CML_LandingPage_001")
	@Description("CML_LandingPage_001->Landing Page->Verify that user is able to validate Landing Page Title")
	@Epic("LandingPage->EP001")
	@Feature("LandingPage->Feature:001")
	@Story("LandingPage TestCases")
	@Step("After successful Login->LandingPage->Verify Page Title")
	@Severity(SeverityLevel.CRITICAL)
	public void VerifyPageTitle() throws IOException {
		log.info("**** Execution of Landing Page Title verification 'CML_LandingPage_001' Started ****");
		try {
			String LandingPageClassTitle=landPage.VerifyLandingPageUIElements();
			Assert.assertEquals(LandingPageClassTitle,"Certified Mail Labels");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		log.info("**** Execution of Landing Page Title verification 'CML_LandingPage_001' Ended ****");
	}

	@AfterMethod
	public void afterMethod() {
	}

}
