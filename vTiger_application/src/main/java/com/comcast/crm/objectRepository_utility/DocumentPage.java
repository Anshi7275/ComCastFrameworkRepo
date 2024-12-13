package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author anshi
 *contains document page elements create new documents
 */
public class DocumentPage {
	
	WebDriver driver;
	public DocumentPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//img[@alt=\"Create Document...\"]")
	private WebElement createDocumentIcon;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCreateDocumentIcon() {
		return createDocumentIcon;
	}
	

}
