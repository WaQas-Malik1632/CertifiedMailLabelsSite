package com.cml.qa.testcases;

import com.cml.qa.pages.DashboardPageClass;
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
	LoginPageClass login;
	DashboardPageClass dashboard;

	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		login = new LoginPageClass();
		login.PreRequisiteLinkClickLogin();
		dashboard = new DashboardPageClass();
	}

	// Validate that user can login to the system using valid credentials
	@Test(invocationCount = 1, priority = 3, enabled = true, description = "Login Test#1", groups = { "Smoke_Suite" })
	@Description("Validate that user can log in using valid credentials: Email:Test@gmail.com , password: Pass123")
	@Epic("EP001")
	@Feature("Feature:001")
	@Story("Login TestCases")
	@Step("Hit Site Url->Login")
	@Severity(SeverityLevel.BLOCKER)
	public void LoginTest() throws IOException {
		dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
		String ExpectedUrl = "https://staging.certifiedmaillabels.com/user/dashboard";
		Assert.assertEquals(dashboard, driver.getCurrentUrl(), ExpectedUrl);
		System.out.print("Current Page Url is:" + driver.getCurrentUrl());
	}

	@Test(priority = 2, invocationCount = 1, enabled = false, description = "Login Page Test #3", groups = {
			"Smoke_Suite" })
	@Description("Login Page->Verify that user is able to validate Login Page Title")
	@Epic("EP001")
	@Feature("Feature:001")
	@Story("Login Page TestCases")
	@Step("Login->Home->Verify Elements")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateLoginTitle() throws IOException {
		String LoginTitle = login.VerifyLoginTitle();
		Assert.assertEquals(LoginTitle, "Login", "Login");
	}

	@Test(priority = 1, invocationCount = 1, enabled = false, description = "Login Page Test #2", groups = {
			"Smoke_Suite" })
	@Description("Login Page->Verify that user is able to validate Login Page Logo")
	@Epic("EP001")
	@Feature("Feature:001")
	@Story("Login Page TestCases")
	@Step("Login->Verify Elements")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateLoginLogo() throws IOException {
		boolean flag = login.VerifyLoginPageLogo();
		Assert.assertTrue(flag);
	}

	@AfterMethod
	public void afterMethod() {
	}

}
