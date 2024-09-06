package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DashboardPageClass extends TestBaseClass {
	public static Logger log;

	@FindBy(xpath = "//h1[normalize-space()='Dashboard']")
	@CacheLookup
	WebElement DashboardTitle;

	public DashboardPageClass() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		log = LogManager.getLogger(DashboardPageClass.class);
	}

	public String DashboardTitleVerify() {
		DashboardTitle.isDisplayed();
		return driver.getTitle();
	}
}
