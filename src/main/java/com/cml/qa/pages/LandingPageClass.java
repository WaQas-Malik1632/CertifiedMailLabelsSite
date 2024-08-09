package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class LandingPageClass extends TestBaseClass {

    JavascriptExecutor js = (JavascriptExecutor) driver;
    public static Logger log;
    @FindBy(xpath = "//h1[normalize-space()='USPS Certified Mail Labels']")
    @CacheLookup
    WebElement VerifyPageHeadingH1;
    String Expected_PageHeadingH1 = "USPS Certified Mail Labels";
    @FindBy(xpath = "//h2[contains(text(),'Address and Print USPS Certified Mail® Labels Onli')]")
    @CacheLookup
    WebElement VerifyPageHeadingH2;
    String Expected_PageHeadingH2 = "Address and Print USPS Certified Mail® Labels Online!";
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div/div[1]/div[1]/h2[1]/a/img")
    @CacheLookup
    WebElement VerifyUSPSCertifiedMailLabelsImage;
    @FindBy(xpath = "//p[contains(text(),'Skip the trip to the Post Office… Address and prin')]")
    @CacheLookup
    WebElement VerifyPTAG_SkipTheTripText;
    String Expected_VerifyPTAG_SkipTheTripText = "Skip the trip to the Post Office… Address and print USPS Certified Mail® Labels online. Save $3.15 on postage for each Certified Mail® green card receipt. No monthly fees, no contracts, and no software or special equipment. Get email notifications with Electronic Delivery Confirmations, Return Receipt Signatures, tracking, and a 10-year compliance archive at no extra cost. You’ll have proof of mailing, letter tracking plus delivery confirmation for each of your compliance letters available 24/7 – 365 days a year.";
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div/div[1]/div[1]/h2[2]")
    @CacheLookup
    WebElement VerifyH2Text_PrintCMLOrSendMailLablesOnline;
    String Expected_VerifyH2Text_PrintCMLOrSendMailLablesOnline = "Print Certified Mail Labels OR Send Certified Mail Online!";
    @FindBy(linkText = "Send Certified Mail")
    @CacheLookup
    WebElement VerifyLinkTextCertifiedMailLabels;
    String Expected_VerifyLinkTextCertifiedMailLabels = "Certified Mail Labels";
    @FindBy(linkText = "Certified Mail Labels")
    @CacheLookup
    WebElement VerifyLinkTextSendCertifiedMail;
    String Expected_VerifyLinkTextSendCertifiedMail = "Send Certified Mail";
    @FindBy(xpath = "//div[contains(text(),'Create USPS Certified Mail® labels, Priority Mail ')]//img[@alt='Certified Mail Labels']")
    @CacheLookup
    WebElement VerifyImageAndTextCombined;
    @FindBy(xpath = "//img[@alt='Certified Mail Labels | Get Started for Free Today']")
    @CacheLookup
    WebElement VerifyImage;

    @FindBy(xpath = "//a[contains(text(),'Certified Mail Labels: Why Is My USPS Certified Ma')]")
  //  @FindBy(linkText="Certified Mail Labels: Why Is My USPS Certified Mail Delayed?")
    @CacheLookup
    WebElement VerifyLink1;

    @FindBy(xpath = "//a[contains(text(),'Certified Mail Labels: How USPS Certified Mail Wor')]")
    //@FindBy(linkText="Certified Mail Labels: How USPS Certified Mail Works")
    @CacheLookup
    WebElement VerifyLink2;

    @FindBy(xpath = "//a[normalize-space()='Certified Mail Envelopes: Secure and Admissible']")
   // @FindBy(linkText="Certified Mail Envelopes: Secure and Admissible")
    @CacheLookup
    WebElement VerifyLink3;

    public LandingPageClass() throws IOException {

        log = LogManager.getLogger(LandingPageClass.class);
        super();
        PageFactory.initElements(driver, this);
    }

    public HashMap<String, String> VerifyLandingPageUIElements() {

        HashMap<String, String> elements = new HashMap<>();
        elements.put("VerifyPageHeadingH1", "USPS Certified Mail Labels");
        log.info("PageHeadingH1: " + Expected_PageHeadingH1);
        Assert.assertEquals(Expected_PageHeadingH1, VerifyPageHeadingH1.getText());

        elements.put("VerifyPageHeadingH2", "Address and Print USPS Certified Mail® Labels Online!");
        Assert.assertEquals(Expected_PageHeadingH2, VerifyPageHeadingH2.getText());
        log.info("VerifyPageHeadingH2 is: " + VerifyPageHeadingH2.getText());

        elements.put("VerifyUSPSCertifiedMailLabelsImage", String.valueOf(VerifyUSPSCertifiedMailLabelsImage.isDisplayed()));
        boolean imagePresent = VerifyUSPSCertifiedMailLabelsImage.isDisplayed();
        Assert.assertTrue(imagePresent, "The Image is not displayed on the page.");
        log.info("Verify Image is present: " + imagePresent);
        js.executeScript("window.scrollBy(0, 1200)", "");
        log.info("Scrolling the window vertically");

        elements.put("VerifyUSPSCMLImagePText", "Skip the trip to the Post Office… Address and print USPS Certified Mail® Labels online. Save $3.15 on postage for each Certified Mail® green card receipt. No monthly fees, no contracts, and no software or special equipment. Get email notifications with Electronic Delivery Confirmations, Return Receipt Signatures, tracking, and a 10-year compliance archive at no extra cost. You’ll have proof of mailing, letter tracking plus delivery confirmation for each of your compliance letters available 24/7 – 365 days a year.");
        Assert.assertEquals(Expected_VerifyPTAG_SkipTheTripText, VerifyPTAG_SkipTheTripText.getText());
        log.info("Verify USPS CML Image Paragraph Text is: " + VerifyPTAG_SkipTheTripText.getText());
        elements.put(Expected_VerifyH2Text_PrintCMLOrSendMailLablesOnline, VerifyH2Text_PrintCMLOrSendMailLablesOnline.getText());
        log.info("Heading2Text_PrintCMLOrSendMailLablesOnline is: " + VerifyH2Text_PrintCMLOrSendMailLablesOnline.getText());

        elements.put("VerifyImageAndTextCombined", "Create USPS Certified Mail® labels, Priority Mail labels and Express Mail labels with USPS Postage online! No more stickers, forms, or lines at the Post Office! Just log on, address, print, and mail! No monthly fees and no special equipment are needed. Pay as you mail, and skip the trip to the Post Office.");
        boolean imageAndTextCombinedPresent = VerifyImageAndTextCombined.isDisplayed();
       //String imageAndTextCombinedPresent = VerifyImageAndTextCombined.getText();
        Assert.assertTrue(imageAndTextCombinedPresent, "The Image is not displayed on the page.");
        log.info("Verify Image and Text both are present: " + VerifyImageAndTextCombined);

        elements.put("VerifyImage", "Get Started Today Image");
        boolean GetStartedImageVerify = VerifyImage.isDisplayed();
        Assert.assertTrue(GetStartedImageVerify, "The Image is not displayed on the page.");
        log.info("Verify Get Started Today Image is present: " + GetStartedImageVerify);






        return elements;
    }

}
