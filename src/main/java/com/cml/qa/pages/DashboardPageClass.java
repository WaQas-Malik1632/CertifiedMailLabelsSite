package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DashboardPageClass extends TestBaseClass {

   @FindBy(xpath = "//h1[normalize-space()='Dashboard']")
    WebElement DashboardTitle;

    public DashboardPageClass() throws IOException {
        super();
        PageFactory.initElements(driver, this);
    }
    public String DashboardTitleVerify()
    {
        DashboardTitle.isDisplayed();
        return driver.getTitle();
    }
}
