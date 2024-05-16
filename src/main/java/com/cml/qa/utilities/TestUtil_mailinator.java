package com.cml.qa.utilities;

import com.cml.qa.base.TestBaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class TestUtil_mailinator extends TestBaseClass {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//a[normalize-space()='click resend email']")
	WebElement linkTextClick_VerifyEmail;

	@FindBy(xpath = "//input[@id='inbox_field']")
	WebElement MailinatorIb;

	@FindBy(xpath = "//button[@class='primary-btn']")
	WebElement ClickGo;

	@FindBy(xpath = "//td[normalize-space()='CERTIFIED MAIL LABELS']")
	WebElement ClickIbMsgResult;

	@FindBy(xpath = "//a[@class='button button-primary']")
	WebElement LinkTextClickToVerifyEmail;


	public TestUtil_mailinator() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public void MailinatorLinkVerification(String email) throws InterruptedException {
		//After signup, user can click to resend the email link again
		//  linkTextClick_VerifyEmail.click();

		driver.navigate().to("https://www.mailinator.com/v4/public/inboxes.jsp");
		MailinatorIb.sendKeys(email);

		ClickGo.click();

		Thread.sleep(3000);
		ClickIbMsgResult.click();

		driver.switchTo().frame("html_msg_body");

		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0, 200)", "");

		LinkTextClickToVerifyEmail.click();
	}
}
