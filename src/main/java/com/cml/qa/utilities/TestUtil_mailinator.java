package com.cml.qa.utilities;

import com.cml.qa.base.TestBaseClass;
import com.cml.qa.pages.DashboardPageClass;
import com.cml.qa.pages.LoginPageClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class TestUtil_mailinator extends TestBaseClass {
	TestUtil util=new TestUtil();
	LoginPageClass loginPage;

	JavascriptExecutor js = (JavascriptExecutor) driver;

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

	public TestUtil_mailinator() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public DashboardPageClass MailinatorLinkVerificationAndLoginNewUser(String email) throws InterruptedException, IOException {
		//After signup, user can click to resend the email link again
		//linkTextClick_VerifyEmail.click();

		driver.navigate().to("https://www.mailinator.com/v4/public/inboxes.jsp");
		System.out.println("\n"+"User is navigating to the Mailinator for the Email verification "+"\n");

		//Getting the Id of parent windows here
		String ParentWindowId= driver.getWindowHandle();
		MailinatorIb.sendKeys(email);

		Thread.sleep(3000);
		ClickGo.click();
		util.TakeScreenshot(driver, " _MailinatorLinkVerificationAndLoginNewUser_Screenshot_ ");

		Thread.sleep(3000);
		ClickIbMsgResult.click();
		util.TakeScreenshot(driver, " _MailinatorLinkVerificationAndLoginNewUser_Screenshot_ ");

		driver.switchTo().frame("html_msg_body");

		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0, 200)", "");

		Thread.sleep(3000);
		LinkTextClickToVerifyEmail.click();
		System.out.println("\n"+"->Title is: "+driver.getTitle()+" and Link is-> "+driver.getCurrentUrl()+"\n");

		//WebDriver control is shifted under the parent window
		driver.switchTo().window(ParentWindowId);

		System.out.println("\n"+"->After switching window-> Url is :"+ driver.getCurrentUrl()+"\n"+"->Title is-> "+driver.getTitle()+"\n");

		driver.navigate().to("https://staging.certifiedmaillabels.com/login");

		loginPage=new LoginPageClass();
		loginPage.Login_Testcases("Testwarner@mailinator.com","Pass@123");

		//driver.navigate().refresh();
		driver.get(driver.getCurrentUrl());
		System.out.println("\n"+"-> Landing page Url is: "+driver.getCurrentUrl()+" and Title is-> "+driver.getTitle()+"\n");

		// Verify page url after Email verification is matched or not
		String ExpectedUrl = "https://staging.certifiedmaillabels.com/";
		String ActualUrl = driver.getCurrentUrl();
		try {
			Assert.assertEquals(ActualUrl, ExpectedUrl, "URL verification Passed: ");
			System.out.println("->User logged in successfully" + "\n");
			System.out.println("->Newly logged in user: Page Url has been verified successfully");

		} catch (AssertionError e) {
			System.out.println("Login Failed->These credentials do not match our records" + "\n");
			util.TakeScreenshot(driver, " _Signup Page Screenshot_ ");
			throw e; // Re-throw the assertion error to ensure the test fails
		}
		return new DashboardPageClass();
	}
}