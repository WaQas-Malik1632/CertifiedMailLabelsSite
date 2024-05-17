package com.cml.qa.testcases;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

public class DashboardPageTestClass extends TestBaseClass {
	LoginPageClass login;
	DashboardPageClass dashboard;


	public DashboardPageTestClass() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		login.PreRequisiteLinkClickLogin();
		dashboard = login.Login_Testcases(prop.getProperty("email"), prop.getProperty("password"));
	}

	@Test
	public void VerifyDashbordPageTitle() {


	}

	@AfterMethod
	public void afterMethod() {
	}
}
