package com.cml.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cml.qa.base.TestBaseClass;

public class LoginPageClass extends TestBaseClass {
	HomePageClass home;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement btnLoginClick;

	@FindBy(xpath = "//div[contains(@class,'required')]//div[@class='col-md-12']//input[@id='email']")
	WebElement emailAdress;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLoginSubmit;

	@FindBy(xpath = "//*[@id=\"remember\"]")
	WebElement rememberMe;

	public LoginPageClass() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public void PreRequisiteLinkClickLogin() {
		btnLoginClick.click();
	}

	public HomePageClass Login_Testcases(String Email, String Pass) throws IOException {
		emailAdress.sendKeys(Email);
		password.sendKeys(Pass);
		
		//After successfull login->it should return Homepage
		btnLoginSubmit.click();
		return home=new HomePageClass();
		
	}
}
