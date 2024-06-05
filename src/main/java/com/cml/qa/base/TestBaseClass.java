package com.cml.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.cml.qa.utilities.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;

	public TestBaseClass() throws IOException {
		log= LogManager.getLogger(TestBaseClass.class);

		prop = new Properties();
		FileInputStream ip = new FileInputStream("D:/Automation Sites/CertifiedMailLabelsSite/src/main/java/com/cml/qa/config/config.properties");
		prop.load(ip);
	}

	public static void intialization() {
		String browserName = prop.getProperty("browser");
		log.info("******************** Browser initialization started  *********************");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("**** Launching the Chrome Browser  ****");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("**** Launching the Firefox Browser  ****");
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.info("**** Launching the Edge Browser  ****");
		} else {
			System.out.println("\n" + "You have entered Invalid Browser Name" + "\n");
			log.warn("Hi, this is warning message");
			log.debug("This is debug message");
			log.fatal("This is fatal error message");
		}
		driver.get(prop.getProperty("url"));
		log.info("**** Entering the Application Url ****");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
}
