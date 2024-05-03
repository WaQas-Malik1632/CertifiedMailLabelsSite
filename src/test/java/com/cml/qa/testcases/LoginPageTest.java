package com.cml.qa.testcases;

import org.testng.annotations.Test;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.HomePageClass;
import com.cml.qa.pages.LoginPageClass;

import org.testng.annotations.BeforeMethod;
import java.io.IOException;

import org.testng.annotations.AfterMethod;

public class LoginPageTest extends TestBaseClass {
	LoginPageClass login;
	HomePageClass homepage;
	String sheetName = "LoginData";

	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		intialization();
		login=new LoginPageClass();
	}

	@Test(priority = 3)
	public void LoginTest() throws IOException {
		homepage = login.Login_Testcases(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void afterMethod() {
	}

}
