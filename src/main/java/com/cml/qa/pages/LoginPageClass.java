package com.cml.qa.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cml.qa.base.TestBaseClass;

public class LoginPageClass extends TestBaseClass {
	JavascriptExecutor js=(JavascriptExecutor) driver;
	DashboardPageClass dashboard;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement btnLoginClick;

	@FindBy(xpath = "//div[contains(@class,'required')]//div[@class='col-md-12']//input[@id='email']")
	WebElement emailAddress;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLoginSubmit;

	@FindBy(id = "remember")
	WebElement rememberMe;

	@FindBy(xpath = "//h1[normalize-space()='Login']")
	WebElement LoginTitle;

	@FindBy(xpath = "//img[@alt='Certified Mail Labels']")
	WebElement CMLLogo;

	public LoginPageClass() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public void PreRequisiteLinkClickLogin() {
		btnLoginClick.click();
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

		// After successful login->User should return Dashboard Page
		return dashboard = new DashboardPageClass();
	}

	public String VerifyLoginTitle() {
		System.out.println("\n" + "Login Page Title is:" + LoginTitle.getText() + "\n");
		return LoginTitle.getText();
	}

	public boolean VerifyLoginPageLogo() {
		System.out.println("\n" + "CML Site Logo is visible:" + CMLLogo.isDisplayed() + "\n");
		return CMLLogo.isDisplayed();
	}

}
