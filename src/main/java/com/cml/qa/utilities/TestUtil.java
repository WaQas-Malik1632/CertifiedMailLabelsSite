package com.cml.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cml.qa.base.TestBaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class TestUtil extends TestBaseClass {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	LocalDateTime date = LocalDateTime.now();

	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
	String formattedDate = date.format(myFormatObj);

	public TestUtil() throws IOException {
		super();
	}

	// Take Screenshot
	public void TakeScreenshot(WebDriver webdriver, String filename) throws IOException {

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
