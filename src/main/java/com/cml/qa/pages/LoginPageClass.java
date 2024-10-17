package com.cml.qa.pages;

import java.io.IOException;

import com.cml.qa.utilities.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cml.qa.base.TestBaseClass;

public class LoginPageClass extends TestBaseClass {

    DashboardPageClass dashboard;
    public static Logger log;

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
        log = LogManager.getLogger(LoginPageClass.class);
    }

    public void PreRequisiteLinkClickLogin() {

        btnLoginClick.click();
    }

    public String VerifyLoginTitle() {

        log.info("\n" + "Login Page Title is:" + LoginTitle.getText() + "\n");
        return LoginTitle.getText();
    }

    public boolean VerifyLoginPageLogo() {

        log.info("\n" + "CML Site Logo is visible:" + CMLLogo.isDisplayed() + "\n");
        return CMLLogo.isDisplayed();
    }

    public DashboardPageClass Login_Testcases(String Email, String Pass) throws IOException {

        log.info("Entering Email into the Email field");
        emailAddress.sendKeys(Email);
        log.info("Entering Password into the Password field");
        password.sendKeys(Pass);
        TestUtil.js.executeScript("window.scrollBy(0, 200)", "");

        // If user wants to check the RememberMe
        rememberMe.click();
        btnLoginSubmit.click();
        log.info("Clicked the Login button");

        return dashboard = new DashboardPageClass();
    }

}
