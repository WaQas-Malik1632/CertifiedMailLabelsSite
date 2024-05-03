package com.cml.qa.pages;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.cml.qa.base.TestBaseClass;

public class HomePageClass extends TestBaseClass{

	public HomePageClass() throws IOException
	{
		super();
		PageFactory.initElements(driver, this);
	}
}
