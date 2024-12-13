package com.comcast.crm.objectRepository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationWindowPage {
	
	WebDriver driver;

	public OrganizationWindowPage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);  

	}
	
	@FindBy(name = "search_text")
	private WebElement searchtxt;
	
	@FindBy(name = "search")
	private WebElement searchbtn;

	public WebElement getSearchtxt() {
		return searchtxt;
	}

	public WebElement getSearchbtn() {
		return searchbtn;
	}
	
	

}
