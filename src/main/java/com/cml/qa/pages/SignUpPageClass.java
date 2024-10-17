package com.cml.qa.pages;

import java.io.IOException;
import java.time.Duration;

import com.cml.qa.utilities.TestUtil;
import com.cml.qa.utilities.TestUtil_mailinator;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.cml.qa.base.TestBaseClass;

public class SignUpPageClass extends TestBaseClass {

    Faker fakeData = new Faker();
    public static String Emailaddress;
    public static String password = "Pass@123";
    public static Logger log;

    public SignUpPageClass() throws IOException {

        super();
        PageFactory.initElements(driver, this);
        log = LogManager.getLogger(SignUpPageClass.class);
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
        log.info("\n" + "SignUp Page Title is:" + registerTitle.getText() + "\n");
        return registerTitle.getText();
    }

    public LoginPageClass userRegistrationForm() throws IOException {
        // Enter First Name
        fName.sendKeys(fakeData.name().firstName());
        log.info("First Name: " + fakeData.name().firstName());
        // Enter Last Name
        lName.sendKeys(fakeData.name().lastName());
        log.info("Last Name: " + fakeData.name().lastName());

        // Enter Combined First & Last Name
        String CombFullName = fakeData.name().fullName();
        CombinedName.sendKeys(CombFullName);
        String fullNameWithoutDotsAndSpaces = CombFullName.replaceAll("\\.", "").replaceAll("\\s+", "");
        log.info("Full Name: " + CombFullName + " ->FullName Without Space: " + fullNameWithoutDotsAndSpaces);

        // Enter your Return Address
        returnAddress.sendKeys(fakeData.address().fullAddress());
        TestUtil.js.executeScript("window.scrollBy(0, 400)", "");
        log.info("Scrolling the window vertically");
        String fullAddress = fakeData.address().fullAddress();
        log.info("Full Address: " + fullAddress);

        // Example full address format: "1234 Elm Street, Springfield, IL 62704"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

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

                log.info("City is : " + city);
                cityName.sendKeys(city);

                log.info("State Name is : " + state);
                Select stateName = new Select(stateSelection);
                stateName.selectByValue(state);
                log.info("\n" +"Selected State Option is:"+ state);
                ZipCode.sendKeys(zip);
                log.info("ZIP Code is : " + zip);
            } else {
                log.error("Unable to extract state and zip code");
            }
        }
        // Enter Phone number
        Phone.sendKeys(fakeData.phoneNumber().cellPhone());
        log.info("Phone Number: " + fakeData.phoneNumber().cellPhone());

        // Enter Email Address
        Emailaddress = fullNameWithoutDotsAndSpaces +"@mailinator.com";
        txt_Email.sendKeys(Emailaddress);
        log.info("Entered Email Address: " + Emailaddress);

        // Enter Password
        txt_password.sendKeys(password);
        log.info("Entered password: " + password);
        TestUtil.js.executeScript("window.scrollBy(0, 400)", "");

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
