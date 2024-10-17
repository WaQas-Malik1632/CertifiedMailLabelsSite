package com.cml.qa.utilities;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.LandingPageClass;
import com.cml.qa.pages.LoginPageClass;
import com.cml.qa.pages.SignUpPageClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class TestUtil_mailinator extends TestBaseClass {
	LoginPageClass loginPage;
	public static Logger log;

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
	WebElement ClickIbMsgTextButton;

	@FindBy(xpath = "//a[@class='button button-primary']")
	@CacheLookup
	WebElement LinkTextClickToVerifyEmail;

	public TestUtil_mailinator() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		log = LogManager.getLogger(TestUtil_mailinator.class);
	}

	public LandingPageClass MailinatorLinkVerificationAndLoginNewUser() throws InterruptedException, IOException {

		// After signup, user can click to resend the email link again
		// linkTextClick_VerifyEmail.click();

		driver.navigate().to(prop.getProperty("MailinatorInboxUrl"));
		TestUtil.TakeScreenshot(driver, "'_Mailinator Test Failure Screenshot'");

		driver.navigate().to("https://www.mailinator.com/v4/public/inboxes.jsp");
		log.info("\n" + "User is navigating to the Mailinator for the Email verification " + "\n");

		// Getting the Id of parent windows here
		String ParentWindowId = driver.getWindowHandle();

		//====Static is the properties of the class, we don't have instance of the pageClass====

		//Pass the Email to Mailinator entered ny the user
		MailinatorIb.sendKeys(SignUpPageClass.Emailaddress);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		ClickGo.click();

		// Wait until the element is clickable and then click it
		TestUtil.wait.until(ExpectedConditions.elementToBeClickable((ClickIbMsgTextButton)));
		ClickIbMsgTextButton.click();
		TestUtil.TakeScreenshot(driver, " _MailinatorLinkVerificationAndLoginNewUser_Screenshot_ ");

		driver.switchTo().frame("html_msg_body");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		TestUtil.js.executeScript("window.scrollBy(0, 200)", "");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		LinkTextClickToVerifyEmail.click();
		log.info("\n" + "->Title is: " + driver.getTitle() + " and Link is-> " + driver.getCurrentUrl() + "\n");

		// WebDriver control is shifted under the parent window
		driver.switchTo().window(ParentWindowId);
		log.info("\n" + "->After switching window-> Url is :" + driver.getCurrentUrl() + "\n" + "->Title is-> " + driver.getTitle() + "\n");

		driver.navigate().to(prop.getProperty("LoginUrl"));

		//=========Login with the newly Registered user========
		log.info("Login with the newly Registered user");

		loginPage = new LoginPageClass();
		loginPage.Login_Testcases(SignUpPageClass.Emailaddress,SignUpPageClass.password);

		// driver.navigate().refresh();
		driver.get(driver.getCurrentUrl());

		// Verify page url after Email verification is matched or not
		log.info("\n" + "-> Landing page URL is: " + driver.getCurrentUrl());

		// Verify the page URL after email verification is matched or not
		String expectedUrl = "https://staging.certifiedmaillabels.com/";
		String actualUrl = driver.getCurrentUrl();

		try {
			// Check if the current URL is "https://staging.certifiedmaillabels.com/user/dashboard"
			if (actualUrl.equals("https://staging.certifiedmaillabels.com/user/dashboard")) {
				log.info("URL is 'https://staging.certifiedmaillabels.com/user/dashboard', navigating back and refreshing the page.");

				driver.navigate().back();  // Navigate back to the previous page
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

				driver.navigate().refresh();
				// Refresh the page
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));   // Wait for the refresh to complete
			}
			// Verify that the current URL matches the expected URL
			Assert.assertEquals(actualUrl, expectedUrl, "URL verification passed: ");
			log.info("-> User logged in and directed to the landing page successfully. URL verification done." + "\n");

		} catch (AssertionError e) {
			log.error("Login Failed -> These credentials do not match our records" + "\n");
			TestUtil.TakeScreenshot(driver, " _Signup Page Screenshot_ ");
			throw e; // Re-throw the assertion error to ensure the test fails
		}

		return new LandingPageClass();
	}
}