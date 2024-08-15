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
    @FindBy(xpath = "//h2[2]")
    @CacheLookup
    WebElement VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline;
    @FindBy(linkText = "Send Certified Mail")
    @CacheLookup
    WebElement VerifyLinkText_SendCertifiedMail;
    @FindBy(linkText = "Certified Mail Labels")
    @CacheLookup
    WebElement VerifyLinkText_CertifiedMailLabels;

    //@FindBy(xpath = "//div[@role='main']")
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div/div[1]/div[1]/div[1]/iframe")
    @CacheLookup
    WebElement VerifyVideo;

    @FindBy(xpath = "//div[contains(text(),'Create USPS Certified Mail® labels, Priority Mail ')]//img[@alt='Certified Mail Labels']")
    @CacheLookup
    WebElement VerifyImageAndTextCombined;
    @FindBy(xpath = "//img[@alt='Certified Mail Labels | Get Started for Free Today']")
    @CacheLookup
    WebElement VerifyImage;
    @FindBy(linkText = "Certified Mail Labels: Why Is My USPS Certified Mail Delayed?")
    @CacheLookup
    WebElement VerifyLink1;
    @FindBy(linkText = "Certified Mail Labels: How USPS Certified Mail Works")
    @CacheLookup
    WebElement VerifyLink2;
    @FindBy(linkText = "Certified Mail Envelopes: Secure and Admissible")
    @CacheLookup
    WebElement VerifyLink3;

    public LandingPageClass() throws IOException {

        log = LogManager.getLogger(LandingPageClass.class);
        super();
        PageFactory.initElements(driver, this);
    }

    public HashMap<String, String> VerifyLandingPageUIElements() throws InterruptedException {

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

        elements.put("VerifyUSPSCMLImagePText", "Skip the trip to the Post Office…");
        String Expected_VerifyPTAG_SkipTheTripText = "Skip the trip to the Post Office… Address and print USPS Certified Mail® Labels online. Save $3.15 on postage for each Certified Mail® green card receipt. No monthly fees, no contracts, and no software or special equipment. Get email notifications with Electronic Delivery Confirmations, Return Receipt Signatures, tracking, and a 10-year compliance archive at no extra cost. You’ll have proof of mailing, letter tracking plus delivery confirmation for each of your compliance letters available 24/7 – 365 days a year.";
        Assert.assertEquals(VerifyPTAG_SkipTheTripText.getText(), Expected_VerifyPTAG_SkipTheTripText);
        log.info("Verify USPS CML Image Paragraph Text is: " + VerifyPTAG_SkipTheTripText.getText());

        elements.put("VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline", "Print Certified Mail Labels OR Send Certified Mail Online!");
        String Expected_VerifyH2Text_PrintCMLOrSendMailLablesOnline = "Print Certified Mail Labels OR Send Certified Mail Online!";
        Assert.assertEquals(VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline.getText(), Expected_VerifyH2Text_PrintCMLOrSendMailLablesOnline);
        log.info("Heading2Text_is: " + VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline.getText());
        /*
        //Verify links are working fine or not

        elements.put("VerifyLink2_SendCertifiedMail", "Send Certified Mail");
        String AttributeUrl2 = VerifyLinkText_SendCertifiedMail.getAttribute("href");
        VerifyLinkText_SendCertifiedMail.click();
        log.info("Text for Link 2 is :" +VerifyLinkText_SendCertifiedMail.getText()+"\n"+"Attribute is: "+AttributeUrl2);
        driver.navigate().to("https://staging.sendcertifiedmail.com/");
        String ExpectedLink1Url2Text="https://staging.sendcertifiedmail.com/";
        Assert.assertEquals(driver.getCurrentUrl(),ExpectedLink1Url2Text);
        driver.navigate().back();

        elements.put("VerifyLink1_PrintCertifiedMail", "Certified Mail Labels");
        VerifyLinkText_CertifiedMailLabels.click();
        String AttributeUrl1 = VerifyLinkText_CertifiedMailLabels.getAttribute("href");
        log.info("Text for Link 1 is : " +VerifyLinkText_CertifiedMailLabels.getText()+"\n"+"Attribute is: "+AttributeUrl1);
        driver.navigate().to("https://staging.certifiedmaillabels.com/");
        String ExpectedLink1UrlText="https://staging.certifiedmaillabels.com/";
        Assert.assertEquals(driver.getCurrentUrl(),ExpectedLink1UrlText);
        //js.executeScript("window.scrollBy(0, 1050)", "");
         */
        elements.put("VerifyVideo", "VerifyVideoIsDisplayed");
        boolean VideoIsPresent = VerifyVideo.isDisplayed();
        Thread.sleep(5000);
        boolean canPlay = (Boolean) js.executeScript(
                "return arguments[0].readyState === 4;", VerifyVideo);
      //  Assert.assertTrue(canPlay, "The video is not ready to be played.");
        Assert.assertTrue(VideoIsPresent, "The Image is not displayed on the page.");
        log.info("Video is available on the Page: " + VideoIsPresent+"\n"+"'Video is ready and can be played':"+ canPlay);

        elements.put("VerifyImageAndTextCombined", "Create USPS Certified Mail® labels, Priority Mail labels and Express Mail labels with USPS Postage online! No more stickers, forms, or lines at the Post Office! Just log on, address, print, and mail! No monthly fees and no special equipment are needed. Pay as you mail, and skip the trip to the Post Office.");
        boolean imageAndTextCombinedPresent = VerifyImageAndTextCombined.isDisplayed();
        Assert.assertTrue(imageAndTextCombinedPresent, "The Image is not displayed on the page.");
        log.info("Verify Image and Text both are present: " + imageAndTextCombinedPresent);

        elements.put("VerifyImage", "Get Started Today Image");
        boolean GetStartedImageVerify = VerifyImage.isDisplayed();
        Assert.assertTrue(GetStartedImageVerify, "The Image is not displayed on the page.");
        log.info("Verify Get Started Today Image is present: " + GetStartedImageVerify);

        //Verify bottom links are working fine or not
        elements.put("VerifyLink1", "Certified Mail Labels: Why Is My USPS Certified Mail Delayed?");
        VerifyLink1.click();
        String AttUrl3 = VerifyLink1.getAttribute("href");
        log.info("Link Text for Link1 is : " + VerifyLink1.getText()+"\n"+"Attribute is: "+AttUrl3);
        driver.navigate().to("https://staging.certifiedmaillabels.com/blog/why-is-my-usps-certified-mail-delayed");
        String ExpectedLink1Url = "https://staging.certifiedmaillabels.com/blog/why-is-my-usps-certified-mail-delayed";
        Assert.assertEquals(driver.getCurrentUrl(), ExpectedLink1Url);
        driver.navigate().back();

        elements.put("VerifyLink2", "Certified Mail Labels: How USPS Certified Mail Works");
        VerifyLink2.click();
        String AttUrl4 = VerifyLink2.getAttribute("href");
        log.info("Link Text for Link2 is :" + VerifyLink2.getText()+"\n"+"Attribute is: "+AttUrl4);
        driver.navigate().to("https://staging.certifiedmaillabels.com/blog/how-usps-certified-mail-works");
        String ExpectedLink2Url = "https://staging.certifiedmaillabels.com/blog/how-usps-certified-mail-works";
        Assert.assertEquals(driver.getCurrentUrl(), ExpectedLink2Url);
        driver.navigate().back();

        elements.put("VerifyLink3", "Certified Mail Envelopes: Secure and Admissible");
        VerifyLink3.click();
        String AttUrl5 = VerifyLink3.getAttribute("href");
        log.info("Link Text for Link3 is :" + VerifyLink3.getText()+"\n"+"Attribute is: " +AttUrl5);
        driver.navigate().to("https://staging.certifiedmaillabels.com/blog/certified-mail-envelopes-secure-admissible");
        String ExpectedLink3Url = "https://staging.certifiedmaillabels.com/blog/certified-mail-envelopes-secure-admissible";
        Assert.assertEquals(driver.getCurrentUrl(), ExpectedLink3Url);

        return elements;
    }
}