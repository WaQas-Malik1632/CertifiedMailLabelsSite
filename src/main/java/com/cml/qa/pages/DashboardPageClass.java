package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DashboardPageClass extends TestBaseClass {

  //  @FindBy(xpath = "//a[normalize-space()='Register']")
   // WebElement click_register;

    public DashboardPageClass() throws IOException {
        super();
        PageFactory.initElements(driver, this);
    }

}
