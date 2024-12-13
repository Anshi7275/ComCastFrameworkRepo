package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contactpage {
	
	WebDriver driver;
	
	public Contactpage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);  
	}
	
	@FindBy(xpath = "//img[@alt=\"Create Contact...\"]")
	private WebElement createContIcon;

	public WebElement getCreateContIcon() {
		return createContIcon;
	}
	
	

}
