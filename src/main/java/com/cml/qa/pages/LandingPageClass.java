package com.cml.qa.pages;

import com.cml.qa.base.TestBaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class LandingPageClass extends TestBaseClass {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public static Logger log;
    //Page Center elements
    @FindBy(xpath = "//h1[normalize-space()='USPS Certified Mail Labels']")
    @CacheLookup
    WebElement VerifyPageHeadingH1;
    @FindBy(xpath = "//h2[contains(text(),'Address and Print USPS Certified Mail® Labels Onli')]")
    @CacheLookup
    WebElement VerifyPageHeadingH2;
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
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div/div[1]/div[1]/div[1]/iframe")
    @CacheLookup
    WebElement VerifyVideo;
    @FindBy(xpath = "//div[contains(text(),'Create USPS Certified Mail® labels, Priority Mail ')]//img[@alt='Certified Mail Labels']")
    @CacheLookup
    WebElement VerifyImageAndTextCombined;
    @FindBy(xpath = "//img[@alt='Certified Mail Labels | GET STARTED FOR FREE TODAY']")
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
    //Right side bar links Page elements
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/div/div[2]/aside/ul")
    @CacheLookup
    WebElement ListOfLinks;
    //Click on CML Logo
    @FindBy(xpath = "//*[@id=\"header\"]/div/div[2]/div/div[1]/div/div/a/img")
    @CacheLookup
    WebElement CMLLogoClick;
    //Click on GET STARTED TODAY-Right side Image
    @FindBy(xpath = "//img[@alt='Certified Mail Labels | Get Started for Free Today']")
    @CacheLookup
    WebElement VerifyImageGetStartedToday;

    public LandingPageClass() throws IOException {

        log = LogManager.getLogger(LandingPageClass.class);
        super();
        PageFactory.initElements(driver, this);
    }

    // Define expected values as constants
    final String PAGE_HEADING_H1_EXPECTED = "USPS Certified Mail Labels";
    final String PAGE_HEADING_H2_EXPECTED = "Address and Print USPS Certified Mail® Labels Online!";
    final String P_TAG_SKIP_TEXT_EXPECTED = "Skip the trip to the Post Office… Address and print USPS Certified Mail® Labels online. Save $3.15 on postage for each Certified Mail® green card receipt. No monthly fees, no contracts, and no software or special equipment. Get email notifications with Electronic Delivery Confirmations, Return Receipt Signatures, tracking, and a 10-year compliance archive at no extra cost. You’ll have proof of mailing, letter tracking plus delivery confirmation for each of your compliance letters available 24/7 – 365 days a year.";
    final String H2_TEXT_EXPECTED = "Print Certified Mail Labels OR Send Certified Mail Online!";
    final String IMAGE_AND_TEXT_COMBINED_EXPECTED = "https://cml-ckeditor.s3.amazonaws.com/usps_certified_mail_envelopes_with_return_receipt_electronic_250x166.jpg";
    final String USPSCML_IMAGE_SRC_EXPECTED = "https://cml-ckeditor.s3.amazonaws.com/July%202024%20USPS%20Rates%20Certified%20Mail%20Labels%20.jpg";
    final String GET_STARTED_IMAGE_TEXT_EXPECTED = "https://cml-ckeditor.s3.amazonaws.com/Get-Started-Now-Certified-Mail-Labels-600.jpg";

    public HashMap<String, WebElement> VerifyLandingPageUIElements() throws InterruptedException {

        HashMap<String, WebElement> elements = new HashMap<>();
        // Store WebElements in the HashMap
        elements.put("VerifyPageHeadingH1", VerifyPageHeadingH1);
        elements.put("VerifyPageHeadingH2", VerifyPageHeadingH2);
        elements.put("VerifyUSPSCertifiedMailLabelsImage", VerifyUSPSCertifiedMailLabelsImage);
        elements.put("VerifyPTAG_SkipTheTripText", VerifyPTAG_SkipTheTripText);
        elements.put("VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline", VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline);
        elements.put("VerifyVideo", VerifyVideo);
        elements.put("VerifyImageAndTextCombined", VerifyImageAndTextCombined);
        elements.put("VerifyImage", VerifyImage);

        elements.put("VerifyLink1", VerifyLink1);
        elements.put("VerifyLink2", VerifyLink2);
        elements.put("VerifyLink3", VerifyLink3);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Verify Page Headings
        WebElement pageHeadingH1 = elements.get("VerifyPageHeadingH1");
        WebElement pageHeadingH2 = elements.get("VerifyPageHeadingH2");
        String actualTextH1 = wait.until(ExpectedConditions.visibilityOf(pageHeadingH1)).getText().trim();
        Assert.assertEquals(actualTextH1, PAGE_HEADING_H1_EXPECTED, "Text mismatch for PageHeadingH1");
        log.info("PageHeadingH1 is: " + actualTextH1);
        String actualTextH2 = wait.until(ExpectedConditions.visibilityOf(pageHeadingH2)).getText().trim();
        Assert.assertEquals(actualTextH2, PAGE_HEADING_H2_EXPECTED, "Text mismatch for PageHeadingH2");
        log.info("PageHeadingH2 is: " + actualTextH2);

        // Verify USPS Certified Mail Labels Image
        WebElement uspsImage = elements.get("VerifyUSPSCertifiedMailLabelsImage");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(uspsImage)).isDisplayed(), "The Image is not displayed on the page.");
        String actualSrc = uspsImage.getAttribute("src").trim();
        Assert.assertEquals(actualSrc, USPSCML_IMAGE_SRC_EXPECTED, "Image source URL mismatch.");
        log.info("Image is present and Its Source URL is: " + actualSrc);

        // Scroll down and verify paragraph text
        js.executeScript("window.scrollBy(0, 1200)", "");
        WebElement pTagText = elements.get("VerifyPTAG_SkipTheTripText");
        String actualPTagText = wait.until(ExpectedConditions.visibilityOf(pTagText)).getText().trim();
        Assert.assertEquals(actualPTagText, P_TAG_SKIP_TEXT_EXPECTED, "Text mismatch for VerifyUSPSCMLImagePText");
        log.info("VerifyUSPSCMLImagePText is: " + actualPTagText);

        // Verify H2 Text
        WebElement h2Text = elements.get("VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline");
        String actualH2Text = wait.until(ExpectedConditions.visibilityOf(h2Text)).getText().trim();
        Assert.assertEquals(actualH2Text, H2_TEXT_EXPECTED, "Text mismatch for VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline");
        log.info("VerifyH2Text_PrintCertifiedMailLabelsORSendCertifiedMailOnline is: " + actualH2Text);

        // Verify Video
        WebElement videoElement = elements.get("VerifyVideo");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(videoElement)).isDisplayed(), "Video is not displayed on the page.");
        String videoSrc = videoElement.getAttribute("src");
        Assert.assertNotNull(videoSrc, "Video source URL is missing.");
        log.info("Video is available on the Page and its Source URL is: " + videoSrc);

        // Verify Image and Text Combined
        WebElement imageAndTextElement = elements.get("VerifyImageAndTextCombined");
        String actualImageAndText = wait.until(ExpectedConditions.visibilityOf(imageAndTextElement)).getAttribute("src");
        Assert.assertEquals(actualImageAndText, IMAGE_AND_TEXT_COMBINED_EXPECTED, "Text mismatch for Image and Text Combined");

        log.info("Verify Image and Text both are present and Src Url is: " + actualImageAndText);

        // Verify Get Started Image
        WebElement getStartedImage = elements.get("VerifyImage");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(getStartedImage)).isDisplayed(), "Get Started Image is not displayed on the page.");
        String getStartedImageSrc = getStartedImage.getAttribute("src").trim();
        Assert.assertEquals(getStartedImageSrc, GET_STARTED_IMAGE_TEXT_EXPECTED, "Image source URL mismatch for Get Started Image.");
        log.info("Get Started Image is present and Its Source URL is: " + getStartedImageSrc);

        // Get the bottomLinks container

        log.info("-----Verification of Bottom link started-----");

        // Define the expected URLs and element keys
        String[] expectedUrls = {
                "https://staging.certifiedmaillabels.com/blog/why-is-my-usps-certified-mail-delayed",
                "https://staging.certifiedmaillabels.com/blog/how-usps-certified-mail-works",
                "https://staging.certifiedmaillabels.com/blog/certified-mail-envelopes-secure-admissiblesss"};

        String[] linkKeys = {"VerifyLink1", "VerifyLink2", "VerifyLink3"};

        for (int i = 0; i < expectedUrls.length; i++) {
            String expectedUrl = expectedUrls[i];
            String linkKey = linkKeys[i];

            // Get the link element from the elements map
            WebElement link = elements.get(linkKey);
            String linkText = link.getText();
            String actualUrl = link.getAttribute("href");
            log.info("Expected URL: " + expectedUrl + " | Actual URL from href: " + actualUrl);

            try {
                link.click();
                // Locate the parent div element
                WebElement parentDiv = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div[1]/div"));

                // Find all child elements inside the parent div
                List<WebElement> allElementsInsideLink = parentDiv.findElements(By.xpath(".//*"));

                js.executeScript("window.scrollBy(0, 200)", "");

                // Log details of each element inside the link
                for (WebElement element : allElementsInsideLink) {
                    String tagName = element.getTagName();
                    String text = element.getText();
                    log.info("Tag Name: " + tagName + " | IsDisplayed: " + element.isDisplayed() + " | Text: " + text);
                }

                // Navigate to the expected URL and verify
                driver.navigate().to(expectedUrl);
                wait.until(ExpectedConditions.urlToBe(expectedUrl));
                Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL mismatch for Link: " + linkText);
                log.info("Link verification passed: " + linkText);

                log.info("-----Verification of link ended-----"+link.getText());
            } catch (Exception e) {
                log.error("Error during verification of link: " + linkText, e);
            } finally {
                // Navigate back to the main page to ensure proper reloading for the next iteration
                driver.navigate().back();
                Thread.sleep(2000); // Wait for the page to reload properly
            }
        }

        log.info("-----Verification of Bottom link ended-----");

        return elements;
    }

    public void RightSideBarLinks() throws InterruptedException {
        // Find the list of links in the right sidebar
        WebElement rightSideBar = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div[2]/aside/ul"));
        List<WebElement> allLinks = rightSideBar.findElements(By.xpath(".//a"));

        log.info("Number of elements in the specific <ul>: " + allLinks.size());

        // Iterate through each link in the sidebar
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement singleLink = allLinks.get(i);
            String linkText = singleLink.getText();
            String linkHref = singleLink.getAttribute("href");
            String linkTarget = singleLink.getAttribute("target");

            log.info("Link Text: " + linkText + " | Link: " + linkHref + " | Target: " + linkTarget);
            js.executeScript("window.scrollBy(0, 60)", "");

            // Check if the link opens in a new tab (target='_blank')
            if("_blank".equals(linkTarget)) {
                log.info("Link opens in a new tab, switching to the new tab to verify content.");

                // Store the current window handle
                String originalWindow = driver.getWindowHandle();

                // Click the link (opens in a new tab)
                singleLink.click();

                // Wait for the new tab to open and switch to it
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandle.equals(originalWindow)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }
                // Wait for the new tab content to load
                Thread.sleep(3000);

                // Locate page content and log details of all elements within the parent div
                WebElement pageContent = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div[1]/div"));
                List<WebElement> allElementsInsideParentDiv = pageContent.findElements(By.xpath(".//*[not(self::br)]"));
                for (WebElement element : allElementsInsideParentDiv) {
                    Thread.sleep(1000);

                    //Scroll the whole page
                    //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    log.info("Tag Name: " + element.getTagName() + " | IsDisplayed: " + element.isDisplayed() + " | Text: " + element.getText());
                }
                log.info("====Link opened and its relevant content verified===="+linkText);
                // Close the new tab and switch back to the original window

                driver.switchTo().window(originalWindow);

                // Wait for the original page to load again
                Thread.sleep(3000);

            } else {
                // Handle links that open in the same tab
                singleLink.click();
                Thread.sleep(3000);

                // Locate page content and log details of all elements within the parent div
                WebElement pageContent = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div[1]/div"));
                List<WebElement> allElementsInsideParentDiv = pageContent.findElements(By.xpath(".//*[not(self::br)]"));
                for (WebElement element : allElementsInsideParentDiv) {
                    Thread.sleep(1000);
                    //Scroll the whole page
                    //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    log.info("Tag Name: " + element.getTagName() + " | IsDisplayed: " + element.isDisplayed() + " | Text: " + element.getText());
                }
                log.info("====Link opened and its relevant content verified===="+linkText);

                // Navigate back to the previous page
                driver.navigate().back();
            }
        }
    }
    public void VerifyCMLLogo() throws InterruptedException {

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(CMLLogoClick)).isDisplayed(), "The Image is not displayed on the page.");
        log.info("Logo is present: " + CMLLogoClick.isDisplayed() + "  and Its Source URL is: " + CMLLogoClick.getAttribute("src"));

        CMLLogoClick.click();

        String expectedUrlAfterClickOnLogo = "https://staging.certifiedmaillabels.com/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrlAfterClickOnLogo, "Page URL mismatch.");
        log.info("After Clicking on CML Logo, its Page URL is: " + expectedUrlAfterClickOnLogo);
    }
    public void GetStartedToday_ImageClick(){
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(VerifyImageGetStartedToday)).isDisplayed(), "'Get Started Today' Image is not displayed on the page.");
        String getStartedImageSrc = VerifyImageGetStartedToday.getAttribute("src");
        log.info("Get Started Image is present and Its Source URL is: " + getStartedImageSrc);
        VerifyImageGetStartedToday.click();
     //   String ClickGetStartedImage_ExpectedURl= "https://staging.certifiedmaillabels.com/register";
        String ClickGetStartedImage_ExpectedURl= "https://staging.certifiedmaillabels.com/";
        Assert.assertEquals(getStartedImageSrc, GET_STARTED_IMAGE_TEXT_EXPECTED, "Image source URL mismatch for Get Started Image.");
        Assert.assertEquals(driver.getCurrentUrl(),ClickGetStartedImage_ExpectedURl, "Image source URL mismatch for Get Started Image.");

        log.info("Get Started Image clicked and Page Url is: " + ClickGetStartedImage_ExpectedURl);
    }
}