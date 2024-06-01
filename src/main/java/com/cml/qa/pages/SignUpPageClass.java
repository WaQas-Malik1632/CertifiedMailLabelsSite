package com.cml.qa.pages;

import java.io.IOException;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.cml.qa.base.TestBaseClass;

public class SignUpPageClass extends TestBaseClass {
    Faker fakeData = new Faker();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public SignUpPageClass() throws IOException {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Register']")
    @CacheLookup
    WebElement click_register;

    @FindBy(xpath = "/html/body/div[1]/div[1]/section/div/div/div[1]/h1")
    @CacheLookup
    WebElement registerTitle;

    @FindBy(id = "first_name")
    @CacheLookup
    WebElement fName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    @CacheLookup
    WebElement lName;

    @FindBy(id = "company")
    @CacheLookup
    WebElement orgNameOrCombinedName;

    @FindBy(id = "address1")
    @CacheLookup
    WebElement returnAddress;

    @FindBy(id = "address2")
    @CacheLookup
    WebElement addressLine2;

    @FindBy(id = "city")
    @CacheLookup
    WebElement city;

    @FindBy(id = "state")
    @CacheLookup
    WebElement stateSelection;

    @FindBy(xpath = "//*[@id=\"state\"]/option[15]")
    @CacheLookup
    WebElement selectedTextValue;

    @FindBy(id = "zip")
    @CacheLookup
    WebElement ZipCode;

    @FindBy(xpath = "//input[@placeholder='Zip+4']")
    @CacheLookup
    WebElement zip4;

    @FindBy(id = "phone")
    @CacheLookup
    WebElement Phone;

    @FindBy(id = "email")
    @CacheLookup
    WebElement txt_Email;

    @FindBy(id = "password")
    @CacheLookup
    WebElement txt_password;

    @FindBy(id = "password_confirmation")
    @CacheLookup
    WebElement txt_confirm_Password;

    @FindBy(id = "agree")
    @CacheLookup
    WebElement checkAgree;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    @CacheLookup
    WebElement btn_RegisterSubmit;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div[1]")
    @CacheLookup
    WebElement EmailVerifiedSuccessText;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div[1]/ul/li")
    @CacheLookup
    WebElement EmailAlreadyVerifiedText;

    public void Precondition() {

        click_register.click();
    }

    public String VerifySignUpTitle() {

        System.out.println("\n" + "Login Page Title is:" + registerTitle.getText() + "\n");
        return registerTitle.getText();
    }

    public LoginPageClass userRegistrationForm() throws IOException {
        // Enter First Name
        fName.sendKeys(fakeData.name().firstName());
        System.out.println("First Name: " + fakeData.name().firstName());

        // Enter Last Name
        lName.sendKeys(fakeData.name().lastName());
        System.out.println("Last Name: " + fakeData.name().lastName());

        // Enter Combined First & Last Name
        orgNameOrCombinedName.sendKeys(fakeData.name().fullName());
        System.out.println("Full Name: " + fakeData.name().fullName());

        // Enter your Return Address
        returnAddress.sendKeys(fakeData.address().fullAddress());
        System.out.println("Full Address : " + fakeData.address().fullAddress());

        js.executeScript("window.scrollBy(0, 400)", "");
        // Enter Address Line 2
        addressLine2.sendKeys(fakeData.address().secondaryAddress());
        System.out.println("Address2: " + fakeData.address().secondaryAddress());

        // Enter City Name
        city.sendKeys(fakeData.address().city());
        System.out.println("City Name: " + fakeData.address().city());
        // Select state
        Select stateName = new Select(stateSelection);
        stateName.selectByVisibleText("Florida");
        System.out.println("\n" + "Selected State option is: " + selectedTextValue.getText());
        // Scrolling down the page till the element is found
        // js.executeScript("arguments[0].scrollIntoView();", comp);

        // Enter Zip code
        ZipCode.sendKeys(fakeData.address().zipCode());
        System.out.println("Zip Code: " + fakeData.address().zipCode());

        // Enter Zip+4 code
        zip4.sendKeys(fakeData.address().zipCode());
        System.out.println("Zip4 code: " + fakeData.address().zipCode());

        // Enter Phone number
        Phone.sendKeys(fakeData.phoneNumber().cellPhone());
        System.out.println("Phone Number: " + fakeData.phoneNumber().cellPhone());

        // Enter Email Address
        txt_Email.sendKeys(fakeData.internet().emailAddress());
        System.out.println("Email Address: " + fakeData.internet().emailAddress());

        // Enter Password
        txt_password.sendKeys("Pass@123");

        js.executeScript("window.scrollBy(0, 400)", "");

        // Enter Confirm Password
        txt_confirm_Password.sendKeys("Pass@123");

        // Check Agree to our Terms Of Use and Privacy Policy
        checkAgree.click();
        // Click on Register button to submit the form
        btn_RegisterSubmit.click();

        return new LoginPageClass();
    }

    public String VerifyUniqueEmail() {
        return EmailVerifiedSuccessText.getText();
    }

    public String VerifyEmailAlreadyTaken() {
        return EmailAlreadyVerifiedText.getText();
    }
}
