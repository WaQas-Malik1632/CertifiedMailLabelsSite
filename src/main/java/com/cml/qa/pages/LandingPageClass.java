package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LandingPageClass extends TestBaseClass {

	@FindBy(xpath = "//h1[normalize-space()='Certified Mail Labels']")
	@CacheLookup
	WebElement LandingPagaTitle;

	@FindBy(xpath = "//a[normalize-space()='click resend email']")
	@CacheLookup
	WebElement linkTextClick_VerifyEmail;

	public LandingPageClass() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public String VerifyLandingPageUIElements() {
		return LandingPagaTitle.getText();
	}
}
