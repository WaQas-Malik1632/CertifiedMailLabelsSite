package com.cml.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cml.qa.base.TestBaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil extends TestBaseClass {

	//Page Load TimeOut
	public static long PAGE_LOAD_TIMEOUT = 40;
	//Implicit wait for 40 Seconds
	public static long IMPLICIT_WAIT = 20;
	//Explicit wait for 40 Seconds
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	//Window Scroll
	public static JavascriptExecutor js = (JavascriptExecutor) driver;

	//Both conditions are same but recommended is 2nd one
	// driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
	// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

	static LocalDateTime date = LocalDateTime.now();

	static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
	static String formattedDate = date.format(myFormatObj);

	public TestUtil() throws IOException {
		super();
	}
	// Take Screenshot
	public static void TakeScreenshot(WebDriver webdriver, String filename) throws IOException {

		try {
			TakesScreenshot src = ((TakesScreenshot) webdriver);
			File srcfile = src.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcfile, new File(".//Screenshot//" + formattedDate + filename + ".png"));
		} catch (WebDriverException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
