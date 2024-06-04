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
    public static String Emailaddress;
    public static String password = "Pass@123";

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
    WebElement CombinedName;
    @FindBy(id = "address1")
    @CacheLookup
    WebElement returnAddress;
    @FindBy(id = "address2")
    @CacheLookup
    WebElement addressLine2;
    @FindBy(id = "city")
    @CacheLookup
    WebElement cityName;
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
        String CombFullName = fakeData.name().fullName();
        CombinedName.sendKeys(CombFullName);
        String fullNameWithoutDotsAndSpaces = CombFullName.replaceAll("\\.", "").replaceAll("\\s+", "");
        System.out.println("Full Name: " + CombFullName + "\n" + "FullName Without Space: " + fullNameWithoutDotsAndSpaces);

        // Enter your Return Address
        returnAddress.sendKeys(fakeData.address().fullAddress());
        js.executeScript("window.scrollBy(0, 400)", "");
        String fullAddress = fakeData.address().fullAddress();
        System.out.println("Full Address: " + fullAddress);

        // Example full address format: "1234 Elm Street, Springfield, IL 62704"

        // Split the address to extract city, state, and zip
        String[] addressParts = fullAddress.split(",");
        if (addressParts.length >= 3) {
            String city = addressParts[1].trim();
            String stateAndZip = addressParts[2].trim();

            // Split the state and zip part to get state and zip
            String[] stateZipParts = stateAndZip.split(" ");
            if (stateZipParts.length >= 2) {
                String state = stateZipParts[0].trim();
                String zip = stateZipParts[1].trim();

                System.out.println("City is : " + city);
                cityName.sendKeys(city);

                System.out.println("State Name is : " + state);
                Select stateName = new Select(stateSelection);
                stateName.selectByValue(state);
                System.out.println("\n" + "Selected State option is: " + state);

                System.out.println("ZIP Code is : " + zip);
                ZipCode.sendKeys(zip);
            } else {
                System.out.println("Unable to extract state and zip code");
            }
        } else {
            System.out.println("Unable to extract city, state, and zip code");
        }

        // Enter Phone number
        Phone.sendKeys(fakeData.phoneNumber().cellPhone());
        System.out.println("Phone Number: " + fakeData.phoneNumber().cellPhone());

        // Enter Email Address
        Emailaddress = fullNameWithoutDotsAndSpaces +"@mailinator.com";
        txt_Email.sendKeys(Emailaddress);
        System.out.println("Entered Email Address: " + Emailaddress);

        // Enter Password
        txt_password.sendKeys(password);
        js.executeScript("window.scrollBy(0, 400)", "");

        // Enter Confirm Password
        txt_confirm_Password.sendKeys(password);
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
