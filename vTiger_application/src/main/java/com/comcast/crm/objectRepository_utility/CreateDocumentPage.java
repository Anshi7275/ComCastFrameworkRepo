package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
}
