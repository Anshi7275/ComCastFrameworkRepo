package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateDocumentPage {
	WebDriver driver;
	public CreateDocumentPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//input[@name=\"notes_title\"]")
	private WebElement titleTextBox;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getTitleTextBox() {
		return titleTextBox;
	}
}
