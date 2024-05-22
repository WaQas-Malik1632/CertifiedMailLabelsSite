package com.cml.qa.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cml.qa.base.TestBaseClass;
import org.testng.Assert;

public class LoginPageClass extends TestBaseClass {
	JavascriptExecutor js=(JavascriptExecutor) driver;
	DashboardPageClass dashboard;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	@CacheLookup
	WebElement btnLoginClick;

	@FindBy(xpath = "//div[contains(@class,'required')]//div[@class='col-md-12']//input[@id='email']")
	@CacheLookup
	WebElement emailAddress;

	@FindBy(xpath = "//input[@id='password']")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	@CacheLookup
	WebElement btnLoginSubmit;

	@FindBy(id = "remember")
	@CacheLookup
	WebElement rememberMe;

	@FindBy(xpath = "//h1[normalize-space()='Login']")
	@CacheLookup
	WebElement LoginTitle;

	@FindBy(xpath = "//img[@alt='Certified Mail Labels']")
	@CacheLookup
	WebElement CMLLogo;

	public LoginPageClass() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public void PreRequisiteLinkClickLogin() {
		btnLoginClick.click();
	}

	public String VerifyLoginTitle() {
		System.out.println("\n" + "Login Page Title is:" + LoginTitle.getText() + "\n");
		return LoginTitle.getText();
	}

	public boolean VerifyLoginPageLogo() {
		System.out.println("\n" + "CML Site Logo is visible:" + CMLLogo.isDisplayed() + "\n");
		return CMLLogo.isDisplayed();
	}

	public DashboardPageClass Login_Testcases(String Email, String Pass) throws IOException {
		emailAddress.sendKeys(Email);
		password.sendKeys(Pass);

		js.executeScript("window.scrollBy(0, 400)", "");

		// Scroll the windows to the specific element
		// js1.executeScript("arguments[0].click();", btnLoginSubmit);

		// If user wants to check the RememberMe
		rememberMe.click();
		btnLoginSubmit.click();
/*
		String ExpectedUrl = "https://staging.certifiedmaillabels.com/user/dashboard";
		String ActualUrl = driver.getCurrentUrl();

		try {
			Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
			System.out.println("\n"+"User logged in successfully" + "\n");
			System.out.println("User landed on Dashboard Page->Page Url has been successfully verified");
		} catch (AssertionError e) {
			System.out.println("Login Failed->These credentials do not match our records" + "\n");
			//  util.TakeScreenshot(driver," _Login Test Failure Screenshot_ ");
			throw e; // Re-throw the assertion error to ensure the test fails
		}


 */
		// After successful login->User should return Dashboard Page
		return dashboard = new DashboardPageClass();
	}
}
