package com.cml.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
//	public static JavascriptExecutor js;

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	LocalDateTime date = LocalDateTime.now();

	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
	String formattedDate = date.format(myFormatObj);

	// Take Screenshot
	public void TakeScreenshot(WebDriver webdriver, String filename) throws IOException {
		TakesScreenshot src = ((TakesScreenshot) webdriver);
		File srcfile = src.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(".//Screenshot//" + formattedDate + filename + ".png"));
	}
}
