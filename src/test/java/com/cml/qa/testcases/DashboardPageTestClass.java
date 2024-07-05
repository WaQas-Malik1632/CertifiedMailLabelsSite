package com.cml.qa.testcases;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.utilities.TestUtil;

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

public class DashboardPageTestClass extends TestBaseClass {
	TestUtil util;
	LoginPageClass login;
	DashboardPageClass dashboard;
	public static Logger log;


	public DashboardPageTestClass() throws IOException {
		log= LogManager.getLogger(DashboardPageTestClass.class);
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		log.info("**** Starting Dashboard Page Test cases execution ****");
		util = new TestUtil();
		intialization();
		login = new LoginPageClass();
		login.PreRequisiteLinkClickLogin();
		dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
	}

	@Test(priority = 1, invocationCount = 1, enabled = true, description = "CML_DASHBOARD_001")
	@Description("CML_DASHBOARD_001->Dashboard Page->Verify that user is able to validate Dashboard Page Title")
	@Epic("Dashboard->EP001")
	@Feature("Dashboard->Feature:001")
	@Story("Dashboard Page TestCases")
	@Step("Login->Dashboard->Verify Page Title")
	@Severity(SeverityLevel.CRITICAL)
	public void VerifyDashboardPageTitle() {
		log.info("Execution of Dashboard Page Title verification 'CML_DASHBOARD_001' Started");
		String DashboardPageTitleIs = dashboard.DashboardTitleVerify();
		try {
			Assert.assertEquals(DashboardPageTitleIs, "Dashboard");
			System.out.println("->Page Title has been successfully verified");
			log.info("Page Title has been successfully verified");
			System.out.println("User is landed on the Dashboard page successfully" + "\n");
			log.info("User is landed on the Dashboard page successfully" + "\n");
		} catch (AssertionError e) {
			System.out.println("User doesn't login->Dashboard Page Title Verification Failed" + "\n");
			throw e; // Re-throw the assertion error to ensure the test fails
		}
		log.info("**** Execution of Dashboard Page Title verification 'CML_DASHBOARD_001' Ended ****");
	}

	@AfterMethod
	public void afterMethod() {
	}
}
