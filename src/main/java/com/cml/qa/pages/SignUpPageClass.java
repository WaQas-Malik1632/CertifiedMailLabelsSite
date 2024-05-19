package com.cml.qa.pages;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.cml.qa.base.TestBaseClass;

public class SignUpPageClass extends TestBaseClass {

    LoginPageClass loginpage;

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

    @FindBy(xpath = "//a[normalize-space()='click resend email']")
    @CacheLookup
    WebElement linkTextClick_VerifyEmail;

    @FindBy(xpath = "//input[@id='inbox_field']")
    @CacheLookup
    WebElement MailinatorIb;

    @FindBy(xpath = "//button[@class='primary-btn']")
    @CacheLookup
    WebElement ClickGo;

    @FindBy(xpath = "//td[normalize-space()='CERTIFIED MAIL LABELS']")
    @CacheLookup
    WebElement ClickIbMsgResult;

    @FindBy(xpath = "//a[@class='button button-primary']")
    @CacheLookup
    WebElement LinkTextClickToVerifyEmail;

    @FindBy(xpath = "//h1[normalize-space()='Error - User Already Verified']")
    @CacheLookup
    WebElement VerifyEmailAlreadyVerifiedText;

    //p[normalize-space()='Your account is already verified']
    //https://staging.certifiedmaillabels.com/user-verification/verified



    public void Precondition() {

        click_register.click();
    }

    public String VerifySignUpTitle() {

        System.out.println("\n" + "Login Page Title is:" + registerTitle.getText() + "\n");
        return registerTitle.getText();
    }

    public LoginPageClass userRegistrationForm(String Fname, String Lname, String CombName, String ReturnAddress, String Address2,
                                  String City, String SelectState, String Zip, String Zip4, String PhoneNumber, String Email, String Pass,
                                     String Confpass) throws IOException {
        // Enter First Name
        fName.sendKeys(Fname);
        // Enter Last Name
        lName.sendKeys(Lname);
        // Enter Organization or Combined First & Last Name
        orgNameOrCombinedName.sendKeys(CombName);
        // Enter your Return Address
        returnAddress.sendKeys(ReturnAddress);
        js.executeScript("window.scrollBy(0, 400)", "");
        // Enter Address Line 2
        addressLine2.sendKeys(Address2);
        // Enter City Name
        city.sendKeys(City);
        // Select state
        Select stateName = new Select(stateSelection);
        stateName.selectByVisibleText(SelectState);
        System.out.println("Selected State option is: " + selectedTextValue.getText());
        // Scrolling down the page till the element is found
        // js.executeScript("arguments[0].scrollIntoView();", comp);
        // Enter Zip code
        ZipCode.sendKeys(Zip);
        // Enter Zip+4 code
        zip4.sendKeys(Zip4);
        // Enter Phone number
        Phone.sendKeys(PhoneNumber);
        // Enter Email Address
        txt_Email.sendKeys(Email);
        // Enter Password
        txt_password.sendKeys(Pass);
        js.executeScript("window.scrollBy(0, 400)", "");
        // Enter Confirm Password
        txt_confirm_Password.sendKeys(Confpass);
        // Check Agree to our Terms Of Use and Privacy Policy
        checkAgree.click();
        // Click on Regsiter button to submit the form
        btn_RegisterSubmit.click();

        return new LoginPageClass();
    }
}
